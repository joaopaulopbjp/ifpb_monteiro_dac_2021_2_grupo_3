package com.bookstore.backend.infrastructure.utils;

import java.util.Optional;

import com.bookstore.backend.domain.model.user.AdminModel;
import com.bookstore.backend.infrastructure.persistence.service.person.AdminRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminVerify {
    
    @Autowired
    private AdminRepositoryService adminRepositoryService;

    public boolean isAdmin(String username) {
        Optional<AdminModel> adminOp = adminRepositoryService.getInstance().findByUsername(username);

        if(adminOp.isPresent())
            return true;
        return false;
    }

}
