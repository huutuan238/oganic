package com.oganic.oganic.order;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.oganic.oganic.product.Product;
import com.oganic.oganic.product.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class OrderController {

	private final ProductService productService;
	private final OrderService orderService;

	public OrderController(ProductService productService, OrderService orderService) {
		this.productService = productService;
		this.orderService = orderService;
	}
	
	@GetMapping("/cart")
	public String getCard(Model model) {
		List<Product> products = productService.getProducts();
		model.addAttribute("cartProducts", products);
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
