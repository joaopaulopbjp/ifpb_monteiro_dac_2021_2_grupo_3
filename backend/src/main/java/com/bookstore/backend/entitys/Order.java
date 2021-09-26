package com.bookstore.backend.entitys;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
public class Order {
    
	private Long id;
	private Date dateOrder;
	private String status;
	private List<ItemOrder> items = new ArrayList<ItemOrder>();
	private Double totalPrice;
	
}
