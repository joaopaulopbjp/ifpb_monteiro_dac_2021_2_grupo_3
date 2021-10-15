package com.bookstore.backend.application.controller.address;

import java.util.List;

import com.bookstore.backend.application.service.address.AddressService;
import com.bookstore.backend.domain.model.address.AddressModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.modelmapper.ModelMapperService;
import com.bookstore.backend.presentation.dto.address.AddressDTO;
import com.bookstore.backend.presentation.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/address")
@CrossOrigin
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody AddressDTO dto){
        AddressModel addressModel = (AddressModel) ModelMapperService.convertToModel(dto, AddressModel.class);
        addressModel = addressService.save(addressModel, dto.getPersonId());
        return ResponseEntity.status(HttpStatus.CREATED).body(addressModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            addressService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body(id + " Address Deleted");
        } catch (IllegalArgumentException e) {
            Response response = new Response(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    
    @GetMapping
    public ResponseEntity<?> getAll() {
        List<AddressModel> list = addressService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddressById(@RequestBody Long id){
        try {
            AddressModel address = addressService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(address);
        } catch (NotFoundException e) {
            Response response = new Response(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    
    @PatchMapping
    public ResponseEntity<?> update(@RequestBody AddressDTO dto){
        AddressModel address = (AddressModel) ModelMapperService.convertToModel(dto, AddressModel.class);
        try {
            address = addressService.update(address);
            return ResponseEntity.status(HttpStatus.OK).body(address);
        } catch (NotFoundException e) {
            Response response = new Response(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
