package com.bookstore.backend.presentation.dto.sale;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDTO {
    private Long Id;

    private List<ItemOrderDTO> itemList;
}
