package com.bookstore.backend.application.controller.shoppingCart;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bookstore.backend.application.controller.TestsController;
import com.bookstore.backend.presentation.dto.sale.ItemOrderDTO;
import com.bookstore.backend.presentation.dto.sale.ShoppingCartDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
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
    public void saveShoppingCartSucess() throws JsonProcessingException, JSONException, UnsupportedEncodingException, Exception{        this.saveUser();
        

        ShoppingCartDTO dto = new ShoppingCartDTO();
        
        JSONObject jsonBook = new JSONObject(this.saveBook().getResponse().getContentAsString());

        List<ItemOrderDTO> list = new ArrayList<>();
        list.add(new ItemOrderDTO(0l, 3, null, jsonBook.getLong("id")));

        dto.setItemList(list);
        
        mockMvc.perform(post(URLbase + "/shopping-cart/add")
            .header("Authorization", this.getToken("user", "userPass"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void saveShoppingCartAmountZeroInvalid() throws JsonProcessingException, Exception{
        this.saveUser();

        ShoppingCartDTO dto = new ShoppingCartDTO();
        
        JSONObject jsonBook = new JSONObject(this.saveBook().getResponse().getContentAsString());

        List<ItemOrderDTO> list = new ArrayList<>();
        list.add(new ItemOrderDTO(0l, 0, null, jsonBook.getLong("id")));

        dto.setItemList(list);
        
        mockMvc.perform(post(URLbase + "/shopping-cart/add")
            .header("Authorization", this.getToken("user", "userPass"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isBadRequest());
    }

    @Test
    @Order(3)
    public void removeShoppingCartSucess() throws JsonProcessingException, Exception{
        
        
        ShoppingCartDTO dto = new ShoppingCartDTO();
        
        JSONObject jsonBook = new JSONObject(this.saveBook().getResponse().getContentAsString());
        JSONObject jsonUser = new JSONObject(this.saveUser().getResponse().getContentAsString());

        List<ItemOrderDTO> list = new ArrayList<>();
        list.add(new ItemOrderDTO(0l, 10, null, jsonBook.getLong("id")));

        dto.setItemList(list);
        dto.setIdPerson(jsonUser.getLong("id"));
        
        mockMvc.perform(post(URLbase + "/shopping-cart/remove")
            .header("Authorization", this.getToken("user", "userPass"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void removeShoppingCartError() throws JsonProcessingException, Exception{
        

        ShoppingCartDTO dto = new ShoppingCartDTO();
        
        JSONObject jsonBook = new JSONObject(this.saveBook().getResponse().getContentAsString());
        JSONObject jsonUser = new JSONObject(this.saveUser().getResponse().getContentAsString());

        List<ItemOrderDTO> list = new ArrayList<>();
        list.add(new ItemOrderDTO(0l, 10, null, jsonBook.getLong("id")));

        dto.setItemList(list);
        dto.setIdPerson(jsonUser.getLong("id"));
        
        mockMvc.perform(post(URLbase + "/shopping-cart/remove")
            .header("Authorization", this.getToken("user", "userPass"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk());
    }
}
