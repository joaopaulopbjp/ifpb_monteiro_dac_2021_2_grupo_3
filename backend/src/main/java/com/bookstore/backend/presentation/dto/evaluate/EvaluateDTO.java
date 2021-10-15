package com.bookstore.backend.presentation.dto.evaluate;

import com.bookstore.backend.domain.model.product.ProductModel;
import com.bookstore.backend.domain.model.user.UserModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EvaluateDTO {
    private Long id;

    private Integer starNumber;
    
    private String comment;

    private UserModel user;

    private ProductModel product;
}
