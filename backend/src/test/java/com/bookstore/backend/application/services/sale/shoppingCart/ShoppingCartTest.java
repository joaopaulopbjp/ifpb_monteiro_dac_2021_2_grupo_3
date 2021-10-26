package com.bookstore.backend.application.services.sale.shoppingCart;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.bookstore.backend.application.service.person.UserService;
import com.bookstore.backend.domain.model.sale.ShoppingCartModel;
import com.bookstore.backend.domain.model.user.UserModel;
import com.bookstore.backend.infrastructure.persistence.service.person.UserRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.sale.ShoppingCartRepositoryService;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ShoppingCartTest {

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartRepositoryService shoppingCartRepositoryService;
    
    @Test
    @Order(1)
    public void addTest() {
        shoppingCartRepositoryService.getInstance().deleteAll();
        userRepositoryService.getInstance().deleteAll();
        
        UserModel userModel = new UserModel(0l, "Thauan", "thauan@email.com", "12345", null, null,
        null, null, null);
        ShoppingCartModel shoppingCartModel = userService.save(userModel)
            .getShoppingCart();

        assertNotNull(shoppingCartModel);
    }
}
