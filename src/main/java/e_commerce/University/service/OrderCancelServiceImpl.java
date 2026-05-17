package e_commerce.University.service;

import e_commerce.University.Enum.OrderStatus;
import e_commerce.University.entity.Order;
import e_commerce.University.entity.OrderItem;
import e_commerce.University.entity.Product;
import e_commerce.University.repository.OrderItemRepository;
import e_commerce.University.repository.OrderRepository;
import e_commerce.University.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderCancelServiceImpl implements OrderCancelService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    public OrderCancelServiceImpl(OrderRepository orderRepository,
                                  OrderItemRepository orderItemRepository,
                                  ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));


        if (order.getStatus() != OrderStatus.DELIVERED) {
            throw new RuntimeException("Only delivered orders can be cancelled");
        }

        List<OrderItem> items = orderItemRepository.findByOrder_Id(orderId);

        for (OrderItem item : items) {

            Product product = item.getProduct();


            product.setStock(product.getStock() + item.getQuantity());

            productRepository.save(product);
        }

        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }
}