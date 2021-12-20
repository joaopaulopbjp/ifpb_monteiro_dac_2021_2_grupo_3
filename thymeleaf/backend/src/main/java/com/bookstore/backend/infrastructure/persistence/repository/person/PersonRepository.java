package com.bookstore.backend.infrastructure.persistence.repository.person;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//import com.bookstore.backend.domain.model.user.AdminModel;
import com.bookstore.backend.domain.model.user.PersonModel;
//import com.bookstore.backend.domain.model.user.UserModel;

@Repository
public interface PersonRepository extends JpaRepository<PersonModel, Long>{

	@Query("SELECT p FROM PersonModel p JOIN p.perfils pls WHERE pls.perfil LIKE '%ADMIN%'")
	List<PersonModel> findAllAdmin();

	Optional<PersonModel> findByUsername(String username);


	
}
