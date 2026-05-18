package e_commerce.University.dto;

import e_commerce.University.Enum.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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

}