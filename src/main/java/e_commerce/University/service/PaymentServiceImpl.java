package e_commerce.University.service;

import e_commerce.University.Enum.OrderStatus;
import e_commerce.University.Enum.PaymentStatus;
import e_commerce.University.dto.PaymentDTO;
import e_commerce.University.entity.Order;
import e_commerce.University.entity.OrderItem;
import e_commerce.University.entity.Payment;
import e_commerce.University.entity.Product;
import e_commerce.University.repository.OrderItemRepository;
import e_commerce.University.repository.OrderRepository;
import e_commerce.University.repository.PaymentRepository;
import e_commerce.University.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository,
                              OrderRepository orderRepository,
                              ProductRepository productRepository,
                              OrderItemRepository orderItemRepository) {

        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    private PaymentDTO mapToDTO(Payment payment) {

        return new PaymentDTO(
                payment.getId(),
                payment.getOrder().getId(),
                payment.getAmount(),
                payment.getStatus(),
                payment.getPaymentMethod()
        );
    }

    @Override
    public PaymentDTO create(PaymentDTO dto) {

        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Payment payment = new Payment();

        payment.setOrder(order);
        payment.setAmount(dto.getAmount());
        payment.setStatus(PaymentStatus.UNPAID);
        payment.setPaymentMethod(dto.getPaymentMethod());

        return mapToDTO(paymentRepository.save(payment));
    }

    @Override
    public List<PaymentDTO> getAll() {

        return paymentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDTO pay(Long id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        // Prevent duplicate payment
        if (payment.getStatus() == PaymentStatus.PAID) {
            throw new RuntimeException("Payment already completed");
        }

        payment.setStatus(PaymentStatus.PAID);
        payment.setPaidAt(LocalDateTime.now());

        Order order = payment.getOrder();

        List<OrderItem> items = orderItemRepository.findByOrder_Id(order.getId());

        for (OrderItem item : items) {

            Product product = item.getProduct();

            int currentStock = product.getStock();
            int orderedQuantity = item.getQuantity();

            int newStock = currentStock - orderedQuantity;


            if (newStock < 0) {
                throw new RuntimeException(
                        "Not enough stock for product: " + product.getName()
                );
            }

            product.setStock(newStock);

            productRepository.save(product);
        }
        order.setStatus(OrderStatus.DELIVERED);
        orderRepository.save(order);

        return mapToDTO(paymentRepository.save(payment));
    }

    @Override
    public void delete(Long id) {

        paymentRepository.deleteById(id);
    }
}