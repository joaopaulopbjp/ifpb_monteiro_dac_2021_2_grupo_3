package com.bookstore.backend.database.person;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.bookstore.backend.domain.model.sale.ShoppingCartModel;
import com.bookstore.backend.domain.model.user.UserModel;
import com.bookstore.backend.infrastructure.persistence.service.person.UserRepositoryService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;


@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Test
    public void variable(){
        // correct
        UserModel user = new UserModel(0l,
        "Thauan",
        "thauan@email.com",
        "123",
        null,
        null, 
        null,
        new ShoppingCartModel(0l, null, null),
        null);
        
        UserModel userSaved = userRepositoryService.getInstance().save(user);
        assertTrue(userSaved != null);

		// incorrect
        user.setUsername("");

        assertThrows(DataIntegrityViolationException.class, () -> userRepositoryService.getInstance().save(user));
    }
}
