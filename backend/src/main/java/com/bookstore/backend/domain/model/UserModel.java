package com.bookstore.backend.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name = "T_USER")
public class UserModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "IS_ADMIN", nullable = false)
    private boolean isAdm;
    @Column(name = "USERNAME", nullable = false)
    private String username;
    @Column(name = "EMAIL", nullable = false)
    private String email;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<AddressModel> addressList;

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
