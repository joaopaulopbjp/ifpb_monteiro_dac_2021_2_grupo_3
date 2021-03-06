package com.bookstore.backend.presentation.dto.address;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDTO{

    private Long id;

    private String street;

    private String number;

    private String zipCode;

    private String city;

    private String district;

    private Long personId;

}
