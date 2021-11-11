package com.bookstore.backend.application.controller.Author;

import java.security.Principal;
import java.util.List;

import com.bookstore.backend.application.service.author.AuthorService;
import com.bookstore.backend.domain.model.author.AuthorModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.modelmapper.ModelMapperService;
import com.bookstore.backend.presentation.dto.author.AuthorDTO;
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
@RequestMapping("/api/author")
@CrossOrigin
public class AuthorController {
    
    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody AuthorDTO dto){
        try {
            AuthorModel author = (AuthorModel) ModelMapperService.convertToModel(dto, AuthorModel.class);
            author = authorService.save(author);
    
            return ResponseEntity.status(HttpStatus.CREATED).body(author);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getCause().getCause().getMessage()));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody AuthorDTO dto, Principal principal){
        try {
            authorService.delete(dto.getId(),principal.getName());
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (IllegalArgumentException | NotFoundException e) {
            Response response = new Response(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getCause().getCause().getMessage()));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }

    @GetMapping("/find/find-all/{page}")
    public ResponseEntity<?> findAll(@PathVariable("page") int page){
        try {
            List<AuthorModel> authorList = authorService.findAll(page);
    
            return ResponseEntity.status(HttpStatus.OK).body(authorList);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getCause().getCause().getMessage()));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }

    @GetMapping("/find/find-by-id")
    public ResponseEntity<?> findById(@RequestBody AuthorDTO dto){
        try {
            AuthorModel authorList = authorService.findById(dto.getId());
            return ResponseEntity.status(HttpStatus.OK).body(authorList);
        } catch (IllegalArgumentException e) {
            Response response = new Response(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }catch(NotFoundException e){
            Response response = new Response(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getCause().getCause().getMessage()));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }

    @GetMapping("/find/find-by-name")
    public ResponseEntity<?> findByName(@RequestBody AuthorDTO dto){
        try {
            List<AuthorModel> author = authorService.findByName(dto.getName());
            return ResponseEntity.status(HttpStatus.OK).body(author);
        } catch (NotFoundException e) {
            Response response = new Response(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getCause().getCause().getMessage()));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody AuthorModel dto){
        try {
            AuthorModel author = (AuthorModel) ModelMapperService.convertToModel(dto, AuthorModel.class);
            author = authorService.update(author);
            return ResponseEntity.status(HttpStatus.OK).body(author);
        } catch (NotFoundException e) {
            Response response = new Response(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getCause().getCause().getMessage()));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    } 
}
