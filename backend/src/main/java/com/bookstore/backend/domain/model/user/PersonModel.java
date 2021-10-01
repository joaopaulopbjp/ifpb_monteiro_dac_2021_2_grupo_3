package com.bookstore.backend.domain.model.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bookstore.backend.domain.model.AddressModel;
import com.bookstore.backend.domain.model.sale.UserSaleHistoryModel;

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
@Table(name = "T_PERSON")
@Inheritance(strategy = InheritanceType.JOINED)
public class PersonModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "USERNAME", nullable = false)
    private String username;
    @Column(name = "EMAIL", nullable = false)
    private String email;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "person")
    private List<AddressModel> addressList;
    @OneToOne(mappedBy = "person")
    @JoinColumn(name = "SALE_HISTORY_ID")
    private UserSaleHistoryModel saleHistory;

    public boolean addAddressToAddressList(AddressModel addressModel) {
        if(addressList != null) {
            addressList.add(addressModel);
        } else {
            addressList = new ArrayList<>();
            addAddressToAddressList(addressModel);
        }
        return true;
    }

    public boolean removeAddressFromAddressList(AddressModel addressModel) {
        if(addressList != null) {
            return addressList.remove(addressModel);
        } 
        return false;
    }
}
