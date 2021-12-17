package com.bookstore.backend.infrastructure.persistence.repository.person;

import java.util.Optional;

import com.bookstore.backend.domain.model.user.AdminModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminModel, Long> {

    public Optional<AdminModel> findByEmail(String email);

    public Optional<AdminModel> findByUsername(String username);

    @Query(value = "SELECT * FROM t_admin JOIN (SELECT * FROM t_person JOIN (SELECT person_id FROM t_person_address_join WHERE t_person_address_join.address_id = :addressId) AS retorno ON retorno.person_id = id) AS person ON person.person_id = t_admin.id;", nativeQuery = true)
    public Optional<AdminModel> findByAddressId(@Param("addressId") Long addressId);
}
