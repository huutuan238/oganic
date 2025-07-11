package com.oganic.oganic.product;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class ProductController {

	private final ProductService productService;

	ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/product")
	public String getAllProduct(Model model) {
		List<Product> products = productService.getProducts();
		model.addAttribute("products", products);
		return "shop-grid";
	}
	
	@GetMapping("/product/{id}")
	public String getProductDetail(@PathVariable Integer id, Model model) {
		model.addAttribute("product", "product");
		return "shop-details";
	}
}
