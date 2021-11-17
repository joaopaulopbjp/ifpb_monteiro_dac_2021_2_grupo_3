package com.bookstore.backend.domain.model.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bookstore.backend.domain.model.address.AddressModel;
import com.bookstore.backend.domain.model.product.ProductModel;
import com.bookstore.backend.domain.model.sale.UserSaleHistoryModel;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "T_ADMIN")
public class AdminModel extends PersonModel {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(
        name = "T_ADMIN_PRODUCT_FOR_SALE_JOIN", 
        joinColumns = @JoinColumn(name = "ADMIN_ID", nullable = false), 
        inverseJoinColumns = @JoinColumn(name = "PRODUCT_FOR_SALE_ID", nullable = false))
    private List<ProductModel> productForSaleList;

    public AdminModel(Long id, String username, String email, String password, List<AddressModel> addressList,
            List<ProductModel> productForSaleList, UserSaleHistoryModel saleHistory) {
        super(id, username, email, password, addressList, saleHistory);
    }

    public AdminModel() {
    }
    
    public boolean addProductToProductList(ProductModel product) {
        if(productForSaleList != null) {
            productForSaleList.add(product);
        } else {
            productForSaleList = new ArrayList<>();
            addProductToProductList(product);
        }
        return true;
    }

    public boolean removeProductFromProductList(ProductModel product) {
        if(productForSaleList != null) {
            return productForSaleList.remove(product);
        } 
        return false;
    }

    @Override
    public String toString() {
        return String.format("ADMIN [ID: %s - USERNAME: %s - EMAIL: %s]", getId(), getUsername(), getEmail()); 
    }
}
