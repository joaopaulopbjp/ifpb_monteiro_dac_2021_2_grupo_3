package com.bookstore.backend.domain.model.sale;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bookstore.backend.domain.model.product.ProductModel;
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
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "AMOUNT", nullable = false)
	private Integer amount;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	private ProductModel product;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ORDER_FK")
	private OrderModel order;


	public BigDecimal getTotalPrice() {
		return product.getPrice().multiply(new BigDecimal(getAmount()));
	}

	@Override
    public String toString() {
        return String.format("ITEM ORDER [ID: %s - AMOUNT: %s ]", getId(), getAmount());
    }
}
