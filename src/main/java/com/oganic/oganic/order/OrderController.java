package com.oganic.oganic.order;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oganic.oganic.cart.CartRequest;

// @Controller
// public class OrderController {

// 	private final OrderService orderService;

// 	public OrderController(OrderService orderService) {
// 		this.orderService = orderService;
// 	}

// 	@GetMapping("/cart")
// 	public String getCard(Model model) {
// 		List<OrderDetail> orderDetails = orderService.getCartItems();
// 		model.addAttribute("cartItems", orderDetails);
// 		return "shoping-cart";
// 	}

// 	@GetMapping("/checkout")
// 	public String checkout(Model model) {
// 		List<OrderDetail> orderDetails = orderService.getCartItems();
// 		model.addAttribute("cartItems", orderDetails);
// 		return "checkout";
// 	}

// 	@PostMapping("/add-cart")
// 	public ResponseEntity<Order> postMethodName(@RequestBody CartRequest cartRequest) {
// 		Order order = orderService.addCart(cartRequest);
// 		return ResponseEntity.status(HttpStatus.CREATED).body(order);
// 	}

// 	@DeleteMapping("remove-cart/{orderId}")
// 	public ResponseEntity<Object> removeCartItem(@PathVariable Long orderId) {
// 		orderService.removeCartItem(orderId);
// 		return ResponseEntity.status(HttpStatus.OK).body(orderId);
// 	}

// }

@RestController
@RequestMapping("api/orders")
public class OrderController {

	private final OrderService orderService;
	ModelMapper modelMapper = new ModelMapper();

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	// @GetMapping
	// public ResponseEntity<List<CartDetailResponse>> getCard() {
	// 	List<OrderDetail> orderDetails = orderService.getCartItems();
	// 	List<CartDetailResponse> cartDetailResponses = orderDetails.stream()
	// 			.map(order -> modelMapper.map(order, CartDetailResponse.class))
	// 			.collect(Collectors.toList());
	// 	return ResponseEntity.status(HttpStatus.OK).body(cartDetailResponses);
	// }

	@GetMapping("/checkout")
	public String checkout(Model model) {
		List<OrderDetail> orderDetails = orderService.getCartItems();
		model.addAttribute("cartItems", orderDetails);
		return "checkout";
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/checkout")
	public ResponseEntity<Order> saveOrder(@RequestBody CartRequest cartRequest) {
		Order order = orderService.checkout(cartRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(order);
	}

}
