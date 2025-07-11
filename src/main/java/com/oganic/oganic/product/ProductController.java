package com.oganic.oganic.product;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.oganic.oganic.category.Category;
import com.oganic.oganic.category.CategoryService;


@Controller
public class ProductController {

	private final ProductService productService;
	private final CategoryService categoryService;

	ProductController(ProductService productService, CategoryService categoryService) {
		this.productService = productService;
		this.categoryService = categoryService;
	}
	
	@GetMapping("/product")
	public String getAllProduct(Model model) {
		List<Product> products = productService.getProducts();
		List<Product> latestProducts = productService.getLatestProducts();
		List<Product> discountProducts = productService.getDiscountProducts();
		List<Category> categories = categoryService.getCategories();

		model.addAttribute("products", products);
		model.addAttribute("latestProducts", latestProducts);
		model.addAttribute("categories", categories);
		model.addAttribute("discountProducts", discountProducts);
	
		return "shop-grid";
	}
	
	@GetMapping("/product/{id}")
	public String getProductDetail(@PathVariable Integer id, Model model) {
		model.addAttribute("product", "product");
		return "shop-details";
	}
}
