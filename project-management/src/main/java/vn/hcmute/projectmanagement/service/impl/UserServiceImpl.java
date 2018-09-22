package vn.hcmute.projectmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hcmute.projectmanagement.entity.User;
import vn.hcmute.projectmanagement.exception.UserNotFoundException;
import vn.hcmute.projectmanagement.repository.UserRepository;
import vn.hcmute.projectmanagement.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User retrieveById(long id) {
        Optional<User> userOptional=userRepository.findById(id);
        if(!userOptional.isPresent())
            throw new UserNotFoundException("user not found");
        return userOptional.get();
    }

    @Override
    public List<User> retrieveAllUsers() {
        List<User> users=userRepository.findAll();
        if(users.isEmpty())
            throw new UserNotFoundException("user not found");
        return users;
    }


}
