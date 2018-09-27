package vn.hcmute.projectmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import vn.hcmute.projectmanagement.api.v1.data.DataReturnList;
import vn.hcmute.projectmanagement.api.v1.dto.UserDto;
import vn.hcmute.projectmanagement.api.v1.mapper.UserMapper;
import vn.hcmute.projectmanagement.entity.User;
import vn.hcmute.projectmanagement.exception.UserNotFoundException;
import vn.hcmute.projectmanagement.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")

public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;
    @RequestMapping(value = "/admin", //
            method = RequestMethod.GET)
    public String admin(){
        return "permit admin";
    }

    @GetMapping("/admin/users/{id}")
    public User retrieveUserById(@PathVariable long id){
        return userService.retrieveById(id);
    }

    @GetMapping("/admin/users")
    public DataReturnList<UserDto> retrieveAllUsers(@RequestHeader("Authorization") String header){
        DataReturnList<UserDto> dataReturnList=new DataReturnList<>();
        List<UserDto> userDtos=userService.retrieveAllUsers()
                                .stream()
                                .map(userMapper::userToUserDto)
                                .collect(Collectors.toList());
        List<UserDto> users=new ArrayList<>();
        if(userDtos.isEmpty()){
            throw new UserNotFoundException("User not found!");
        }
        dataReturnList.setMessage("success !");
        userDtos.forEach(user->users.add(user));
        dataReturnList.setData(users);
//        res.getHeader("Authorization");
//        HttpHeaders headers=new HttpHeaders();
//        headers.add("Authorization",header);
        System.out.println("response token : "+header);
        return dataReturnList;
    }
}
