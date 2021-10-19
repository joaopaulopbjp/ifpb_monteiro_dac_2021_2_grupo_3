package com.bookstore.backend.application.controller.company;

import java.util.List;

import com.bookstore.backend.application.service.company.PublishingCompanyService;
import com.bookstore.backend.domain.model.company.PublishingCompanyModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.modelmapper.ModelMapperService;
import com.bookstore.backend.presentation.dto.company.PublishingCompanyDTO;
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
@RequestMapping("/api/company")
@CrossOrigin
public class PublishingCompanyController {
    
    @Autowired
    private PublishingCompanyService publishingCompanyService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PublishingCompanyDTO dto){
        try {
            PublishingCompanyModel company = (PublishingCompanyModel)ModelMapperService.convertToModel(dto, PublishingCompanyModel.class);
            company = publishingCompanyService.save(company);
            dto = (PublishingCompanyDTO)ModelMapperService.convertToDTO(company, PublishingCompanyDTO.class);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (IllegalArgumentException e) {
            Response response = new Response(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(){
        List<PublishingCompanyModel> companyList = publishingCompanyService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(companyList);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<?> findById(@RequestBody PublishingCompanyDTO dto){
        try {
            PublishingCompanyModel company = publishingCompanyService.findById(dto.getId());
            return ResponseEntity.status(HttpStatus.OK).body(company);
        } catch (NotFoundException e) {
            Response response = new Response(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        
    }
}
