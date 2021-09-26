package com.bookstore.backend.entitys;

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
public class Address {
    
	private Long id;
	private String street;
	private int num;
	private String CEP;
	private String city;
	private String district;
	private User user;
	
}
