package com.bookstore.backend.application.services.person.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.bookstore.backend.application.service.person.UserService;
import com.bookstore.backend.domain.model.user.UserModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.person.UserRepositoryService;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UserTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Test
    @Order(1)
    public void saveSucess() {
        UserModel userModel = new UserModel(0l, "Thauan", "thauan@email.com", "12345", null, null,
        null, null, null);
        
        List<UserModel> userList = userRepositoryService.getInstance().findAll();
        
        assertEquals(userList.size()+1, userService.save(userModel).getId());
    }
    
    //username precisa de no minimo 5 caracteres
    @Test
    @Order(2)
    public void saveUsernameError() {
        UserModel userModel = new UserModel(0l, "Th", "thauan@email.com", "12345", null, null,
        null, null, null);
        
        assertThrows(IllegalArgumentException.class, () -> userService.save(userModel));

        userModel.setUsername("Thauan");
        userModel.setEmail("thauan@email");
        assertThrows(IllegalArgumentException.class, () -> userService.save(userModel));
        
        userModel.setEmail("thauan@email.com");
        userModel.setPassword("123");
        assertThrows(IllegalArgumentException.class, () -> userService.save(userModel));
    }
    
    @Test
    @Order(2)
    public void findByEmailSucess() throws NotFoundException {
        UserModel user = userService.findByEmail("thauan@email.com");
        
        assertNotNull(user);
    }
    
    @Test
    @Order(3)
    public void findByEmailError() throws NotFoundException {
        assertThrows(NotFoundException.class, () -> userService.findByEmail("naoExiste@email.com"));
    }

    @Test
    @Order(4)
    public void findAllSucess() {
        List<UserModel> userList = userRepositoryService.getInstance().findAll();

        assertFalse(userList.isEmpty());
    }

    @Test
    @Order(5)
    public void deleteSucess() throws NotFoundException {
        List<UserModel> userModel = userRepositoryService.getInstance().findAll();
        userService.delete(userModel.stream().findFirst().get().getId());
        
        assertEquals(userModel.size()-1, userRepositoryService.getInstance().findAll().size());
    }

    @Test
    @Order(6)
    public void findAllError() {
        List<UserModel> userList = userRepositoryService.getInstance().findAll();

        assertTrue(userList.isEmpty());
    }

    @Test
    @Order(7)
    public void deleteError() {
        assertThrows(NotFoundException.class, () -> userService.delete(100000000l));
    }
}
