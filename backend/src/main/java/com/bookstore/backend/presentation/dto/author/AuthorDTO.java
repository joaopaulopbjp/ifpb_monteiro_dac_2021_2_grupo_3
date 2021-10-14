package com.bookstore.backend.presentation.dto.author;

import java.util.List;

import com.bookstore.backend.domain.model.product.ProductModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorDTO {

    private Long id;

    private String name;

    private List<ProductModel> productList;
    
}
