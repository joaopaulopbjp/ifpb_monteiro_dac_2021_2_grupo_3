package com.bookstore.backend.presentation.dto.inventory;

import com.bookstore.backend.infrastructure.enumerator.InventoryStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InventoryDTO {
    private Long id;

	private Integer amount;

	private InventoryStatus status;

	public InventoryDTO(Long id, Integer amount, InventoryStatus status) {
		this.id = id;
		this.amount = amount;
		this.status = InventoryStatus.UNAVAILABLE;
		if(amount != null && amount > 0) {
			this.status = InventoryStatus.AVAILABLE;
		}
	}
	
	public InventoryDTO() {
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
		this.status = InventoryStatus.UNAVAILABLE;
		if(amount != null && amount > 0) {
			this.status = InventoryStatus.AVAILABLE;
		}
	}
}
