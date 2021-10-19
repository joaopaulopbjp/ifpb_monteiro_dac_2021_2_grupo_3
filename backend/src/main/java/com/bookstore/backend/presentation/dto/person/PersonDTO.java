package com.bookstore.backend.presentation.dto.person;

import java.util.List;

import com.bookstore.backend.presentation.dto.address.AddressDTO;
import com.bookstore.backend.presentation.dto.product.ProductDTO;
import com.bookstore.backend.presentation.dto.sale.UserSaleHistoryDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private Long id;

    private String username;

    private String email;

    private String password;

    private List<AddressDTO> addressList;

    private List<Long> idAddressList;
    
    private List<ProductDTO> productForSaleList;

    private List<Long> idProductForSaleList;

    private UserSaleHistoryDTO saleHistory;

    private Long IdsaleHistory;
}
