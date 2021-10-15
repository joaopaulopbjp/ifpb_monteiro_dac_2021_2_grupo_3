package com.bookstore.backend.application.service.address;

import com.bookstore.backend.domain.model.address.AddressModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.address.AddressRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.person.UserRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    
    @Autowired
    private AddressRepositoryService addressRepositoryService;

    @Autowired
    private UserRepositoryService userRepositoryService;

    public AddressModel save(AddressModel address, Long personId){
        address.setPerson(userRepositoryService.getInstance().findById(personId).get());
        return addressRepositoryService.getInstance().save(address);
    }

    public void delete(AddressModel addressModel){
        addressRepositoryService.getInstance().delete(addressModel);
    }

    public AddressModel update(AddressModel addressModel) throws NotFoundException{
        return addressRepositoryService.update(addressModel);
    }

    public AddressModel findById(Long id) throws NotFoundException{
        Optional<AddressModel> address = addressRepositoryService.getInstance().findById(id);
        if(address == null){
            throw new NotFoundException();
        }
        return address.get();
    }

    public List<AddressModel> findAll(){
        return addressRepositoryService.getInstance().findAll();
    }
}
