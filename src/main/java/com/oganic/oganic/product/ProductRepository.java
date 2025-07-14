package com.oganic.oganic.product;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.discount > 0")
    List<Product> findDiscount();

     @Query("SELECT p FROM Product p WHERE " + 
            "(:categoryId IS NULL OR p.category.id = :categoryId) AND " +
            "(:q IS NULL OR p.name LIKE CONCAT('%', :q, '%'))"
     )
    Page<Product> search(@Param("categoryId") Integer categoryId, @Param("q") String q, Pageable pageable);
}
