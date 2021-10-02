package com.bookstore.backend.domain.model.address;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bookstore.backend.domain.model.user.PersonModel;

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
	@Column(name = "ID")
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
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PERSON_FK")
	private PersonModel person;
	
}
