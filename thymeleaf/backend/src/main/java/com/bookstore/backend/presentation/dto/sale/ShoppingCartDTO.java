package com.bookstore.backend.presentation.dto.sale;

import java.math.BigDecimal;
import java.util.List;

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
public class ShoppingCartDTO {
    private Long Id;

    private Long idPerson;

    private List<ItemOrderDTO> itemList;

    private BigDecimal totalPrice;
}
