package com.bookstore.backend.infrastructure.persistence.repository;

import com.bookstore.backend.domain.model.InventoryModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryModel, Long> {
    
}
