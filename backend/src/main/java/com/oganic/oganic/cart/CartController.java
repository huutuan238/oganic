package com.oganic.oganic.cart;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oganic.oganic.exception.ApiResponse;
import com.oganic.oganic.user.User;
import com.oganic.oganic.user.UserService;

@RestController
@RequestMapping("api/carts")
public class CartController {

    @Autowired
    private ModelMapper modelMapper;

    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<CartResponse>> getCarts(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        List<Cart> carts = cartService.getCarts(user);
        List<CartResponse> cartResponses = carts.stream()
                .map(cart -> modelMapper.map(cart, CartResponse.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(cartResponses);
    }

    @PostMapping("/add")
    public ResponseEntity<CartResponse> postMethodName(@RequestBody CartRequest request) {
        Cart cart = cartService.addCart(request);
        CartResponse cartResponse = modelMapper.map(cart, CartResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartResponse);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateCart(@RequestBody List<CartRequest> requests) {
        requests.forEach(request -> cartService.updateCart(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Update success", null));
    }

    @PreAuthorize("isAuthenticated()")
	@DeleteMapping("/remove-cart/{cartId}")
	public ResponseEntity<ApiResponse> removeCartItem(@PathVariable Long cartId) {
		cartService.removeCart(cartId);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, "Remove cart success", null));
	}
}