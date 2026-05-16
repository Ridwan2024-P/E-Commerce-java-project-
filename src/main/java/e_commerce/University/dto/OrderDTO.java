package e_commerce.University.dto;

import e_commerce.University.Enum.OrderStatus;

public class OrderDTO {

    private Long id;
    private Long userId;
    private OrderStatus status;
    private Double totalAmount;

    public OrderDTO() {}

    public OrderDTO(Long id, Long userId, OrderStatus status, Double totalAmount) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}