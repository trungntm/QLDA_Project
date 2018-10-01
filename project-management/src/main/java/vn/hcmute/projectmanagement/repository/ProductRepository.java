package vn.hcmute.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hcmute.projectmanagement.entity.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductsById(long id);
}
