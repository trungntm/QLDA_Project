package vn.hcmute.projectmanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.hcmute.projectmanagement.api.v1.dto.UserDto;
import vn.hcmute.projectmanagement.api.v1.mapper.UserMapper;
import vn.hcmute.projectmanagement.entity.User;
import vn.hcmute.projectmanagement.service.UserService;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/users")
    public String user(){
        return "permit user";
    }

    @PostMapping("/register")
    public UserDto registerUser(@RequestBody User user){
        return userMapper.userToUserDto(userService.registerUser(user));

    }
}
