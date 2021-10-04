package com.bookstore.backend.domain.model.inventory;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "T_INVENTORY")
public class  InventoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
	private Long id;

    @Column(name = "AMOUNT")
	private int amount;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_FK")
	private ProductModel product;
	

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

	@Override
    public String toString() {
        return String.format("INVENTORY [ID: %s - AMOUNT: %s ]", getId(), getAmount());
    }
}
