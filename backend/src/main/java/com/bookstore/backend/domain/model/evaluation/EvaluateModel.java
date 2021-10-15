package com.bookstore.backend.domain.model.evaluation;

import javax.persistence.CascadeType;
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
import com.fasterxml.jackson.annotation.JsonBackReference;

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
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "STAR_NUMBER", nullable = false)
    private Integer starNumber;
    
    @Column(name = "COMMENT", nullable = false)
    private String comment;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_FK", nullable = false)
    private UserModel user;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "PRODUCT_FK", nullable = false)
    private ProductModel product;

    @Override
    public String toString() {
        return String.format("EVALUATE [ID: %S - STAR NUMBER: %S - COMMENT: %S]", getId(), getStarNumber(), getComment());
    }
}
