package com.bookstore.backend.application.controller.shoppingCart;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bookstore.backend.application.controller.TestsController;
import com.bookstore.backend.presentation.dto.sale.ItemOrderDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONException;
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
    public void saveShoppingCart() throws JsonProcessingException, JSONException, UnsupportedEncodingException, Exception{
        this.saveUser();
        List<ItemOrderDTO> list = new ArrayList<>();
        list.add(new ItemOrderDTO(0l, 3, null, 1l));
        
        mockMvc.perform(post(URLbase + "/shopping-cart/add")
            .header("Authorization", this.getToken("user", "userPass"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(list)))
            .andExpect(status().isCreated());
    }
}
