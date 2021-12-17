package com.bookstore.backend.presentation.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CredentialResponse {
    private String token;
    private boolean isAdmin;
}
