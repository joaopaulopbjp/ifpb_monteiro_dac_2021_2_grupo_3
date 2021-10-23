package com.bookstore.backend.database.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.bookstore.backend.domain.model.address.AddressModel;
import com.bookstore.backend.domain.model.user.UserModel;
import com.bookstore.backend.infrastructure.persistence.service.person.UserRepositoryService;

import org.apache.tomcat.jni.Address;
import org.junit.Test;

import junit.framework.*;

public class UserTest extends TestCase{

    private UserRepositoryService userRepositoryService;

    @Test
    void nameTest(){
        UserModel user = new UserModel(0l, null, "thauan@gmail.com", 
        "123", null, null, null, null, null);
        
        assertThrows(expectedType, executable, messageSupplier);
    }

    @Test
    public void testeAssertNotSame(){
    
    }
}
