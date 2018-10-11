package vn.hcmute.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hcmute.projectmanagement.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String s);
    Optional<User> findById(long id);
    Optional<User> findByIdOrUsername(long id,String username);
}
