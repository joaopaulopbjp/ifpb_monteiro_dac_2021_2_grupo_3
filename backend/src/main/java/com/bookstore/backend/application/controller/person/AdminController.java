package com.bookstore.backend.application.controller.person;

import java.util.ArrayList;
import java.util.List;

import com.bookstore.backend.application.service.person.AdminService;
import com.bookstore.backend.application.service.person.UserService;
import com.bookstore.backend.domain.model.user.AdminModel;
import com.bookstore.backend.domain.model.user.UserModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.modelmapper.ModelMapperService;
import com.bookstore.backend.presentation.dto.person.AdminDTO;
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
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    
    
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody AdminDTO dto) {
        try {
            AdminModel admin = adminService.save(dto.getId());
            dto = (AdminDTO) ModelMapperService.convertToDTO(admin, AdminDTO.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getCause().getCause().getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }

    // @PutMapping
    // public ResponseEntity<?> update(@RequestBody UserDTO dto) {
    //     UserModel userModel = (UserModel) ModelMapperService.convertToModel(dto, UserModel.class);

    //     try {
    //         userModel = userService.update(userModel);
    //         dto = (UserDTO) ModelMapperService.convertToDTO(userModel, UserDTO.class);
    //         return ResponseEntity.status(HttpStatus.OK).body(dto);
    //     } catch (NotFoundException e) {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
    //     }
    // } 

    // @DeleteMapping
    // public ResponseEntity<?> delete(@RequestBody UserDTO dto) {
    //     try {
    //         userService.delete(dto.getId());
    //         return ResponseEntity.status(HttpStatus.OK).body(null);
    //     } catch (NotFoundException e) {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
    //     } catch (DataIntegrityViolationException e) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getCause().getCause().getMessage()));
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
    //     }
    // }

    @GetMapping("/find-all/{page}")
    public ResponseEntity<?> getAll(@PathVariable("page") int page) {
        try {
            List<AdminModel> adminList = adminService.findAll(page);
            List<AdminDTO> adminDtoList = new ArrayList<>();

            for(AdminModel admin : adminList) {
                AdminDTO dto = (AdminDTO) ModelMapperService.convertToDTO(admin, AdminDTO.class);
                adminDtoList.add(dto);
            }
            return ResponseEntity.status(HttpStatus.OK).body(adminDtoList);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }

    // @GetMapping("/find-by-id")
    // public ResponseEntity<?> getById(@RequestBody UserDTO dto) {
    //     try {
    //         UserModel user = userService.findById(dto.getId());
    //         dto = (UserDTO) ModelMapperService.convertToDTO(user, UserDTO.class);
    //         return ResponseEntity.status(HttpStatus.OK).body(dto);
    //     } catch (NotFoundException e) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
    //     }catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
    //     }
    // }

    // @GetMapping("/find-by-email")
    // public ResponseEntity<?> getByEmail(@RequestBody UserDTO dto) {
    //     try {
    //         UserModel user = userService.findByEmail(dto.getEmail());
    //         dto = (UserDTO) ModelMapperService.convertToDTO(user, UserDTO.class);
    //         return ResponseEntity.status(HttpStatus.OK).body(dto);
    //     } catch (NotFoundException e) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
    //     }catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getCause().getCause().getMessage()));
    //     }
    // }

    // @GetMapping("/find-by-username")
    // public ResponseEntity<?> getByUsername(@RequestBody UserDTO dto) {
    //     try {
    //         UserModel user = userService.findByUsername(dto.getUsername());
    //         dto = (UserDTO) ModelMapperService.convertToDTO(user, UserDTO.class);
    //         return ResponseEntity.status(HttpStatus.OK).body(dto);
    //     } catch (NotFoundException e) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
    //     }catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
    //     }
    // }
}
