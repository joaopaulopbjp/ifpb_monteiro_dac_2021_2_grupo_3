package com.bookstore.backend.infrastructure.persistence.repository.person;

import java.util.Optional;

import com.bookstore.backend.domain.model.user.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    
    public Optional<UserModel> findByEmail(String email);

    public Optional<UserModel> findByUsername(String username);

    @Query(value = "SELECT * FROM t_user JOIN (SELECT * FROM t_person JOIN (SELECT person_id FROM t_person_address_join WHERE t_person_address_join.address_id = :addressId) AS retorno ON retorno.person_id = id) AS person ON person.person_id = t_user.id;", nativeQuery = true)
    public Optional<UserModel> findByAddressId(@Param("addressId") Long addressId);

    @Query(value = "SELECT * FROM t_user JOIN (SELECT * FROM t_person JOIN (SELECT person_id FROM t_person_product_for_sale_join WHERE t_person_product_for_sale_join.product_for_sale_id = :productId) AS retorno ON retorno.person_id = id) AS person ON person.person_id = t_user.id;", nativeQuery = true)
    public Optional<UserModel> findByProductId(@Param("productId") Long productId);

    @Query(value = "SELECT * FROM t_user JOIN (SELECT * FROM t_person JOIN (SELECT person_id FROM t_person_evaluate_join WHERE t_person_evaluate_join.evaluate_id = :userId) AS retorno ON retorno.person_id = id) AS person ON person.person_id = t_user.id;",nativeQuery = true)
    public Optional<UserModel> findbyEvaluateId(@Param("userId") Long userId);
}
