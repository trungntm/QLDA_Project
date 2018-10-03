package vn.hcmute.projectmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.hcmute.projectmanagement.api.v1.data.DataReturnList;
import vn.hcmute.projectmanagement.api.v1.data.DataReturnOne;
import vn.hcmute.projectmanagement.api.v1.dto.UserDto;
import vn.hcmute.projectmanagement.api.v1.mapper.UserMapper;
import vn.hcmute.projectmanagement.entity.User;
import vn.hcmute.projectmanagement.exception.NotFoundException;
import vn.hcmute.projectmanagement.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/admin/")
public class AdminController {
    @Autowired
    private UserService userService;


    @Autowired
    private UserMapper userMapper;
    @RequestMapping(value = "", //
            method = RequestMethod.GET)
    public String admin(){
        return "permit admin";
    }

    // retrieve user by id // chua xong
    @GetMapping("/users/search")
    public DataReturnOne<UserDto> retrieveUserById(@RequestParam(required = false) long id,@RequestParam(required = false) String username){
        DataReturnOne<UserDto> dataReturnOne=new DataReturnOne<>();
        dataReturnOne.setSuccess("true");
        dataReturnOne.setMessage("success");
        dataReturnOne.setData(userMapper.userToUserDto(userService.retrieveUserByIdOrUsername(id,username)));
        return dataReturnOne;
    }


//    @GetMapping("/users/{username}")
//    public DataReturnOne<UserDto> retrieveUserByUsername(@PathVariable String username){
//        DataReturnOne<UserDto> dataReturnOne=new DataReturnOne<>();
//        dataReturnOne.setSuccess("true");
//        dataReturnOne.setMessage("success");
//        dataReturnOne.setData(userMapper.userToUserDto(userService.retrieveUserByUsername(username)));
//        return dataReturnOne;
//    }
    // retrieve all users
    @GetMapping("/users")
    public DataReturnList<UserDto> retrieveAllUsers(@RequestHeader("Authorization") String header){
        DataReturnList<UserDto> dataReturnList=new DataReturnList<>();
        List<UserDto> userDtos=userService.retrieveAllUsers()
                                .stream()
                                .map(userMapper::userToUserDto)
                                .collect(Collectors.toList());
        List<UserDto> users=new ArrayList<>();
        if(userDtos.isEmpty()){
            throw new NotFoundException("User not found!");
        }
        dataReturnList.setMessage("success !");
        userDtos.forEach(user->users.add(user));
        dataReturnList.setData(users);
        System.out.println("response token : "+header);
        return dataReturnList;
    }
    // update role for user
    @PutMapping("/users/role/{uid}/{rid}")
    public DataReturnOne<UserDto> updateUserRole(@PathVariable long uid, @PathVariable long rid){
        DataReturnOne<UserDto> dataReturnOne=new DataReturnOne<>();
        dataReturnOne.setMessage("success");
        dataReturnOne.setSuccess("true");
        dataReturnOne.setData(userMapper.userToUserDto(userService.updateRoleForUser(uid,rid)));
        return dataReturnOne;
    }

    // update status for user
    @PutMapping("/users/status/{uid}")
    public DataReturnOne<UserDto> updateUserStatus(@PathVariable long uid){
        DataReturnOne<UserDto> dataReturnOne=new DataReturnOne<>();
        dataReturnOne.setSuccess("true");
        dataReturnOne.setMessage("success");
        dataReturnOne.setData(userMapper.userToUserDto(userService.updateUserStatus(uid)));
        return dataReturnOne;
    }


}
