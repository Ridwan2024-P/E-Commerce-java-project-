package e_commerce.University.service;

import e_commerce.University.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO create(OrderDTO dto);

    List<OrderDTO> getAll();

    OrderDTO getById(Long id);

    OrderDTO updateStatus(Long id, OrderDTO dto);

    void delete(Long id);
}