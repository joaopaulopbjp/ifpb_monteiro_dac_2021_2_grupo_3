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
public class  InventoryModel {
    
	private Long id;
	private int amount;
	private BookModel book;

	public void decrease() {
		if(amount > 0) {
			amount--;
		}
	}

	public void increase() {
		amount++;
	}
	
	public void addAmount(int amount) {
		this.amount += amount;
	}

	public void removerAmount(int amount) {
		this.amount -= amount;
	}
}
