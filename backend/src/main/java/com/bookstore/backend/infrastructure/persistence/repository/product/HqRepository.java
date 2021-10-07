package com.bookstore.backend.infrastructure.persistence.repository.product;

import com.bookstore.backend.domain.model.product.HqModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HqRepository extends JpaRepository<HqModel, Long> {
    @Query("SELECT hq FROM HqModel hq WHERE hq.inventory.amount > 0")
    public Page<HqModel> findAllIgnoreInventoryUnavailable(Pageable pageable);
}

