package com.oganic.oganic.cart;

import java.util.List;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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

    public CartService(CartReponsitory cartReponsitory, ProductRepository productRepository, UserRepository userRepository) {
        this.cartReponsitory = cartReponsitory;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<Cart> getCarts() {
        return cartReponsitory.findAll();
    }

    public Cart addCart(CartRequest cartRequest) {
        Cart cart = new Cart();
        User user = userRepository.findById(cartRequest.getUserId()).orElseThrow();
        Product product = productRepository.findById(cartRequest.getProductId()).orElseThrow();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuatity(cartRequest.getQuatity());
        return cartReponsitory.save(cart);
    }
}
