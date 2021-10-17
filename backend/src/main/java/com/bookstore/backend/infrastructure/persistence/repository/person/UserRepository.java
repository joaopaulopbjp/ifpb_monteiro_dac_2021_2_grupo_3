package com.bookstore.backend.infrastructure.persistence.repository.person;

import com.bookstore.backend.domain.model.user.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    
    public UserModel findByEmail(String email);

    public UserModel findByUsername(String username);

    @Query(value = "SELECT * FROM t_user JOIN (SELECT * FROM t_person JOIN (SELECT person_id FROM t_person_address_join WHERE t_person_address_join.address_id = :addressId) AS retorno ON retorno.person_id = id) AS person ON person.person_id = t_user.id;", nativeQuery = true)
    public UserModel findByAddressId(@Param("addressId") Long addressId);
}
