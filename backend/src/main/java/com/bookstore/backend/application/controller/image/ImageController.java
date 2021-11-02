package com.bookstore.backend.application.controller.image;

import java.security.Principal;
import java.util.List;

import com.bookstore.backend.application.service.image.ImageService;
import com.bookstore.backend.domain.model.image.ImageModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.modelmapper.ModelMapperService;
import com.bookstore.backend.presentation.dto.image.ImageDTO;
import com.bookstore.backend.presentation.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/image")
@CrossOrigin
public class ImageController {
    
    @Autowired
    private ImageService imageService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ImageDTO dto, Principal principal){
        try {
            ImageModel image = (ImageModel) ModelMapperService.convertToModel(dto, ImageModel.class);
            image = imageService.save(image, dto.getIdBook(), principal.getName());
            dto = (ImageDTO) ModelMapperService.convertToDTO(image, ImageDTO.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody ImageDTO dto, Principal principal){
        try {
            imageService.delete(dto.getId(), principal.getName());
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getCause().getCause().getMessage()));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(){
        try {
            List<ImageModel> imageList = imageService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(imageList);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        }
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<?> findById(@RequestBody ImageDTO dto){
        try {
            ImageModel image = imageService.findById(dto.getId());
            return ResponseEntity.status(HttpStatus.OK).body(image);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        }
    }
}
