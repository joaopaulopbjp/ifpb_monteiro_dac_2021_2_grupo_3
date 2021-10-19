package com.bookstore.backend.application.controller.company;

import com.bookstore.backend.application.service.company.PublishingCompanyService;
import com.bookstore.backend.domain.model.company.PublishingCompanyModel;
import com.bookstore.backend.infrastructure.modelmapper.ModelMapperService;
import com.bookstore.backend.presentation.dto.company.PublishingCompanyDTO;
import com.bookstore.backend.presentation.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
}
