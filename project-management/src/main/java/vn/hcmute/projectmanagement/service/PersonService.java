package vn.hcmute.projectmanagement.service;

import vn.hcmute.projectmanagement.entity.Person;

public interface PersonService {
    Person findPersonById(Long id);
	Person createPerson(Person person,long uid);
	Person updateProfile(Person person);
}
