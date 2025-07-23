package com.oganic.oganic.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartReponsitory extends JpaRepository<Cart, Long> {
    
}
