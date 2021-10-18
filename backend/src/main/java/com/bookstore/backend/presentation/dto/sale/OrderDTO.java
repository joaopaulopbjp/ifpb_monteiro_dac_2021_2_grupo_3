package com.bookstore.backend.presentation.dto.sale;

import java.time.LocalDate;
import java.util.List;

import com.bookstore.backend.infrastructure.enumerator.orderModel.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
	private Long id;

	private LocalDate dateOrder;

	private OrderStatus status;

	private List<ItemOrderDTO> itemList;
}
