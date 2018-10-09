package vn.hcmute.projectmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.hcmute.projectmanagement.entity.Person;
import vn.hcmute.projectmanagement.entity.Role;
import vn.hcmute.projectmanagement.entity.User;
import vn.hcmute.projectmanagement.exception.NotFoundException;
import vn.hcmute.projectmanagement.repository.PersonRepository;
import vn.hcmute.projectmanagement.repository.PrivilegeRepository;
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

    @Autowired
    private PersonRepository personRepository;
    @Override
    public User retrieveById(long id) {
        Optional<User> userOptional=userRepository.findById(id);
        if(!userOptional.isPresent())
            throw new NotFoundException("user not found");
        return userOptional.get();
    }

    @Override
    public List<User> retrieveAllUsers() {
        List<User> users=userRepository.findAll();
        if(users.isEmpty())
            throw new NotFoundException("user not found");
        return users;
    }

    @Override
    public User registerUser(User user) {
    // register will set role default is ROLE_USER
        user.setStatus(1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByName("ROLE_USER").get();
        Set<Role> roles=new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public User updateRegisterUser(User user,long pid) {
        user.setPerson(personRepository.findById(pid).get());
        return userRepository.save(user);
    }

    @Override
    public User updateRoleForUser(long uid, long rid) {
        Optional<User> userOptional =userRepository.findById(uid);
        if(!userOptional.isPresent())
            throw new NotFoundException("User not found. Could not update role for this user");
        Optional<Role> roleOptional=roleRepository.findById(rid);
        if(!roleOptional.isPresent())
            throw new NotFoundException("Role not found. Please check again");
        User user=userOptional.get();
        Role role=roleOptional.get();
        Set<Role> userRoles=user.getRoles();
        if(userRoles.contains(role))
            userRoles.remove(role);
        else
            userRoles.add(role);
        user.setRoles(userRoles);
        return userRepository.save(user);
    }
    @Override
    public User updateUserStatus(long uid){
        Optional<User> userOptional=userRepository.findById(uid);
        if(!userOptional.isPresent())
            throw new NotFoundException("User not found. Could not update role for this user");
        User user=userOptional.get();
        if(user.getStatus()==0)
            user.setStatus(1);
        else user.setStatus(0);
        return userRepository.save(user);
    }

    @Override
    public User retrieveUserByUsername(String username){
        Optional<User> userOptional=userRepository.findByUsername(username);
        if(!userOptional.isPresent())
            throw new NotFoundException("User not found. Could not update role for this user");
        return userOptional.get();
    }

    @Override
    public User retrieveUserByIdOrUsername(long id, String username){
        Optional<User> userOptional=userRepository.findByIdOrUsername(id,username);
        if(!userOptional.isPresent())
            throw new NotFoundException("User not found. Could not update role for this user");
        return userOptional.get();
    }
}
