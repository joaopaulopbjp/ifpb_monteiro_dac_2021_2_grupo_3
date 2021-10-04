package com.bookstore.backend.domain.model.author;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import java.util.ArrayList;

import com.bookstore.backend.domain.model.product.ProductModel;
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
@Table(name = "T_AUTHOR")
public class AuthorModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "T_PRODUCT_AUTHOR_JOIN",
        joinColumns = @JoinColumn(name = "PRODUCT_ID"),
        inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID"))
    private List<ProductModel> productList;

    public boolean addProductToProductList(ProductModel product) {
        if(productList != null){
            this.productList.add(product);
        } else {
            productList = new ArrayList<>();
            addProductToProductList(product);
        }
        return true;
    }

    public boolean removeProductFromProductList(ProductModel product) {
        if(productList != null) {
            return productList.remove(product);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("AUTHOR [ID: %s - NAME: %s ]", getId(), getName());
    }
}
