package com.bookstore.backend.infrastructure.utils;

import java.util.Optional;

//import com.bookstore.backend.domain.model.user.AdminModel;
import com.bookstore.backend.domain.model.user.Perfil;
import com.bookstore.backend.domain.model.user.PersonModel;
import com.bookstore.backend.infrastructure.persistence.repository.person.PersonRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminVerify {
    
    @Autowired
    private PersonRepository personRepository;

    public boolean isAdmin(String username) {
        Optional<PersonModel> adminOp = personRepository.findByUsername(username);

        if(adminOp.isPresent()) {
        	Perfil admin = new Perfil("ADMIN");
        	if(adminOp.get().getPerfils().contains(admin)) {
        		return true;	
        	}
        }
        return false;
    }

}
