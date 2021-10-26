package com.bookstore.backend.application.controller.category;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.bookstore.backend.application.controller.TestsController;
import com.bookstore.backend.presentation.dto.category.CategoryDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.jupiter.api.Disabled;
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
@Disabled
public class CategoryTest extends TestsController {

    @Test
    @Disabled
    public void saveCategory() throws JsonProcessingException, Exception{
        CategoryDTO dto = new CategoryDTO(null, "ho");
        
        mockMvc.perform(post(URLbase + "/category")
            // .header("Authorization", this.getToken())
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1));
    }
}
