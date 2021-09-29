package com.bookstore.backend.infrastructure.persistence.repository;

import com.bookstore.backend.domain.model.PublishingCompanyModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishingCompanyRepository extends JpaRepository<PublishingCompanyModel, Long> {
    
}
