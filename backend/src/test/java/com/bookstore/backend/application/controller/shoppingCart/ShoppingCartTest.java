package com.bookstore.backend.application.controller.shoppingCart;

import java.util.ArrayList;
import java.util.List;

import com.bookstore.backend.application.controller.TestsController;
import com.bookstore.backend.domain.model.sale.ShoppingCartModel;
import com.bookstore.backend.presentation.dto.sale.ItemOrderDTO;
import com.bookstore.backend.presentation.dto.sale.ShoppingCartDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class ShoppingCartTest extends TestsController{
    
    @Test
    @Order(1)
    public void saveShoppingCart(){
        mockMvc.perform(post(URLbase + "/category")
            .header("Authorization", this.getToken("admin", "admin"))
            .contentType("application/json")
            .content(""itemList":[{"amount":3,"idProduct":1}]"));
            .andExpect(status().isCreated());
    }
}
