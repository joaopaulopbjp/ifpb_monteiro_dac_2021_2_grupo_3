package com.bookstore.backend.application.controller.product;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.backend.application.service.product.BookService;
import com.bookstore.backend.domain.model.product.BookModel;
import com.bookstore.backend.infrastructure.exception.InvalidValueException;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.modelmapper.ModelMapperService;
import com.bookstore.backend.presentation.dto.product.BookDTO;
import com.bookstore.backend.presentation.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<?> save(@RequestBody BookDTO dto, Principal principal) {
        BookModel book = (BookModel) ModelMapperService.convertToModel(dto, BookModel.class);
        try {
            BookModel booksaved = bookServices.save(book, dto.getIdCategoryList(), dto.getIdCompany(), dto.getIdAuthorList(), principal.getName());
            
            dto = (BookDTO) ModelMapperService.convertToDTO(booksaved, dto.getClass());
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getCause().getCause().getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody BookDTO dto) {
        try {
            bookServices.delete(dto.getId());
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getCause().getCause().getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody BookDTO dto) {
        BookModel book = (BookModel) ModelMapperService.convertToModel(dto, BookModel.class);
        try {
            book = bookServices.update(book);
            dto = (BookDTO) ModelMapperService.convertToDTO(book, dto.getClass());
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getCause().getCause().getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }

    @GetMapping("/find/find-all/{page}")
    public ResponseEntity<?> findAll(@PathVariable("page") int page) {
        try {
            List<BookModel> bookList = bookServices.findAll(page);
            List<BookDTO> dtoList = new ArrayList<>();
            for (BookModel book : bookList) {
                BookDTO dto = (BookDTO) ModelMapperService.convertToDTO(book, BookDTO.class);

                dtoList.add(dto);
            }
            return ResponseEntity.status(HttpStatus.OK).body(dtoList);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getCause().getCause().getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }

    @GetMapping("/find/find-by-category")
    public ResponseEntity<?> findByCategoryList(@RequestBody BookDTO dto) {
        try {
            List<BookModel> bookList = bookServices.findByCategoryId(dto.getIdCategory());
            List<BookDTO> dtoList = new ArrayList<>();
            for (BookModel book : bookList) {
                dto = (BookDTO) ModelMapperService.convertToDTO(book, BookDTO.class);

                dtoList.add(dto);
            }
            return ResponseEntity.status(HttpStatus.OK).body(dtoList);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getCause().getCause().getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }
    
    @GetMapping("/find/find-by-id")
    public ResponseEntity<?> findById(@RequestBody BookDTO dto) {
        try {
            BookModel book = bookServices.findById(dto.getId());
            dto = (BookDTO) ModelMapperService.convertToDTO(book, BookDTO.class);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getCause().getCause().getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }

    @GetMapping("/find/find-by-title")
    public ResponseEntity<?> findByTitle(@RequestBody BookDTO dto) {
        try {
            List<BookModel> book = bookServices.findByTitle(dto.getTitle());
            dto = (BookDTO) ModelMapperService.convertToDTO(book, BookDTO.class);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getCause().getCause().getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }

    @GetMapping("/find/find-{number}-cheapests")
    public ResponseEntity<?> cheapests(@PathVariable("number") int number) {
        try {
            List<BookModel> bookList = bookServices.findCheapests(number);
            List<BookDTO> dtoList = new ArrayList<>();
            for (BookModel book : bookList) {
                BookDTO dto = (BookDTO) ModelMapperService.convertToDTO(book, BookDTO.class);

                dtoList.add(dto);
            }
            return ResponseEntity.status(HttpStatus.OK).body(dtoList);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        } catch (InvalidValueException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getCause().getCause().getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }

    @GetMapping("/find/find-{number}-available")
    public ResponseEntity<?> findBooksAvailable(@PathVariable("number") int number){
        try {
            List<BookModel> bookList = bookServices.findBooksAvailable(number);
            return ResponseEntity.status(HttpStatus.OK).body(bookList);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        }
    }

    @GetMapping("/find/find-{number}-unavailable")
    public ResponseEntity<?> findBooksUnavailable(@PathVariable("number") int number){
        try {
            List<BookModel> bookList = bookServices.findBooksUnavailable(number);
            return ResponseEntity.status(HttpStatus.OK).body(bookList);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        }
    }
}
