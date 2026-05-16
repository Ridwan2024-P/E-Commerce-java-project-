package e_commerce.University.controller;

import e_commerce.University.dto.PaymentDTO;
import e_commerce.University.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> create(@RequestBody PaymentDTO dto) {
        return ResponseEntity.ok(paymentService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAll() {
        return ResponseEntity.ok(paymentService.getAll());
    }

    @PutMapping("/{id}/pay")
    public ResponseEntity<PaymentDTO> pay(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.pay(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        paymentService.delete(id);
        return ResponseEntity.ok("Payment deleted");
    }
}