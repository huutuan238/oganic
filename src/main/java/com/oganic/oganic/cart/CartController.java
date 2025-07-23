package com.oganic.oganic.cart;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<Cart>> getCarts() {
        List<Cart> cart = cartService.getCarts();
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
