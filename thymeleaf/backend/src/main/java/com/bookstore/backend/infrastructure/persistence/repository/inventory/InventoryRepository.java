package com.bookstore.backend.infrastructure.persistence.repository.inventory;

import java.util.Optional;

import com.bookstore.backend.domain.model.inventory.InventoryModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryModel, Long> {
    
    @Query(value = "SELECT * FROM t_inventory JOIN (SELECT inventory_fk FROM t_product WHERE id = :productId) as retorno on retorno.inventory_fk = t_inventory.id",
    nativeQuery = true)
    public Optional<InventoryModel> findByProductId(Long productId);
}
