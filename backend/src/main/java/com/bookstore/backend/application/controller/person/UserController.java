package com.bookstore.backend.application.controller.person;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.backend.application.service.person.UserService;
import com.bookstore.backend.domain.model.user.UserModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.modelmapper.ModelMapperService;
import com.bookstore.backend.presentation.dto.person.UserDTO;
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
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody UserDTO dto) {
        UserModel userModel = (UserModel) ModelMapperService.convertToModel(dto, UserModel.class);
        try {
            userModel = userService.save(userModel);
            dto = (UserDTO) ModelMapperService.convertToDTO(userModel, UserDTO.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getCause().getCause().getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody UserDTO dto, Principal principal) {
        UserModel userModel = (UserModel) ModelMapperService.convertToModel(dto, UserModel.class);

        try {
            userModel = userService.update(userModel, principal.getName());
            dto = (UserDTO) ModelMapperService.convertToDTO(userModel, UserDTO.class);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    } 

    @DeleteMapping
    public ResponseEntity<?> delete(Principal principal) {
        try {
            userService.delete(principal.getName());
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getCause().getCause().getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }

    @GetMapping("/find/find-all/{page}")
    public ResponseEntity<?> getAll(@PathVariable("page") int page) {
        try {
            List<UserModel> userList = userService.findAll(page);
            List<UserDTO> userDtoList = new ArrayList<>();

            for(UserModel user : userList) {
                UserDTO dto = (UserDTO) ModelMapperService.convertToDTO(user, UserDTO.class);
                userDtoList.add(dto);
            }
            return ResponseEntity.status(HttpStatus.OK).body(userDtoList);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }

    @PostMapping("/find/find-by-id")
    public ResponseEntity<?> getById(@RequestBody UserDTO dto) {
        try {
            UserModel user = userService.findById(dto.getId());
            dto = (UserDTO) ModelMapperService.convertToDTO(user, UserDTO.class);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }

    @PostMapping("/find/find-by-email")
    public ResponseEntity<?> getByEmail(@RequestBody UserDTO dto) {
        try {
            UserModel user = userService.findByEmail(dto.getEmail());
            dto = (UserDTO) ModelMapperService.convertToDTO(user, UserDTO.class);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getCause().getCause().getMessage()));
        }
    }

    @PostMapping("/find/find-by-username")
    public ResponseEntity<?> getByUsername(@RequestBody UserDTO dto) {
        try {
            UserModel user = userService.findByUsername(dto.getUsername());
            dto = (UserDTO) ModelMapperService.convertToDTO(user, UserDTO.class);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }
}
