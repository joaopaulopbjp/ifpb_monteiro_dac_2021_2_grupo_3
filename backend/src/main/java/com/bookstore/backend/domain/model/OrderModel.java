package com.bookstore.backend.domain.model;

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
	private List<ItemOrderModel> itemList = new ArrayList<ItemOrderModel>();
	private Double totalPrice;
	
}
