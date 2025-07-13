package com.oganic.oganic.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String getAllProduct(@RequestParam("page") Optional<Integer> page,  Model model) {
		Integer pageNumber = page.orElse(1);
		Page<Product> pageProducts = productService.getProductsPageable(pageNumber - 1,2);
		List<Product> latestProducts = productService.getLatestProducts();
		List<Product> discountProducts = productService.getDiscountProducts();
		List<Category> categories = categoryService.getCategories();

		model.addAttribute("products", pageProducts.getContent());
		model.addAttribute("productTotals", pageProducts.getTotalElements());
		model.addAttribute("latestProducts", latestProducts);
		model.addAttribute("categories", categories);
		model.addAttribute("discountProducts", discountProducts);
	
		return "shop-grid";
	}
	
	@GetMapping("/product/{id}")
	public String getProductDetail(@PathVariable Long id, Model model) {
		Product product = productService.getById(id);
		model.addAttribute("product", product);
		return "shop-details";
	}
}
