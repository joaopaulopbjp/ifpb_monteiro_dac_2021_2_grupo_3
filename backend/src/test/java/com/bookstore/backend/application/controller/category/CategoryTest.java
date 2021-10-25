package com.bookstore.backend.application.controller.category;

import static org.mockito.Mockito.when;

import java.util.List;

import com.bookstore.backend.configs.ConfigClass;
import com.bookstore.backend.domain.model.category.CategoryModel;
import com.bookstore.backend.infrastructure.persistence.service.category.CategoryRepositoryService;
import com.bookstore.backend.presentation.dto.category.CategoryDTO;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CategoryController.class)
public class CategoryTest {

    private String URLbase = new ConfigClass().getURLbase();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryRepositoryService categoryRepositoryService;

    @Test
    public void saveCategory(){
        CategoryModel category = new CategoryModel(0l,"teste");
        when(categoryRepositoryService.getInstance().save(category)).thenReturn(category);
        this.mockMvc.perform(post(URLbase+"/category"));
    }
    
}
