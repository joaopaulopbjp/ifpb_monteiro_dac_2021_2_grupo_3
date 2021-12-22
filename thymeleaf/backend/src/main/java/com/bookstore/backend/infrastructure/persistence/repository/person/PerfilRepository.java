package com.bookstore.backend.infrastructure.persistence.repository.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.backend.domain.model.user.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long>{

	
}
