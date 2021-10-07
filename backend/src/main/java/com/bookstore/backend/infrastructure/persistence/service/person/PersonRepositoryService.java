package com.bookstore.backend.infrastructure.persistence.service.person;

import com.bookstore.backend.infrastructure.persistence.repository.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonRepositoryService {
    @Autowired
    private PersonRepository personRepository;

    public PersonRepository getInstance() {
        return personRepository;
    }
}
