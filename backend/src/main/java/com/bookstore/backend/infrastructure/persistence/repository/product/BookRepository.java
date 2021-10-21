package com.bookstore.backend.infrastructure.persistence.repository.product;


import java.util.List;

import com.bookstore.backend.domain.model.product.BookModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {
    
    @Query("SELECT book FROM BookModel book WHERE book.inventory.amount > 0")
    public Page<BookModel> findAllIgnoreInventoryUnavailable(Pageable pageable);
    
    public List<BookModel> findByTitle(String title);

    @Query(value = "SELECT * FROM t_book JOIN (SELECT DISTINCT * FROM t_product JOIN (SELECT product_id FROM t_product_category_join WHERE t_product_category_join.category_id IN :categoryIdListToFind) AS retorno ON retorno.product_id = id) as product on product.id = t_book.id;", 
    nativeQuery = true)
    public List<BookModel> findByCategoryIdList(@Param("categoryIdListToFind") List<Long> categoryIdListToFind);

    @Query(value = "select * from t_book join (select * from t_product JOIN (SELECT product_id from t_product_author_join WHERE author_id = :authorId) AS products_id on product_id=id) as products on products.id=t_book.id ", nativeQuery = true)
    public List<BookModel> findByAuthorId(@Param("authorId") Long authorId);

    @Query(value = "select * from t_book join (select * from t_product JOIN (SELECT product_id from t_product_category_join WHERE category_id = :categoryId) AS products_id on product_id=id) as products on products.id=t_book.id", nativeQuery = true)
    public List<BookModel> findByCategoryId(@Param("categoryId") Long categoryId);
}