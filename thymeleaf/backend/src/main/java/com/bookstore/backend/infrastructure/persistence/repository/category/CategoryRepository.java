package com.bookstore.backend.infrastructure.persistence.repository.category;

import java.util.List;

import com.bookstore.backend.domain.model.category.CategoryModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
    
    public List<CategoryModel> findByName(String name);
}
