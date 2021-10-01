package com.bookstore.backend.infrastructure.persistence.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@Repository
@NoRepositoryBean
public interface HqRepository extends JpaRepository<HqRepository, Long> {
        
}

