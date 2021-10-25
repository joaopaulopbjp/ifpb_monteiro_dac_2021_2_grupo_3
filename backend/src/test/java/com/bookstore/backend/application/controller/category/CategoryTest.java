package com.bookstore.backend.application.controller.category;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bookstore.backend.configs.TestsController;
import com.bookstore.backend.domain.model.category.CategoryModel;
import com.bookstore.backend.infrastructure.persistence.service.category.CategoryRepositoryService;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CategoryController.class)
public class CategoryTest extends TestsController {

    @MockBean
    private CategoryRepositoryService categoryRepositoryService;

    @Test
    public void saveCategory() throws JsonProcessingException, Exception{
        CategoryModel category = new CategoryModel(0l,"teste");
        when(categoryRepositoryService.getInstance().save(category)).thenReturn(category);
        mockMvc.perform(post(URLbase + "/category")
            // .header("Authorization", this.getToken())
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(category)))
            .andExpect(status().isCreated());
    }
    
}
