package com.bookstore.backend.application.controller.product;

import java.util.ArrayList;
import java.util.List;

import com.bookstore.backend.application.service.product.BookService;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.presentation.dto.product.BookDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    
    @Autowired
    private BookService bookServices;

    @PostMapping()
    // @RequestBody BookDTO dto
    public ResponseEntity<?> save() {
        List<Long> list = new ArrayList<>();
        list.add(1l);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(bookServices.findByCategoryIdList(list));
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
