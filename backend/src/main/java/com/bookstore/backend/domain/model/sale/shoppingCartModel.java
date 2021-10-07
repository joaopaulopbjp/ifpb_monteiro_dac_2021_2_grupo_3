package com.bookstore.backend.domain.model.sale;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bookstore.backend.domain.model.user.UserModel;

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
public class shoppingCartModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long Id;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "ITEMLIST_FK")
    @Fetch(FetchMode.SUBSELECT)
    private List<ItemOrderModel> itemList;

    @OneToOne
    private UserModel userModel;


    public boolean addItemOrderToItemList(ItemOrderModel item) {
        if(itemList != null){
            this.itemList.add(item);
        } else {
            itemList = new ArrayList<>();
            addItemOrderToItemList(item);
        }
        return true;
    }

    public boolean removeItemOrderFromItemList(ItemOrderModel item) {
        if(itemList != null) {
            return itemList.remove(item);
        }
        return false;
    }

    public boolean removeItemOrderFromItemListByProductId(Long id) {
        if(itemList != null) {
            for(ItemOrderModel item : itemList) {
                if(item.getProduct().getId() == id) {
                    return itemList.remove(item);
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
