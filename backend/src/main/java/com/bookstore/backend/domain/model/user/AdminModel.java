package com.bookstore.backend.domain.model.user;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bookstore.backend.domain.model.AddressModel;

@Entity
@Table(name = "T_ADMIN")
public class AdminModel extends PersonModel{

    public AdminModel(Long id, String username, String email, String password, List<AddressModel> addressList) {
        super(id, username, email, password, addressList);
    }

    public AdminModel() {
    }
    
}
