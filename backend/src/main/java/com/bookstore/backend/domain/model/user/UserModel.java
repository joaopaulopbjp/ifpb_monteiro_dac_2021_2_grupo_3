package com.bookstore.backend.domain.model.user;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bookstore.backend.domain.model.AddressModel;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_USER")
public class UserModel extends PersonModel{

    public UserModel(Long id, String username, String email, String password, List<AddressModel> addressList) {
        super(id, username, email, password, addressList);
    }

    public UserModel() {
    }
    
}
