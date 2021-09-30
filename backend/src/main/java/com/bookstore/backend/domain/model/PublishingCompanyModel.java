package com.bookstore.backend.domain.model;

import com.bookstore.backend.domain.model.product.ProductModel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	private String name;
	@OneToMany(mappedBy = "company")
	private List<ProductModel> productList;

	public boolean addBookToBookList(ProductModel ProductModel) {
		if(productList != null) {
			productList.add(ProductModel);
		} else {
			productList = new ArrayList<>();
			addBookToBookList(ProductModel);
		}
		return true;
	}

	public boolean removeBookFromBookList(ProductModel ProductModel) {
		if(productList != null) {
			return productList.remove(ProductModel);
		}
		 return false;
	}
}
