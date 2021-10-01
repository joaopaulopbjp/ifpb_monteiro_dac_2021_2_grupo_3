package com.bookstore.backend.infrastructure.persistence.repository.person;

import com.bookstore.backend.domain.model.user.AdminModel;
import com.bookstore.backend.domain.model.user.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminModel, Long> {
    
    public UserModel findByEmail(String email);

    public UserModel findByUsername(String username);
}
