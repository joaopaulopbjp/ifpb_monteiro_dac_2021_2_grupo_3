package com.bookstore.backend.infrastructure.persistence.repository.person;

import java.util.Optional;

import com.bookstore.backend.domain.model.user.AdminModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminModel, Long> {

    public Optional<AdminModel> findByEmail(String email);

    public Optional<AdminModel> findByUsername(String username);
}
