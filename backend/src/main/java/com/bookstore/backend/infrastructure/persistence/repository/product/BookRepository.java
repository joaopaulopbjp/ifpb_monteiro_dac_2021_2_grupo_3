package com.bookstore.backend.infrastructure.persistence.repository.product;


import java.util.List;

import com.bookstore.backend.domain.model.category.CategoryModel;
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

    @Query("SELECT book FROM BookModel book WHERE :categoryListToFind IN book.categoryList")
    public Page<BookModel> findByCategoryIdList(@Param("categoryListToFind") List<CategoryModel> categoryListToFind, Pageable pageable);
    
}

