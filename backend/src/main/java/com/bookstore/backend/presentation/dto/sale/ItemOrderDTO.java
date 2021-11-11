package com.bookstore.backend.presentation.dto.sale;

import com.bookstore.backend.presentation.dto.product.ProductDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemOrderDTO {
    private Long id;

	private Integer amount;

	private ProductDTO product;

	private Long idProduct;
}
