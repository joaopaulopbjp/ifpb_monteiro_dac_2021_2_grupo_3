package com.bookstore.backend.infrastructure.persistence.service.address;

import java.util.NoSuchElementException;

import com.bookstore.backend.domain.model.address.AddressModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.address.AddressRepository;
import com.bookstore.backend.infrastructure.utils.Utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class AddressRepositoryService {
    @Autowired
    private AddressRepository addressRepository;

    public AddressRepository getInstance() {
        return addressRepository;
    }

    public AddressModel update(AddressModel addressModel) throws NotFoundException {
        AddressModel AddressDB = null;
        try {
            AddressDB = addressRepository.findById(addressModel.getId()).get();

        } catch (NoSuchElementException e) {
            throw new NotFoundException();
        }

        BeanUtils.copyProperties(addressModel, AddressDB, Utils.getNullPropertyNames(addressModel));

        return addressRepository.save(AddressDB);
    }
}
