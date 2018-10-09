package vn.hcmute.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hcmute.projectmanagement.entity.Cart_Product;

import java.util.List;

@Repository
public interface CartProductRepository extends JpaRepository<Cart_Product, Long> {
    List<Cart_Product> findById_CartId(Long id);
}
