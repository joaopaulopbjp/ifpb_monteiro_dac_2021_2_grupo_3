package com.bookstore.backend.presentation.dto.sale;

import com.bookstore.backend.presentation.dto.product.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaleDTO {
    private Long id;

    private Integer amount;

    private ProductDTO productDto;
    
    private Long productId;
}
