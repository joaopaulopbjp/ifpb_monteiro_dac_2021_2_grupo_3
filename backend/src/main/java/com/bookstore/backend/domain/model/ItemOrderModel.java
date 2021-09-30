package com.bookstore.backend.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "T_ITEM_ORDER")
public class ItemOrderModel {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "AMOUNT")
	private int amount;
	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	private ProductModel product;
	@ManyToOne
	@JoinColumn(name = "ORDER_FK")
	private OrderModel order;

	public BigDecimal getTotalPrice() {
		return product.getPrice().multiply(new BigDecimal(getAmount()));
	}
}
