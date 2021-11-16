package com.bookstore.backend.application.controller.category;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bookstore.backend.application.controller.TestsController;
import com.bookstore.backend.presentation.dto.category.CategoryDTO;
import com.bookstore.backend.presentation.dto.person.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

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
public class CategoryTest extends TestsController {

    @Test
    @Order(1)
    public void saveAdminCategory() throws JsonProcessingException, Exception {
        CategoryDTO dto = new CategoryDTO(0l, "horror");
        
        mockMvc.perform(post(URLbase + "/category")
            .header("Authorization", this.getToken("admin", "admin"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    public void saveUserCategory() throws JsonProcessingException, Exception {
        saveUser();

        CategoryDTO dto = new CategoryDTO(0l, "action");
        
        mockMvc.perform(post(URLbase + "/category")
            .header("Authorization", this.getToken("user", "userPass"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isUnauthorized());
    }

    private void saveUser() throws JsonProcessingException, Exception {
        UserDTO dto = new UserDTO();
        dto.setUsername("user");
        dto.setPassword("userPass");
        dto.setEmail("user@gmail.com");

        mockMvc.perform(post(URLbase + "/user/save")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isCreated());
    }
}
