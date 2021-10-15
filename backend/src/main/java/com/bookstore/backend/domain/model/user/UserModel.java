package com.bookstore.backend.domain.model.user;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bookstore.backend.domain.model.address.AddressModel;
import com.bookstore.backend.domain.model.evaluation.EvaluateModel;
import com.bookstore.backend.domain.model.product.ProductModel;
import com.bookstore.backend.domain.model.sale.UserSaleHistoryModel;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.bookstore.backend.domain.model.sale.ShoppingCartModel;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "T_USER")
public class UserModel extends PersonModel{

    @JsonManagedReference
    @OneToOne(mappedBy = "userModel")
    private ShoppingCartModel shoppingCart;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<EvaluateModel> evaluateList;

    public UserModel(Long id, String username, String email, String password, List<AddressModel> addressList,
            List<ProductModel> productForSaleList, UserSaleHistoryModel saleHistory, ShoppingCartModel shoppingCart,
            List<EvaluateModel> evaluation) {
        super(id, username, email, password, addressList, productForSaleList, saleHistory);
        this.shoppingCart = shoppingCart;
        this.evaluateList = evaluation;
    }

    public UserModel() {
    }
    
    @Override
    public String toString() {
        return String.format("USER [ID: %s - USERNAME: %s - EMAIL: %s]", getId(), getUsername(), getEmail()); 
    }
}
