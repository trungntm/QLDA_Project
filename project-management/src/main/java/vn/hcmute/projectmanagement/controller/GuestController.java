package vn.hcmute.projectmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.hcmute.projectmanagement.api.v1.dto.UserDto;
import vn.hcmute.projectmanagement.api.v1.mapper.UserMapper;
import vn.hcmute.projectmanagement.entity.Person;
import vn.hcmute.projectmanagement.entity.User;
import vn.hcmute.projectmanagement.model.RegisterModel;
import vn.hcmute.projectmanagement.service.PersonService;
import vn.hcmute.projectmanagement.service.UserService;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin
public class GuestController {
    @Autowired
    private UserService userService;
    @Autowired
    private PersonService personService;
    @Autowired
    private UserMapper userMapper;
    @PostMapping("/register")
    public UserDto register(@RequestBody RegisterModel registerModel){
        System.out.println(registerModel);
        User user=new User();
        user.setUsername(registerModel.getUsername());
        user.setPassword(registerModel.getPassword());
        Person person=new Person();
        person.setFullName(registerModel.getFullName());
        person.setPhone(registerModel.getPhone());
        person.setSex(registerModel.getSex());
        person.setEmail(registerModel.getEmail());
        person.setDateOfBirth(registerModel.getDob());
        person.setAddress(registerModel.getAddress());

        User userCreated=userService.registerUser(user);
        Person personCreated=personService.createPerson(person,userCreated.getId());
        User userUpdated=userService.updateRegisterUser(userCreated,personCreated.getId());
        return userMapper.userToUserDto(userUpdated);
    }
}
