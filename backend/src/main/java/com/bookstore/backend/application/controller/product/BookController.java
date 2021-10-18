package com.bookstore.backend.application.controller.product;

import java.util.List;

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
import org.springframework.web.bind.annotation.PathVariable;
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
        try {
            List<BookModel> bookList = bookServices.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(bookList);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        }
    }

    @GetMapping("/{page}")
    public ResponseEntity<?> findByCategoryList(@PathVariable("page") int page, @RequestBody BookDTO dto) {
        try {
            List<BookModel> bookList = bookServices.findByCategoryIdList(page, dto.getIdCategoryList());
            return ResponseEntity.status(HttpStatus.OK).body(bookList);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        }
    }
    
    @GetMapping("/id")
    public ResponseEntity<?> findById(@RequestBody BookDTO dto) {
        try {
            BookModel book = bookServices.findById(dto.getId());
            return ResponseEntity.status(HttpStatus.OK).body(book);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        }
    }

    @GetMapping("/title")
    public ResponseEntity<?> findByTitle(@RequestBody BookDTO dto) {
        try {
            List<BookModel> book = bookServices.findByTitle(dto.getTitle());
            return ResponseEntity.status(HttpStatus.OK).body(book);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        }
    }

    @GetMapping("/cheapests")
    public ResponseEntity<?> cheapests() {
        try {
            List<BookModel> bookList = bookServices.findFiveCheapests();
            return ResponseEntity.status(HttpStatus.OK).body(bookList);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        }
    }
}
