package com.bookstore.backend.infrastructure.persistence.repository.image;

import com.bookstore.backend.domain.model.image.ImageModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageModel, Long> {
    
    
}
