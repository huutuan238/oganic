package com.oganic.oganic.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import com.oganic.oganic.product.Product;
import com.oganic.oganic.user.User;


@Repository
public interface CartReponsitory extends JpaRepository<Cart, Long> {
    List<Cart> findByUser(User user);

    Optional<Cart> findFirstByProductAndUser(Product product, User user);
}
