package vn.hcmute.projectmanagement.service;

import vn.hcmute.projectmanagement.entity.Person;

public interface PersonService {
    Person createPerson(Person person,long uid);
}
