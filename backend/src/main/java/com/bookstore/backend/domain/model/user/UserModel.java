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
import com.bookstore.backend.domain.model.sale.shoppingCartModel;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "T_USER")
public class UserModel extends PersonModel{

    @OneToOne(mappedBy = "userModel", fetch = FetchType.EAGER)
    private shoppingCartModel shoppingCart;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<EvaluateModel> evaluateList;

    public UserModel(Long id, String username, String email, String password, List<AddressModel> addressList,
            List<ProductModel> productForSaleList, UserSaleHistoryModel saleHistory, shoppingCartModel shoppingCart,
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
