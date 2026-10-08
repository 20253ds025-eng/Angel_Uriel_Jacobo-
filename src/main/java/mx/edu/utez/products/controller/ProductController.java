package mx.edu.utez.products.controller;
//Angel Uriel Jacobo Uriostegui
import mx.edu.utez.products.model.Product;
import mx.edu.utez.products.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService service;

  public ProductController(ProductService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<Product>> findAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> findById(@PathVariable Long id) {
    Optional<Product> product = service.findById(id);
    if (product.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(product.get());
  }

  @PostMapping
  public ResponseEntity<Product> save(@RequestBody Product product) {
    Product newProduct = service.save(product);
    return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product productDetails) {
    Optional<Product> updatedProduct = service.update(id, productDetails);
    if (updatedProduct.isPresent()) {
      return ResponseEntity.ok(updatedProduct.get());
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    boolean isDeleted = service.delete(id);
    if (!isDeleted) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.noContent().build();
  }
}