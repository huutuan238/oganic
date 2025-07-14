package com.oganic.oganic.order;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.oganic.oganic.product.Product;
import com.oganic.oganic.product.ProductService;

@Controller
public class OrderController {

	private final ProductService productService;

	public OrderController(ProductService productService) {
		this.productService = productService;
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
}
