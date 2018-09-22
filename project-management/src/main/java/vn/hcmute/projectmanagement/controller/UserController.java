package vn.hcmute.projectmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import vn.hcmute.projectmanagement.api.v1.dto.UserDto;
import vn.hcmute.projectmanagement.api.v1.mapper.UserMapper;
import vn.hcmute.projectmanagement.entity.User;
import vn.hcmute.projectmanagement.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;
    @GetMapping("/")
    public String main(){
        return "hello all";
    }

    @GetMapping("/users")
    public String user(){
        return "permit user";
    }
    @RequestMapping(value = "/admin", //
            method = RequestMethod.GET)
    public String admin(){
        return "permit admin";
    }
    @GetMapping("/admin/{id}")
    public User retrieveUserById(@PathVariable long id){
        return userService.retrieveById(id);
    }
    @GetMapping("/admin/users")
    public List<UserDto> retrieveAllUsers(){
        return userService.retrieveAllUsers()
                .stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/register/{username}/{password}")
    public UserDto registerUser(@PathVariable String username, @PathVariable String password){
        return userMapper.userToUserDto(userService.registerUser(username,password));

    }
}
