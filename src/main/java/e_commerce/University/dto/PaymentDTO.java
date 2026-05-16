package e_commerce.University.dto;

import e_commerce.University.Enum.PaymentStatus;

public class PaymentDTO {

    private Long id;
    private Long orderId;
    private Double amount;
    private PaymentStatus status;
    private String paymentMethod;

    public PaymentDTO() {}

    public PaymentDTO(Long id, Long orderId, Double amount, PaymentStatus status, String paymentMethod) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.status = status;
        this.paymentMethod = paymentMethod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}