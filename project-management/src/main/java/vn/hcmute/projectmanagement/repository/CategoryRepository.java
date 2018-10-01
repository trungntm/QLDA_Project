package vn.hcmute.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import vn.hcmute.projectmanagement.entity.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findCategoriesById(long id);
}
