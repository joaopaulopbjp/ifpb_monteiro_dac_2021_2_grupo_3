package com.bookstore.backend.infrastructure.persistence.repository.person;

import com.bookstore.backend.domain.model.user.UserModel;

import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends PersonRepository {
    
    public UserModel findByEmail(String email);

    public UserModel findByUsername(String username);
}
