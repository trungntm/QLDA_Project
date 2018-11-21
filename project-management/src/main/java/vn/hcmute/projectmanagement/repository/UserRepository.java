package vn.hcmute.projectmanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.hcmute.projectmanagement.entity.User;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Long>  {
    Optional<User> findByUsername(String s);
    Optional<User> findById(long id);
    @Query("SELECT u FROM users u WHERE u.username LIKE CONCAT('%',:username,'%')")
    Page<User> retrieveByUsernamePagingAndSorting(@Param("username") String username, Pageable pageable);
    @Query("SELECT u FROM users u")
    Page<User> retrieveAllUsersPagingAndSorting(Pageable pageable);
}
