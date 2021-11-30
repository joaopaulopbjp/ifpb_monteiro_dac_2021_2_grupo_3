package com.bookstore.backend.IntegrationsTests.controller.category;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bookstore.backend.TestsController;
import com.bookstore.backend.presentation.dto.category.CategoryDTO;
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
    public void saveAdminCategorySucess() throws JsonProcessingException, Exception {
        CategoryDTO dto = new CategoryDTO(0l, "horror");
        
        mockMvc.perform(post(URLbase + "/category")
            .header("Authorization", this.getToken("admin", "admin"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    public void saveUserCategoryErrorUnauthorized() throws JsonProcessingException, Exception {
        this.saveUser();
        CategoryDTO dto = new CategoryDTO(0l, "action");
        
        mockMvc.perform(post(URLbase + "/category")
            .header("Authorization", this.getToken("user", "userPass"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isUnauthorized());
    }

    @Test
    @Order(3)
    public void findAllCategorySucess() throws JsonProcessingException, Exception {
        mockMvc.perform(get(URLbase + "/category/find-all")
            .header("Authorization", this.getToken("user", "userPass"))
            .contentType("application/json")
            .content(""))
            .andExpect(status().isFound());
    }

    @Test
    @Order(4)
    public void findAllCategoryErroForbbiden() throws JsonProcessingException, Exception {
        mockMvc.perform(get(URLbase + "/category/find-all")
            .contentType("application/json")
            .content(""))
            .andExpect(status().isForbidden());
    }
}
