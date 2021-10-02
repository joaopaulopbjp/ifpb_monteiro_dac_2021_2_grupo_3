package com.bookstore.backend.infrastructure.persistence.repository.sale;

import com.bookstore.backend.domain.model.sale.shoppingCartModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<shoppingCartModel, Long> {
    
}
