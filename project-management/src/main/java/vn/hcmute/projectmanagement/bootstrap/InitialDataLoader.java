package vn.hcmute.projectmanagement.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vn.hcmute.projectmanagement.entity.Privilege;
import vn.hcmute.projectmanagement.entity.Role;
import vn.hcmute.projectmanagement.entity.User;
import vn.hcmute.projectmanagement.repository.PrivilegeRepository;
import vn.hcmute.projectmanagement.repository.RoleRepository;
import vn.hcmute.projectmanagement.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

//@Component
//public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {
//
//    private boolean isAlreadySetup;
//    @Autowired
//    private PrivilegeRepository privilegeRepository;
//    @Autowired
//    private RoleRepository roleRepository;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//
//    @Override
//    @Transactional
//    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        if(isAlreadySetup) return;
//        Privilege privilege_read=createPrivilegeIfNotFound("READ_PRIVILEGE");
//        Privilege privilege_write=createPrivilegeIfNotFound("WRITE_PRIVILEGE");
//        Privilege privilege_create=createPrivilegeIfNotFound("CREATE_PRIVILEGE");
//        Privilege privilege_delete=createPrivilegeIfNotFound("DELETE_PRIVILEGE");
//
//        Set<Privilege> adminPrivileges=new HashSet<>(Arrays.asList(privilege_read,privilege_write,privilege_create,privilege_delete));
//        Role role_admin=createRoleIfNotFound("ROLE_ADMIN",adminPrivileges);
//
//        Set<Privilege> userPrivileges=new HashSet<>(Arrays.asList(privilege_read,privilege_write));
//        Role role_user=createRoleIfNotFound("ROLE_USER",userPrivileges);
//
//        User user=new User();
//        user.setUsername("admin");
//        user.setPassword(passwordEncoder.encode("admin"));
//        user.setStatus(1);
//        user.setRoles(new HashSet<>(Arrays.asList(role_admin,role_user)));
//        userRepository.save(user);
//
//        isAlreadySetup=true;
//    }
//
//    @Transactional
//    Privilege createPrivilegeIfNotFound(String s){
//        Optional<Privilege> privilegeOptional=privilegeRepository.findByName(s);
//        if(!privilegeOptional.isPresent()){
//            Privilege privilege=new Privilege();
//            privilege.setName(s);
//            privilegeRepository.save(privilege);
//            return privilege;
//        }
//        return privilegeOptional.get();
//    }
//
//    @Transactional
//    Role createRoleIfNotFound(String s, Set<Privilege> privileges){
//        Optional<Role> roleOptional=roleRepository.findByName(s);
//        if(!roleOptional.isPresent()){
//            Role role=new Role();
//            role.setName(s);
//            role.setPrivileges(privileges);
//            roleRepository.save(role);
//            return role;
//        }
//        return roleOptional.get();
//    }
//}
