package com.bookstore.backend.application.controller.category;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bookstore.backend.configs.TestsController;
import com.bookstore.backend.presentation.dto.category.CategoryDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

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
    public void saveCategory() throws JsonProcessingException, Exception{
        CategoryDTO dto = new CategoryDTO(null, "doidera");
        
        mockMvc.perform(post(URLbase + "/category")
            // .header("Authorization", this.getToken())
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isCreated());
    }
    
}
