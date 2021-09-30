package com.bookstore.backend.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    @ManyToMany
    @JoinColumn(name = "AUTHOR_FK")
    private List<ProductModel> productList;

    public boolean addBookToBookList(ProductModel product) {
        if(productList != null){
            this.productList.add(product);
        } else {
            productList = new ArrayList<>();
            addBookToBookList(product);
        }
        return true;
    }

    public boolean removeBookFromBookList(ProductModel product) {
        if(productList != null) {
            return productList.remove(product);
        }
        return false;
    }

}
