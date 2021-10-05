package com.bookstore.backend.domain.model.evaluation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bookstore.backend.domain.model.product.ProductModel;
import com.bookstore.backend.domain.model.user.UserModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name =   "T_EVALUATE")
public class EvaluateModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "STAR_NUMBER")
    private int starNumber;
    
    @Column(name = "COMMENT")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "USER_FK")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_FK")
    private ProductModel product;

    @Override
    public String toString() {
        return String.format("EVALUATE [ID: %S - STAR NUMBER: %S - COMMENT: %S]", getId(), getStarNumber(), getComment());
    }
}