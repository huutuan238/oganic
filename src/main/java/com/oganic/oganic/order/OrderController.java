package com.oganic.oganic.order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
	
	@GetMapping("/cart")
	public String getCard(Model model) {
		return "shoping-cart";
	}
	
	@GetMapping("/checkout")
	public String checkout(Model model) {
		return "checkout";
	}
}
