package com.bookstore.backend.domain.model.sale;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_SHOPPING_CART")
public class ShoppingCartModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long Id;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "ITEMLIST_FK")
    @Fetch(FetchMode.SUBSELECT)
    private List<ItemOrderModel> itemList;

    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;

    public BigDecimal calculateTotalPrice() {
        BigDecimal total = new BigDecimal(0);
        for(ItemOrderModel item : itemList) {
            total = total.add(item.getTotalPrice());
        }
        this.totalPrice = total;
        return total;
    }

    public boolean addItemOrderToItemList(ItemOrderModel item) {
        if(itemList != null){
            this.itemList.add(item);
        } else {
            itemList = new ArrayList<>();
            addItemOrderToItemList(item);
        }
        calculateTotalPrice();
        return true;
    }

    public boolean removeItemOrderFromItemList(ItemOrderModel item) {
        if(itemList != null) {
            boolean flag = itemList.remove(item);
            calculateTotalPrice();
            return flag;
        }
        return false;
    }

    public boolean removeItemOrderFromItemListByProductId(Long id) {
        if(itemList != null) {
            for(ItemOrderModel item : itemList) {
                if(item.getProduct().getId() == id) {
                    boolean flag = itemList.remove(item);
                    calculateTotalPrice();
                    return flag;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("SHOPPING CART [ID: %s]", getId());
    }
}
