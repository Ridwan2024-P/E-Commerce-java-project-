package e_commerce.University.controller;

import e_commerce.University.dto.ProductDTO;
import e_commerce.University.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductDTO dto) {
        try {
            return ResponseEntity.ok(service.create(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating product: " + e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(service.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching products: " + e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Product not found: " + e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductDTO dto) {
        try {
            return ResponseEntity.ok(service.update(id, dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating product: " + e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("Deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting product: " + e.getMessage());
        }
    }
}