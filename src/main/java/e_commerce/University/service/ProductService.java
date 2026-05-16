package e_commerce.University.service;

import e_commerce.University.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO create(ProductDTO dto);

    List<ProductDTO> getAll();

    ProductDTO getById(Long id);

    ProductDTO update(Long id, ProductDTO dto);

    void delete(Long id);
}