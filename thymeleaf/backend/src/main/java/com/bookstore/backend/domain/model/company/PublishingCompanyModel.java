package com.bookstore.backend.domain.model.company;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "T_PUBLISHING_COMPANY")
public class PublishingCompanyModel {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "NAME", nullable = false, unique = true)
	private String name;

	@Override
    public String toString() {
        return String.format("PUBLISHING COMPANY [ID: %s - NAME: %s ]", getId(), getName());
    }
}
