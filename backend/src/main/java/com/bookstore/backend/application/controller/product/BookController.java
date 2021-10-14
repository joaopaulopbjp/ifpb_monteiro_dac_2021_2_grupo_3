package com.bookstore.backend.application.controller.product;

import com.bookstore.backend.application.service.product.BookService;
import com.bookstore.backend.presentation.dto.product.BookDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/book")
public class BookController {
    
    private BookService bookServices;

    @PostMapping()
    // @RequestBody BookDTO dto
    public ResponseEntity<?> save() {
        return null;
    }
}
