package com.bookstore.backend.domain.model.user;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bookstore.backend.domain.model.address.AddressModel;
import com.bookstore.backend.domain.model.product.ProductModel;
import com.bookstore.backend.domain.model.sale.UserSaleHistoryModel;
import com.bookstore.backend.domain.model.sale.shoppingCartModel;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "T_USER")
public class UserModel extends PersonModel{

    @OneToOne(mappedBy = "userModel")
    private shoppingCartModel shoppingCart;

    public UserModel(Long id, String username, String email, String password, List<AddressModel> addressList,
            List<ProductModel> productForSaleList, UserSaleHistoryModel saleHistory,
            shoppingCartModel shoppingCart) {
        super(id, username, email, password, addressList, productForSaleList, saleHistory);
        this.shoppingCart = shoppingCart;
    }

    public UserModel() {
    }
    
    @Override
    public String toString() {
        return String.format("USER [ID: %s - USERNAME: %s - EMAIL: %s]", getId(), getUsername(), getEmail()); 
    }
}
