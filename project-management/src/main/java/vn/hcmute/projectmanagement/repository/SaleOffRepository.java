package vn.hcmute.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hcmute.projectmanagement.entity.SaleOff;

import java.util.Optional;

@Repository
public interface SaleOffRepository extends JpaRepository<SaleOff, Long> {
    Optional<SaleOff> findById(long id);
}
