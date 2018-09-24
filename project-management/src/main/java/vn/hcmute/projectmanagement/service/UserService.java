package vn.hcmute.projectmanagement.service;

import vn.hcmute.projectmanagement.entity.User;

import java.util.List;

public interface UserService {
    User retrieveById(long id);
    List<User> retrieveAllUsers();
    User registerUser(User user);
}
