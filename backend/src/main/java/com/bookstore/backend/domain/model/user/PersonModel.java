package com.bookstore.backend.domain.model.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bookstore.backend.domain.model.address.AddressModel;
import com.bookstore.backend.domain.model.product.ProductModel;
import com.bookstore.backend.domain.model.sale.UserSaleHistoryModel;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "T_PERSON")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PersonModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<AddressModel> addressList;

    @OneToMany(mappedBy = "salesman", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<ProductModel> productForSaleList;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    @JoinColumn(name = "SALE_HISTORY_ID")
    private UserSaleHistoryModel saleHistory;
    

    public boolean addAddressToAddressList(AddressModel addressModel) {
        if(addressList != null) {
            addressList.add(addressModel);
        } else {
            addressList = new ArrayList<>();
            addAddressToAddressList(addressModel);
        }
        return true;
    }

    public boolean removeAddressFromAddressList(AddressModel addressModel) {
        if(addressList != null) {
            return addressList.remove(addressModel);
        } 
        return false;
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
        return String.format("PERSON [ID: %s - USERNAME: %s - EMAIL: %s]", getId(), getUsername(), getEmail()); 
    }
}
