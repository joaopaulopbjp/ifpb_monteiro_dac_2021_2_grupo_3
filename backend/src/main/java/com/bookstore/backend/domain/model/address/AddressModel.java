package com.bookstore.backend.domain.model.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.bookstore.backend.presentation.dto.address.AddressDTO;

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
@Entity
@Table(name = "T_ADDRESS")
public class AddressModel {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "STREET", nullable = false)
	private String street;

	@Column(name = "NUMBER", nullable = false)
	private String number;

	@Column(name = "ZIPCODE", nullable = false)
	private String ZipCode;

	@Column(name = "CITY", nullable = false)
	private String city;

	@Column(name = "DISTRICT", nullable = false)
	private String district;

	public AddressModel(AddressDTO address){
		
	}
	
	@Override
    public String toString() {
        return String.format("ADDRESS [ID: %s - STREET: %s - NUMBER: %s - ZIPCODE: %s - CITY: %s - DISTRICT: %s ]", getId(), getStreet(), getNumber(), getZipCode(), getCity(), getDistrict());
    }
}
