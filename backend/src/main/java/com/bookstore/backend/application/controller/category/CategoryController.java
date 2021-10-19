package com.bookstore.backend.application.controller.category;

import com.bookstore.backend.application.service.category.CategoryService;
import com.bookstore.backend.domain.model.category.CategoryModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.modelmapper.ModelMapperService;
import com.bookstore.backend.presentation.dto.category.CategoryDTO;
import com.bookstore.backend.presentation.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
@CrossOrigin
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CategoryDTO dto){
        try {
            CategoryModel category = (CategoryModel) ModelMapperService.convertToModel(dto, CategoryModel.class);
            category = categoryService.save(category);
            return ResponseEntity.status(HttpStatus.OK).body(category);
        } catch (IllegalArgumentException e) {
            Response response = new Response(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody CategoryDTO dto){
        Response response;
        try {
            categoryService.delete(dto.getId());
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (IllegalArgumentException e) {
            response = new Response(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (NotFoundException e) {
            response = new Response(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody CategoryDTO dto){
        CategoryModel category = (CategoryModel) ModelMapperService.convertToModel(dto, CategoryModel.class);
        try {
            category = categoryService.update(category);
            return ResponseEntity.status(HttpStatus.OK).body(category);
        } catch (NotFoundException e) {
            Response response = new Response(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}
