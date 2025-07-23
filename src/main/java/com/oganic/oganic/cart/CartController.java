package com.oganic.oganic.cart;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oganic.oganic.user.User;
import com.oganic.oganic.user.UserService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("api/carts")
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<Cart>> getCarts(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        List<Cart> cart = cartService.getCarts(user);
        return ResponseEntity.status(HttpStatus.OK).body(cart);
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> postMethodName(@RequestBody CartRequest request) {
        Cart cart = cartService.addCart(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }

    @PostMapping("/update")
    public ResponseEntity<Cart> updateCart(@RequestBody CartRequest request) {
        Cart cart = cartService.addCart(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }
    
}
