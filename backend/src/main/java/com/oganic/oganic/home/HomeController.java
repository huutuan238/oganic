package com.oganic.oganic.home;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oganic.oganic.category.Category;
import com.oganic.oganic.category.CategoryService;
import com.oganic.oganic.product.Product;
import com.oganic.oganic.product.ProductService;

@Controller
public class HomeController {
	
	private final ProductService productService;
	private final CategoryService categoryService;
	
	HomeController(ProductService productService, CategoryService categoryService) {
		this.productService = productService;
		this.categoryService = categoryService;
	}

	@GetMapping("/")
	public String homepage(Model model) {
		List<Product> products = productService.getProducts();
		List<Product> latestProducts = productService.getLatestProducts();
		List<Category> categories = categoryService.getCategories();
		model.addAttribute("products", products);
		model.addAttribute("latestProducts", latestProducts);
		model.addAttribute("categories", categories);
		return "index";
	}
	
	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}
}
