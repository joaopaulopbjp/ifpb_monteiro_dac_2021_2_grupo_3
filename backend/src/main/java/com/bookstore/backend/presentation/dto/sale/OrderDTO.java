package com.bookstore.backend.presentation.dto.sale;

import java.time.LocalDate;
import java.util.List;

import com.bookstore.backend.infrastructure.enumerator.orderModel.OrderStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
	private Long id;

	private LocalDate dateOrder;

	private OrderStatus status;

	private Long idStatus;

	private List<ItemOrderDTO> itemList;

	private List<Long> idItemList;

	private Long idUser;
}
