package com.bookstore.backend.IntegrationsTests.controller.shoppingCart;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bookstore.backend.TestsController;
import com.bookstore.backend.presentation.dto.product.BookDTO;
import com.bookstore.backend.presentation.dto.sale.ItemOrderDTO;
import com.bookstore.backend.presentation.dto.sale.ShoppingCartDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONArray;
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
import org.springframework.test.web.servlet.MvcResult;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class ShoppingCartTest extends TestsController{
    
    @Test
    @Order(1)
    public void addShoppingCartSucess() throws JsonProcessingException, JSONException, UnsupportedEncodingException, Exception{        
        this.saveUser();
        
        ShoppingCartDTO dto = new ShoppingCartDTO();
        
        JSONObject jsonBook = new JSONObject(this.saveBook().getResponse().getContentAsString());

        List<ItemOrderDTO> list = new ArrayList<>();
        list.add(new ItemOrderDTO(0l, 10, null, jsonBook.getLong("id")));

        dto.setItemList(list);
        
        mockMvc.perform(post(URLbase + "/shopping-cart/add")
            .header("Authorization", this.getToken("user", "userPass"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk());

        MvcResult result = mockMvc.perform(get(URLbase + "/shopping-cart/find-shoppingcart")
            .header("Authorization", this.getToken("user", "userPass")))
            .andExpect(status().isOk()).andReturn();


        JSONObject cart = new JSONObject(result.getResponse().getContentAsString());
        JSONObject itemList = new JSONArray(cart.getJSONArray("itemList")).getJSONObject(0);
        assertEquals(list.get(0).getAmount(), (Integer)itemList.getInt("amount"));
    }

    @Test
    @Order(2)
    public void addShoppingCartDois() throws JsonProcessingException, JSONException, UnsupportedEncodingException, Exception{

        BookDTO dto = new BookDTO();
        dto.setTitle("Em busca da batata perdida");

        MvcResult result = mockMvc.perform(get(URLbase + "/book/find/find-by-title")
            .header("Authorization", this.getToken("admin", "admin"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk())
            .andReturn();

        ShoppingCartDTO dto1 = new ShoppingCartDTO();

        List<ItemOrderDTO> list = new ArrayList<>();

        list.add(new ItemOrderDTO(0l, 5, null, 1l));

        dto1.setItemList(list);
        
        mockMvc.perform(post(URLbase + "/shopping-cart/add")
            .header("Authorization", this.getToken("user", "userPass"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto1)))
            .andExpect(status().isBadRequest());

    }

    @Test
    @Order(3)
    public void addShoppingCartAmountZeroInvalid() throws JsonProcessingException, Exception{

        MvcResult result = mockMvc.perform(get(URLbase + "/shopping-cart/find-shoppingcart")
            .header("Authorization", this.getToken("user", "userPass")))
            .andExpect(status().isOk()).andReturn();

        ShoppingCartDTO dto = new ShoppingCartDTO();

        List<ItemOrderDTO> list = new ArrayList<>();
        JSONObject item = new JSONObject(result.getResponse().getContentAsString()).getJSONArray("itemList").getJSONObject(0);
        list.add(new ItemOrderDTO(0l, 0, null, (Long)item.getLong("id")));

        dto.setItemList(list);
        
        mockMvc.perform(post(URLbase + "/shopping-cart/add")
            .header("Authorization", this.getToken("user", "userPass"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isBadRequest());

    }
    
    @Test
    @Order(4)
    public void addShoppingCartAmountAdminUnauthorized() throws JsonProcessingException, Exception{

        MvcResult result = mockMvc.perform(get(URLbase + "/shopping-cart/find-shoppingcart")
            .header("Authorization", this.getToken("user", "userPass")))
            .andExpect(status().isOk()).andReturn();

        ShoppingCartDTO dto = new ShoppingCartDTO();
        
        List<ItemOrderDTO> list = new ArrayList<>();
        JSONObject item = new JSONObject(result.getResponse().getContentAsString()).getJSONArray("itemList").getJSONObject(0);
        list.add(new ItemOrderDTO(0l, 1000, null, (Long)item.getLong("id")));

        dto.setItemList(list);
        
        mockMvc.perform(post(URLbase + "/shopping-cart/add")
            .header("Authorization", this.getToken("admin", "admin"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isUnauthorized());
    }

    @Test
    @Order(5)
    public void removeShoppingCartSucess() throws JsonProcessingException, Exception{

        MvcResult result = mockMvc.perform(get(URLbase + "/shopping-cart/find-shoppingcart")
            .header("Authorization", this.getToken("user", "userPass")))
            .andExpect(status().isOk()).andReturn();
        
        ShoppingCartDTO dto = new ShoppingCartDTO();
        
        List<ItemOrderDTO> list = new ArrayList<>();
        ItemOrderDTO item = new ItemOrderDTO();

        list.add(item);
        item.setId(new JSONObject(result.getResponse().getContentAsString()).getJSONArray("itemList").getJSONObject(0).getLong("id"));
        dto.setItemList(list);
        
        mockMvc.perform(post(URLbase + "/shopping-cart/remove")
            .header("Authorization", this.getToken("user", "userPass"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk());
    }

    @Test
    @Order(6)
    public void removeShoppingCartAdminError() throws JsonProcessingException, Exception{

        ShoppingCartDTO dto = new ShoppingCartDTO();
        
        List<ItemOrderDTO> list = new ArrayList<>();
        ItemOrderDTO item = new ItemOrderDTO();

        
        list.add(item);
        item.setId(1l);
        dto.setItemList(list);
        
        mockMvc.perform(post(URLbase + "/shopping-cart/remove")
            .header("Authorization", this.getToken("admin", "admin"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isUnauthorized());
    }

    @Test
    @Order(7)
    public void removeShoppingCartError() throws JsonProcessingException, JSONException, UnsupportedEncodingException, Exception {
        
        ShoppingCartDTO dto = new ShoppingCartDTO();
        
        List<ItemOrderDTO> list = new ArrayList<>();
        ItemOrderDTO item = new ItemOrderDTO();

        item.setId(1l);
        list.add(item);
        dto.setItemList(list);
        
        mockMvc.perform(post(URLbase + "/shopping-cart/remove")
            .header("Authorization", this.getToken("user", "userPass"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isNotFound());
    }

    
}
