
package com.bookstore.backend.application.service.person;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.bookstore.backend.domain.model.user.Perfil;
import com.bookstore.backend.domain.model.user.PersonModel;

import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.person.PerfilRepository;
import com.bookstore.backend.infrastructure.persistence.repository.person.PersonRepository;
import com.bookstore.backend.infrastructure.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private PerfilRepository perfilRepository;


    
    @EventListener(ApplicationReadyEvent.class)
    private void init() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        List<PersonModel> adminlist = personRepository.findAllAdmin();
        if(adminlist.isEmpty()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            Perfil  perfilAdmin = new Perfil("ADMIN");
            perfilRepository.save(perfilAdmin);
            PersonModel admin = new PersonModel(0l, "admin", null, "admin@email.com", "admin", null, null);
            admin.setPerfils(Arrays.asList(perfilAdmin));
            System.out.println(admin.getPassword());
            String encodedPass = encoder.encode(admin.getPassword());
            System.out.println(encodedPass);
            admin.setPassword(encodedPass);
            personRepository.save(admin);
        }
    }


    public PersonModel save(PersonModel user) throws IllegalArgumentException, NoSuchAlgorithmException, UnsupportedEncodingException {

        if(user.getUsername().equals(null))
            throw new IllegalArgumentException();

        if(user.getEmail().equals(null))
            throw new IllegalArgumentException();

        if(user.getPassword().equals(null))
            throw new IllegalArgumentException();

        if(user.getUsername().length() < 3 || user.getUsername().length() > 15) {
            throw new IllegalArgumentException("Username must be between 3 and 15 characters");
        }

        if(user.getPassword().length() < 5) {
            throw new IllegalArgumentException("Password must be at least 5 characters");
        }

        return personRepository.save(user);
    }
    
    public void delete(String username) throws NotFoundException {
        Optional<PersonModel> userOp = personRepository.findByUsername(username);
        personRepository.deleteById(userOp.get().getId());
    }


    public PersonModel findByUsername(String username) throws NotFoundException {
        Optional<PersonModel> user = personRepository.findByUsername(username);
        if(!user.isPresent())
            throw new NotFoundException("User with username " + username + " not found");
        
        return user.get();
    }

}

