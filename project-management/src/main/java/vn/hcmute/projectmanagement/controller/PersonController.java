package vn.hcmute.projectmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.hcmute.projectmanagement.api.v1.data.DataReturnOne;
import vn.hcmute.projectmanagement.api.v1.dto.PersonDto;
import vn.hcmute.projectmanagement.api.v1.mapper.PersonMapper;
import vn.hcmute.projectmanagement.entity.Person;
import vn.hcmute.projectmanagement.exception.NotFoundException;
import vn.hcmute.projectmanagement.service.PersonService;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {
    @Autowired
    private PersonService personService;

    @Autowired
    private PersonMapper personMapper;

    @PutMapping("/update/profile")
    public DataReturnOne<PersonDto> updateProfile(@RequestBody Person person){
        DataReturnOne<PersonDto> personDtoDataReturnOne=new DataReturnOne<>();
        PersonDto personDto;
        try {
            personDto = personMapper.personToPersonDto(personService.updateProfile(person));
            personDtoDataReturnOne.setSuccess("true");
            personDtoDataReturnOne.setMessage("success");
            personDtoDataReturnOne.setData(personDto);
        }catch (NotFoundException ex){
            personDtoDataReturnOne.setSuccess("false");
            personDtoDataReturnOne.setMessage("error");
            personDtoDataReturnOne.setData(null);
        }
        return personDtoDataReturnOne;
    }

    @PutMapping("/update/avatar")
    public DataReturnOne<PersonDto> uploadAvatar(@RequestParam("avatar") MultipartFile file){
        System.out.println("image");
        if(file.isEmpty()){
            System.out.println("image null");
            return null;
        }
        System.out.println("original "+file.getOriginalFilename());
        return null;
    }
}
