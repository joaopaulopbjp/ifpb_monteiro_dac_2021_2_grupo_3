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

    public AddressModel save(AddressModel address, Long personId) throws NotFoundException{
        Optional<UserModel> user = userRepositoryService.getInstance().findById(personId);
        if(!user.isPresent()){
            throw new NotFoundException("Not find person with this id " + personId);
        }
        address = addressRepositoryService.getInstance().save(address);
        user.get().addAddressToAddressList(address);
        userRepositoryService.getInstance().save(user.get());
        return address;
    }

    public void delete(Long id) throws IllegalArgumentException {
        UserModel user = userRepositoryService.getInstance().findByAddressId(id).get();
        Optional<AddressModel> address = addressRepositoryService.getInstance().findById(id);
        user.removeAddressFromAddressList(address.get());
        userRepositoryService.getInstance().save(user);
        addressRepositoryService.getInstance().delete(address.get());
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
