package com.bookstore.backend.infrastructure.persistence.repository;

import com.bookstore.backend.domain.model.ItemOrderModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOrderRepository extends JpaRepository<ItemOrderModel, Long> {
    
}
