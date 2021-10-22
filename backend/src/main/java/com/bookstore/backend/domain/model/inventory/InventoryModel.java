package com.bookstore.backend.domain.model.inventory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.bookstore.backend.infrastructure.enumerator.InventoryStatus;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "T_INVENTORY")
public class  InventoryModel {

    public InventoryModel(Long id, Integer amount, InventoryStatus status) {
		this.id = id;
		this.amount = amount;
		this.status = InventoryStatus.UNAVAILABLE;
		if(amount != null && amount > 0) {
			this.status = InventoryStatus.AVAILABLE;
		}
	}
	
	public InventoryModel() {
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
	private Long id;

    @Column(name = "AMOUNT", nullable = false)
	private Integer amount;

	@Column(name = "STATUS", nullable = false)
	private InventoryStatus status;

	public void decrease() {
		if(this.amount > 0) {
			this.amount--;
			if(this.amount == 0){
				setStatus(InventoryStatus.UNAVAILABLE);
			}
		}
	}

	public void increase() {
		this.amount++;
		if(this.amount > 0){
			setStatus(InventoryStatus.AVAILABLE);
		}
	}
	
	public void addAmount(int amount) {
		if(amount > 0){
			this.amount += amount;
			setStatus(InventoryStatus.AVAILABLE);
		}
	}

	public void removerAmount(int amount) {
		this.amount -= amount;
		if(this.amount == 0){
			setStatus(InventoryStatus.UNAVAILABLE);
		}else if(this.amount < 0){
			setAmount(0);
			setStatus(InventoryStatus.UNAVAILABLE);
		}
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
		this.status = InventoryStatus.UNAVAILABLE;
		if(amount != null && amount > 0) {
			this.status = InventoryStatus.AVAILABLE;
		}
	}

	@Override
    public String toString() {
        return String.format("INVENTORY [ID: %s - AMOUNT: %s ]", getId(), getAmount());
    }
}
