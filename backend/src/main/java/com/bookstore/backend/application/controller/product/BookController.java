package com.bookstore.backend.application.controller.product;

import com.bookstore.backend.application.service.product.BookService;
import com.bookstore.backend.domain.model.product.BookModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.modelmapper.ModelMapperService;
import com.bookstore.backend.presentation.dto.product.BookDTO;
import com.bookstore.backend.presentation.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping
    public ResponseEntity<?> save(@RequestBody BookDTO dto) {
        BookModel book = (BookModel) ModelMapperService.convertToModel(dto, BookModel.class);
        try {
            BookModel booksaved = bookServices.save(book, dto.getIdCategoryList(), dto.getIdSaller(), dto.getIdCompany(), dto.getIdAuthorList());
            
            dto = (BookDTO) ModelMapperService.convertToDTO(booksaved, dto.getClass());
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return null;
    }
    
}
