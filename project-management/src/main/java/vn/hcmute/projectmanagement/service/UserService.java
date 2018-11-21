package vn.hcmute.projectmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.hcmute.projectmanagement.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User retrieveById(long id);
    Page<User> retrieveAllUsers(Pageable pageable);
    User registerUser(User user);
    User updateRegisterUser(User user,long pid);
    User updateRoleForUser(long uid,long rid);
    User updateUserStatus(long id);
    User retrieveUserByUsername(String username);
    Page<User> retrieveByUsernamePagingAndSorting(Optional<String> username, Optional<Integer> page, Optional<Integer> size, Optional<String> sortBy);
    Page<User> retrieveAllUserPagingAndSorting(Optional<Integer> page, Optional<Integer> size, Optional<String> sortBy);
}
