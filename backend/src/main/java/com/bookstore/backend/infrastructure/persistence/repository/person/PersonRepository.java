package com.bookstore.backend.infrastructure.persistence.repository.person;

import com.bookstore.backend.domain.model.user.PersonModel;
import com.bookstore.backend.domain.model.user.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PersonRepository extends JpaRepository<PersonModel, Long> {
    
    public UserModel findByEmail(String email);

    public UserModel findByUsername(String username);
}
