package com.bookstore.backend.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bookstore.backend.infrastructure.enumerator.orderModel.OrderStatus;

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
public class OrderModel {
    
	private Long id;
	private LocalDate dateOrder;
	private OrderStatus status;
	private List<ItemOrderModel> itemList;

	public BigDecimal getTotalPrice() {
		BigDecimal totalPrice = new BigDecimal(0);

		for(ItemOrderModel item : itemList) {
			totalPrice.add(item.getTotalPrice());
		}
		return totalPrice;
	}

	public boolean addItemToItemList(ItemOrderModel itemOrderModel) {
		if(itemList != null) {
			itemList.add(itemOrderModel);
		} else {
			itemList = new ArrayList<>();
			addItemToItemList(itemOrderModel);
		}
		return true;
	}

	public boolean removeItemFromItemList(ItemOrderModel itemOrderModel) {
		if(itemList != null) {
			return itemList.remove(itemOrderModel);
		} else {
			return false;
		}
	} 
}
