package com.bookstore.backend.infrastructure.persistence.repository.company;

import java.util.List;

import com.bookstore.backend.domain.model.company.PublishingCompanyModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishingCompanyRepository extends JpaRepository<PublishingCompanyModel, Long> {

    public List<PublishingCompanyModel> findByName(String name);
}
