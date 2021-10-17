package com.bookstore.backend.application.service.address;

import com.bookstore.backend.domain.model.address.AddressModel;
import com.bookstore.backend.domain.model.user.UserModel;
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
        UserModel user = userRepositoryService.getInstance().findById(personId).get();
        address = addressRepositoryService.getInstance().save(address);
        user.addAddressToAddressList(address);
        user = userRepositoryService.getInstance().save(user);
        return address;
    }

    public void delete(Long id) throws IllegalArgumentException {
        UserModel user = userRepositoryService.getInstance().findByAddressId(id);
        Optional<AddressModel> address = addressRepositoryService.getInstance().findById(id);

        user.removeAddressFromAddressList(address.get());
        userRepositoryService.getInstance().save(user);
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
