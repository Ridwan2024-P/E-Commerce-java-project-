package e_commerce.University.controller;

import e_commerce.University.service.OrderCancelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderCancelController {

    private final OrderCancelService orderCancelService;

    public OrderCancelController(OrderCancelService orderCancelService) {
        this.orderCancelService = orderCancelService;
    }

    @PutMapping("/orders/{id}/cancel")
    public ResponseEntity<String> cancelOrder(@PathVariable Long id) {

        orderCancelService.cancelOrder(id);

        return ResponseEntity.ok("Order cancelled successfully");
    }
}