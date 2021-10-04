package com.bookstore.backend.domain.model.user;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bookstore.backend.domain.model.address.AddressModel;
import com.bookstore.backend.domain.model.product.ProductModel;
import com.bookstore.backend.domain.model.sale.UserSaleHistoryModel;

@Entity
@Table(name = "T_ADMIN")
public class AdminModel extends PersonModel{

    public AdminModel(Long id, String username, String email, String password, List<AddressModel> addressList,
            List<ProductModel> productForSaleList, UserSaleHistoryModel saleHistory) {
        super(id, username, email, password, addressList, productForSaleList, saleHistory);
    }

    public AdminModel() {
    }
    
    @Override
    public String toString() {
        return String.format("ADMIN [ID: %s - USERNAME: %s - EMAIL: %s]", getId(), getUsername(), getEmail()); 
    }
}
