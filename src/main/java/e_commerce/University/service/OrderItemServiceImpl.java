package e_commerce.University.service;

import e_commerce.University.dto.OrderItemDTO;
import e_commerce.University.entity.Order;
import e_commerce.University.entity.OrderItem;
import e_commerce.University.entity.Product;
import e_commerce.University.repository.OrderItemRepository;
import e_commerce.University.repository.OrderRepository;
import e_commerce.University.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository,
                                OrderRepository orderRepository,
                                ProductRepository productRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    private OrderItemDTO mapToDTO(OrderItem item) {
        return new OrderItemDTO(
                item.getId(),
                item.getOrder().getId(),
                item.getProduct().getId(),
                item.getQuantity(),
                item.getPrice()
        );
    }

    @Override
    public OrderItemDTO create(OrderItemDTO dto) {

        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(dto.getQuantity());
        item.setPrice(dto.getPrice());

        return mapToDTO(orderItemRepository.save(item));
    }

    @Override
    public List<OrderItemDTO> getAll() {
        return orderItemRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        orderItemRepository.deleteById(id);
    }
}