package com.bookstore.backend.presentation.dto.person;

import java.util.List;

import com.bookstore.backend.presentation.dto.evaluate.EvaluateDTO;
import com.bookstore.backend.presentation.dto.sale.ShoppingCartDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends PersonDTO {
    private ShoppingCartDTO shoppingCart;

    private List<EvaluateDTO> evaluateList;
}
