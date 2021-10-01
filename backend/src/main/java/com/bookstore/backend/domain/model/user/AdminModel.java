package com.bookstore.backend.domain.model.user;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bookstore.backend.domain.model.AddressModel;
import com.bookstore.backend.domain.model.sale.UserSaleHistoryModel;

@Entity
@Table(name = "T_ADMIN")
public class AdminModel extends PersonModel{

    public AdminModel(Long id, String username, String email, String password, List<AddressModel> addressList,
            UserSaleHistoryModel saleHistory) {
        super(id, username, email, password, addressList, saleHistory);
    }

    public AdminModel() {
    }
    
}