package com.bookstore.backend.domain.model.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bookstore.backend.domain.model.address.AddressModel;
import com.bookstore.backend.domain.model.evaluation.EvaluateModel;
import com.bookstore.backend.domain.model.sale.UserSaleHistoryModel;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SHOPPING_CART_ID", nullable = false)
    private ShoppingCartModel shoppingCart;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(
        name = "T_PERSON_EVALUATE_JOIN", 
        joinColumns = @JoinColumn(name = "PERSON_ID", nullable = false), 
        inverseJoinColumns = @JoinColumn(name = "EVALUATE_ID", nullable = false))
    private List<EvaluateModel> evaluateList;

    public UserModel(Long id, String username, String image, String email, String password, List<AddressModel> addressList,
        UserSaleHistoryModel saleHistory, ShoppingCartModel shoppingCart,
        List<EvaluateModel> evaluation) {
        super(id, username, image, email, password, addressList, saleHistory);
        this.shoppingCart = shoppingCart;
        this.evaluateList = evaluation;
    }

    public UserModel() {
    }

    public boolean addEvaluateToEvaluateList(EvaluateModel evaluateModel) {
        if(evaluateModel != null) {
            evaluateList.add(evaluateModel);
        } else {
            evaluateList = new ArrayList<>();
            addEvaluateToEvaluateList(evaluateModel);
        }
        return true;
    }

    public boolean removeEvaluateFromEvaluateList(EvaluateModel evaluateModel) {
        if(evaluateModel != null) {
            return evaluateList.remove(evaluateModel);
        }
        return false;
    }
    
    @Override
    public String toString() {
        return String.format("USER [ID: %s - USERNAME: %s - EMAIL: %s]", getId(), getUsername(), getEmail()); 
    }
}
