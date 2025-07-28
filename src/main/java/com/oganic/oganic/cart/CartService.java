package com.oganic.oganic.cart;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oganic.oganic.product.Product;
import com.oganic.oganic.product.ProductRepository;
import com.oganic.oganic.user.User;
import com.oganic.oganic.user.UserRepository;

@Service
public class CartService {

    private CartReponsitory cartReponsitory;
    private ProductRepository productRepository;
    private UserRepository userRepository;

    public CartService(CartReponsitory cartReponsitory, ProductRepository productRepository,
            UserRepository userRepository) {
        this.cartReponsitory = cartReponsitory;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<Cart> getCarts(User user) {
        return cartReponsitory.findByUser(user);
    }

    public Cart addCart(CartRequest cartRequest) {
        User user = userRepository.findById(cartRequest.getUserId()).orElseThrow();
        Product product = productRepository.findById(cartRequest.getProductId()).orElseThrow();
        Optional<Cart> existCart = cartReponsitory.findFirstByProductAndUser(product, user);
        if (existCart.isPresent()) {
            Cart cart = existCart.get();
            Integer newQuatity = cart.getQuatity() + cartRequest.getQuatity();
            cart.setQuatity(newQuatity);
            return cartReponsitory.save(cart);
        } else {
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setProduct(product);
            cart.setQuatity(cartRequest.getQuatity());
            return cartReponsitory.save(cart);
        }
    }

    public Cart updateCart(CartRequest cartRequest) {
        Cart cart = cartReponsitory.findById(cartRequest.getId()).orElseThrow();
        cart.setQuatity(cartRequest.getQuatity());
        cart = cartReponsitory.save(cart);
        return cart;
    }
}
