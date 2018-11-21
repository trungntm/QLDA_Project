package vn.hcmute.projectmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
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
import java.util.Optional;
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

    // retrieve all users
//    @GetMapping("/users")
//    public List<UserDto> retrieveAllUsers(Pageable pageable){
//
//        Page<User> resultPages=userService.retrieveAllUsers(pageable);
////        DataReturnList<UserDto> dataReturnList=new DataReturnList<>();
//        List<UserDto> userDtos;
//        try{
//            userDtos=resultPages.getContent()
//                    .stream()
//                    .map(userMapper::userToUserDto)
//                    .collect(Collectors.toList());
//            if(userDtos.isEmpty()) throw new NotFoundException("Not found user");
////            List<UserDto> users=new ArrayList<>();
////            dataReturnList.setMessage("success !");
////            userDtos.forEach(user->users.add(user));
////            dataReturnList.setData(users);
//        }catch (NotFoundException e){
//            return null;
////            dataReturnList.setData(null);
////            dataReturnList.setMessage(e.getMessage());
////            dataReturnList.setSuccess("false");
//        }
//        return userDtos;
//    }

    @GetMapping("/users/search")
    public List<UserDto> findByUsernamePagingAndSorting(@RequestParam("username") Optional<String> username,
                                                        @RequestParam("page") Optional<Integer> page,
                                                        @RequestParam("size") Optional<Integer> size,
                                                        @RequestParam("sortBy") Optional<String> sortBy){
        Page<User> userPage=userService.retrieveByUsernamePagingAndSorting(username,page,size,sortBy);
        return userPage.getContent().stream().map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/users")
    public List<UserDto> findAllUsserPagingAndSorting(  @RequestParam("page") Optional<Integer> page,
                                                        @RequestParam("size") Optional<Integer> size,
                                                        @RequestParam("sortBy") Optional<String> sortBy){
        Page<User> userPage=userService.retrieveAllUserPagingAndSorting(page,size,sortBy);
        return userPage.getContent().stream().map(userMapper::userToUserDto)
                .collect(Collectors.toList());
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
