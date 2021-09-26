package com.bookstore.backend.entitys;

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
public class User {
    
    private Long id;
    private boolean isAdm;
    private String username;
    private String email;
    private String password;
    private Address address;
}
