package e_commerce.University.service;

import e_commerce.University.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {

    PaymentDTO create(PaymentDTO dto);

    List<PaymentDTO> getAll();

    PaymentDTO pay(Long id);

    void delete(Long id);
}