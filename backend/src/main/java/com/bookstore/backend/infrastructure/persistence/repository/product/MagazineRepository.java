package com.bookstore.backend.infrastructure.persistence.repository.product;

import com.bookstore.backend.domain.model.product.MagazineModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MagazineRepository extends JpaRepository<MagazineModel, Long> {
    @Query("SELECT magazine FROM MagazineModel hq WHERE magazine.inventory.amount > 0")
    public Page<MagazineModel> findAllIgnoreInventoryUnavailable(Pageable pageable);
}

