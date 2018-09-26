package vn.hcmute.projectmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.hcmute.projectmanagement.entity.Privilege;
import vn.hcmute.projectmanagement.entity.Role;
import vn.hcmute.projectmanagement.entity.User;
import vn.hcmute.projectmanagement.repository.UserRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> userOptional=userRepository.findByUsername(s);
        if(!userOptional.isPresent())
            throw new UsernameNotFoundException("User name not found in function loadUserByUsername. Please check again! ");
        User user=userOptional.get();
        System.out.println("user login : "+user);
        Set<GrantedAuthority> grantedAuthorities=new HashSet<>();
        user.getRoles().forEach(role->grantedAuthorities.add(new SimpleGrantedAuthority(role.getName())));
        // add privileges into roles
        //
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getAuthorities(user.getRoles()));
//        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),grantedAuthorities);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            Set<Role> roles) {
        Set<GrantedAuthority> authorities
                = new HashSet<>();
        for (Role role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            role.getPrivileges().stream()
                    .map(p -> new SimpleGrantedAuthority(p.getName()))
                    .forEach(authorities::add);
        }
        return authorities;
    }
    private Set<GrantedAuthority> getGrantedAuthorities(Set<String> privileges) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
    private Set<String> getPrivileges(Set<Role> roles) {

        Set<String> privileges = new HashSet<>();
        Set<Privilege> collection = new HashSet<>();

        roles.forEach(role -> collection.addAll(role.getPrivileges()));

        collection.forEach(privilege -> privileges.add(privilege.getName()));
        return privileges;
    }
}
