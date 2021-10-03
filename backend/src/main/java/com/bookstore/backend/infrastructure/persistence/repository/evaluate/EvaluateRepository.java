package com.bookstore.backend.infrastructure.persistence.repository.evaluate;

import com.bookstore.backend.domain.model.evaluation.EvaluateModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluateRepository extends JpaRepository<EvaluateModel, Long> {
    
}
