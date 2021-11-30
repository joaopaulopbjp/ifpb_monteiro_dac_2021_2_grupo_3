package com.bookstore.backend.TestsMocks.controller.order;

import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import com.bookstore.backend.TestsController;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class OrderTest extends TestsController {
    
    @Test
    @Order(1)
    public void orderSaveSucess() throws JsonProcessingException, JSONException, UnsupportedEncodingException, Exception {
        this.saveProductShoppingCart();
        
        mockMvc.perform(post(URLbase + "/order/save")
            .header("Authorization", this.getToken("user", "userPass")))
            .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    public void orderSaveErroAdmin() throws JsonProcessingException, JSONException, UnsupportedEncodingException, Exception {
        mockMvc.perform(post(URLbase + "/order/save")
            .header("Authorization", this.getToken("admin", "admin")))
            .andExpect(status().isUnauthorized());
    }

    @Test
    @Order(3)
    public void orderSaveErroShoppingEmpty() throws JsonProcessingException, JSONException, UnsupportedEncodingException, Exception {
        mockMvc.perform(post(URLbase + "/order/save")
            .header("Authorization", this.getToken("user", "userPass")))
            .andExpect(status().isBadRequest());
    }
}
