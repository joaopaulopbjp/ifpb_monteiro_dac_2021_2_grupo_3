package com.bookstore.backend.application.controller.person;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.backend.application.service.person.AdminService;
import com.bookstore.backend.domain.model.user.AdminModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.modelmapper.ModelMapperService;
import com.bookstore.backend.presentation.dto.person.AdminDTO;
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
    public ResponseEntity<?> save(@RequestBody AdminDTO dto, Principal principal) {
        try {
            AdminModel admin = adminService.save(dto.getId(), principal.getName());
            dto = (AdminDTO) ModelMapperService.convertToDTO(admin, AdminDTO.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getCause().getCause().getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody AdminDTO dto, Principal principal) {
        AdminModel adminModel = (AdminModel) ModelMapperService.convertToModel(dto, AdminModel.class);

        try {
            AdminModel admin = adminService.update(adminModel, principal.getName());
            dto = (AdminDTO) ModelMapperService.convertToDTO(admin, AdminDTO.class);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    } 

    @DeleteMapping("/delete-user")
    public ResponseEntity<?> delete(@RequestBody AdminDTO dto, Principal principal) {
        try {
            adminService.delete(dto.getId(), principal.getName());
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getCause().getCause().getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(Principal principal) {
        try {
            adminService.delete(principal.getName());
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getCause().getCause().getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }

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

    @PostMapping("/find-by-id")
    public ResponseEntity<?> getById(@RequestBody AdminDTO dto) {
        try {
            AdminModel admin = adminService.findById(dto.getId());
            dto = (AdminDTO) ModelMapperService.convertToDTO(admin, AdminDTO.class);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }

    @PostMapping("/find-by-email")
    public ResponseEntity<?> getByEmail(@RequestBody AdminDTO dto) {
        try {
            AdminModel admin = adminService.findByEmail(dto.getEmail());
            dto = (AdminDTO) ModelMapperService.convertToDTO(admin, AdminDTO.class);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getCause().getCause().getMessage()));
        }
    }

    @PostMapping("/find-by-username")
    public ResponseEntity<?> getByUsername(@RequestBody AdminDTO dto) {
        try {
            AdminModel admin = adminService.findByUsername(dto.getUsername());
            dto = (AdminDTO) ModelMapperService.convertToDTO(admin, AdminDTO.class);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }
}
