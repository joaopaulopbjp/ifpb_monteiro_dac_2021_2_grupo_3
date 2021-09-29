package com.bookstore.backend.infrastructure.persistence.repository;

import com.bookstore.backend.domain.model.AddressModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Long> {
    
}
