package com.bookstore.backend.application.controller.order;

import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.backend.application.controller.TestsController;
import com.bookstore.backend.presentation.dto.sale.OrderDTO;
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
    public void orderSave() throws JsonProcessingException, JSONException, UnsupportedEncodingException, Exception {
        saveProductShoppingCart();
        
        OrderDTO dto = new OrderDTO();

        List<Long> idItemList = new ArrayList<>();
        idItemList.add(1l);

        dto.setIdItemList(idItemList);
        
        mockMvc.perform(post(URLbase + "/order")
            .header("Authorization", this.getToken("user", "userPass"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isCreated());
    }
}
