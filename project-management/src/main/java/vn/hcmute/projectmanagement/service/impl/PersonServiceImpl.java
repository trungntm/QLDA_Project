package vn.hcmute.projectmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hcmute.projectmanagement.entity.Person;
import vn.hcmute.projectmanagement.exception.NotFoundException;
import vn.hcmute.projectmanagement.repository.PersonRepository;
import vn.hcmute.projectmanagement.service.PersonService;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person findPersonById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        if(!person.isPresent())
            throw new NotFoundException("Not Found Person");
        return person.get();
    }
}
