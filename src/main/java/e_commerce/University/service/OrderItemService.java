package e_commerce.University.service;


import e_commerce.University.dto.OrderItemDTO;

import java.util.List;

public interface OrderItemService {

    OrderItemDTO create(OrderItemDTO dto);

    List<OrderItemDTO> getAll();

    void delete(Long id);
}