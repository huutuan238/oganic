package com.oganic.oganic.order;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping("/cart")
	public String getCard(Model model) {
		List<OrderDetail> orderDetails = orderService.getCartItems();
		model.addAttribute("cartItems", orderDetails);
		return "shoping-cart";
	}
	
	@GetMapping("/checkout")
	public String checkout(Model model) {
		return "checkout";
	}

	@PostMapping("/add-cart")
	public ResponseEntity<Object> postMethodName(@RequestBody CartRequest cartRequest) {
		orderService.addCart(cartRequest);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
}
