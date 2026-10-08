package mx.edu.utez.products.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import mx.edu.utez.products.model.Product;
public interface ProductRepository extends JpaRepository<Product,Long>{


}
