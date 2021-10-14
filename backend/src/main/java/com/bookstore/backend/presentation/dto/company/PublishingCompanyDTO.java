package com.bookstore.backend.presentation.dto.company;

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
public class PublishingCompanyDTO {
    
    private Long id;

    private String name;

    private List<ProductModel> productList;
    
}
