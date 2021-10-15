package com.bookstore.backend.domain.model.sale;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bookstore.backend.domain.model.user.PersonModel;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "T_SALE_HISTORY")
public class UserSaleHistoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

	@JsonBackReference
    @OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PERSON_FK", nullable = false)
    private PersonModel person;

    @OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
        name = "T_PRODUCT_SALEHISTORY_JOIN", 
        joinColumns = @JoinColumn(name = "ORDER_ID", nullable = false), 
        inverseJoinColumns = @JoinColumn(name = "SALE_HISTORY_ID", nullable = false))
    private List<OrderModel> orderList;

    public boolean addOrderToOrderList(OrderModel orderModel) {
		if(orderModel != null) {
			orderList.add(orderModel);
		} else {
			orderList = new ArrayList<>();
			addOrderToOrderList(orderModel);
		}
		return true;
	}

	public boolean removeOrderFromOrderList(OrderModel orderModel) {
		if(orderList != null) {
			return orderList.remove(orderModel);
		} else {
			return false;
		}
	}
	
	@Override
    public String toString() {
        return String.format("SALE HISTORY [ID: %s ]", getId());
    }
}
