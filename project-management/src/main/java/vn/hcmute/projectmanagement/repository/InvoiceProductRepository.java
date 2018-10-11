package vn.hcmute.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hcmute.projectmanagement.entity.Invoice_Product;

@Repository
public interface InvoiceProductRepository extends JpaRepository<Invoice_Product, Long> {
}
