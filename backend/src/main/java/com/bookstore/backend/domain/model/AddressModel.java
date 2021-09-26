package com.bookstore.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class AddressModel {
    
	private Long id;
	private String street;
	private String num;
	private String ZipCode;
	private String city;
	private String district;
	private UserModel user;
	
}
