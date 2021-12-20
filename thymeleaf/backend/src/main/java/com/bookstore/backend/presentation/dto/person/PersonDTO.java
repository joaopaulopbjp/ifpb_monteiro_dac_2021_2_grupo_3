package com.bookstore.backend.presentation.dto.person;

import java.util.List;

import com.bookstore.backend.presentation.dto.address.AddressDTO;

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
public class PersonDTO {
    private Long id;

    private String username;

    private String image;

    private String email;

    private String password;

    private List<AddressDTO> addressList;

    private List<Long> idAddressList;
    
 
    private List<Long> idProductForSaleList;


    private Long IdsaleHistory;
}
