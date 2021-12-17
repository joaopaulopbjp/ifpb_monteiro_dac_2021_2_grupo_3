package com.bookstore.backend.infrastructure.persistence.repository.author;

import java.util.List;

import com.bookstore.backend.domain.model.author.AuthorModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorModel, Long> {

    public List<AuthorModel> findByName(String name);
}
