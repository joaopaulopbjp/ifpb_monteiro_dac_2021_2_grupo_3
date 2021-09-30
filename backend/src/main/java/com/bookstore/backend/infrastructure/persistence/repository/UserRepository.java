package com.bookstore.backend.infrastructure.persistence.repository;

import com.bookstore.backend.domain.model.user.PersonModel;
import com.bookstore.backend.domain.model.user.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<PersonModel, Long> {
    
    public UserModel findByEmail(String email);
}
