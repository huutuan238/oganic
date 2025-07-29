package com.oganic.oganic.product;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> getRelatedProducts(Long categoryId) {
        String sql = "select p from Product p where p.category.id = :categoryId";
        return entityManager.createQuery(sql, Product.class)
                .setParameter("categoryId", categoryId)
                .setMaxResults(4).getResultList();
    }
}
