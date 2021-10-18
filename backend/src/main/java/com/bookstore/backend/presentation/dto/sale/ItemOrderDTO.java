package com.bookstore.backend.presentation.dto.sale;

import com.bookstore.backend.presentation.dto.product.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemOrderDTO {
    private Long id;

	private Integer amount;

	private ProductDTO product;
}
