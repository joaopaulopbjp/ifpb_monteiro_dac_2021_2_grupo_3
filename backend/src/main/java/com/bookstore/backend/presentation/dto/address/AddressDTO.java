package com.bookstore.backend.presentation.dto.address;

import com.bookstore.backend.domain.model.user.PersonModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDTO{

    private Long id;

    private String street;

    private String number;

    private String zipCode;

    private String city;

    private String district;

    private PersonModel personModel;

}
