package com.bookstore.backend.domain.model;

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
public class UserModel {
    
    private Long id;
    private boolean isAdm;
    private String username;
    private String email;
    private String password;
    private List<AddressModel> addressList;
}
