package com.bookstore.backend.domain.model.sale;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Entity
@Table(name = "T_ORDER")
public class OrderModel {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "DATA_ORDER")
	private LocalDate dateOrder;
	@Column(name = "STATUS")
	private OrderStatus status;
	@OneToMany(mappedBy = "order")
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