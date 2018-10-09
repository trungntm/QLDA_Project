package vn.hcmute.projectmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hcmute.projectmanagement.entity.Person;
import vn.hcmute.projectmanagement.repository.PersonRepository;
import vn.hcmute.projectmanagement.repository.UserRepository;
import vn.hcmute.projectmanagement.service.PersonService;

import java.util.Date;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Person createPerson(Person person,long uid) {
        person.setDateCreated(new Date());
        person.setUser(userRepository.findById(uid).get());
        person.setUserCreated(person.getUser().getUsername());
        return personRepository.save(person);
    }
}
