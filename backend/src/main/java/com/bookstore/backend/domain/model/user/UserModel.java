package com.bookstore.backend.domain.model.user;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bookstore.backend.domain.model.AddressModel;
import com.bookstore.backend.domain.model.product.ProductModel;
import com.bookstore.backend.domain.model.sale.UserSaleHistoryModel;


@Entity
@Table(name = "T_USER")
public class UserModel extends PersonModel{

    public UserModel(Long id, String username, String email, String password, List<AddressModel> addressList,
            List<ProductModel> productList, UserSaleHistoryModel saleHistory) {
        super(id, username, email, password, addressList, productList, saleHistory);
    }

    public UserModel() {
    }
    
}
