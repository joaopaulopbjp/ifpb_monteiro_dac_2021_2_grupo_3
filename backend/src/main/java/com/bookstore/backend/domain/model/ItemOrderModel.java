package com.bookstore.backend.domain.model;

import java.math.BigDecimal;

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
public class ItemOrderModel {
    
	private Long id;
	private int amount;
	private BookModel book;

	public BigDecimal getTotalPrice() {
		return book.getPrice().multiply(new BigDecimal(getAmount()));
	}
}
