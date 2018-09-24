package vn.hcmute.projectmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.hcmute.projectmanagement.entity.Role;
import vn.hcmute.projectmanagement.entity.User;
import vn.hcmute.projectmanagement.exception.UserNotFoundException;
import vn.hcmute.projectmanagement.repository.RoleRepository;
import vn.hcmute.projectmanagement.repository.UserRepository;
import vn.hcmute.projectmanagement.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

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

    @Override
    public User registerUser(User user) {
    // register will set role default is ROLE_USER
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByName("ROLE_USER").get();
        Set<Role> roles=new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        return userRepository.save(user);
    }
}
