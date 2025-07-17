package com.oganic.oganic.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oganic.oganic.category.Category;
import com.oganic.oganic.category.CategoryService;

// for thymeleaf
// @Controller
// public class ProductController {

// 	private final ProductService productService;
// 	private final CategoryService categoryService;

// 	ProductController(ProductService productService, CategoryService categoryService) {
// 		this.productService = productService;
// 		this.categoryService = categoryService;
// 	}

// 	@GetMapping("/product")
// 	public String getAllProduct(@RequestParam("page") Optional<Integer> page, Model model) {
// 		Integer pageNumber = page.orElse(1);
// 		Page<Product> pageProduct = productService.getProductsPageable(pageNumber - 1, 2);
// 		List<Product> latestProducts = productService.getLatestProducts();
// 		List<Product> discountProducts = productService.getDiscountProducts();
// 		List<Category> categories = categoryService.getCategories();

// 		model.addAttribute("products", pageProduct.getContent());
// 		model.addAttribute("productTotals", pageProduct.getTotalElements());
// 		model.addAttribute("totalPage", pageProduct.getTotalPages());
// 		model.addAttribute("currentPage", pageNumber);

// 		model.addAttribute("latestProducts", latestProducts);
// 		model.addAttribute("categories", categories);
// 		model.addAttribute("discountProducts", discountProducts);

// 		return "shop-grid";
// 	}

// 	@GetMapping("/product/{id}")
// 	public String getProductDetail(@PathVariable Long id, Model model) {
// 		Product product = productService.getById(id);
// 		model.addAttribute("product", product);
// 		return "shop-details";
// 	}

// 	@GetMapping("/product/search")
// 	public String searchProducts(
// 							@RequestParam(defaultValue = "1") Integer page,
//                            @RequestParam(defaultValue = "2") Integer size,
//                            @RequestParam(required = false) Integer categoryId,
//                            @RequestParam(required = false) String q,
// 			Model model) {
// 		Page<Product> pageProduct = productService.search(page - 1, size, categoryId, q);

// 		List<Category> categories = categoryService.getCategories();
// 		model.addAttribute("categories", categories);

// 		model.addAttribute("products", pageProduct.getContent());
// 		model.addAttribute("productTotals", pageProduct.getTotalElements());
// 		model.addAttribute("totalPage", pageProduct.getTotalPages());
// 		model.addAttribute("currentPage", page);
// 		return "products";
// 	}
// }

// for Vue
@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService productService;
	private final CategoryService categoryService;

	ProductController(ProductService productService, CategoryService categoryService) {
		this.productService = productService;
		this.categoryService = categoryService;
	}

	@GetMapping
	public ResponseEntity<List<Product>> getAllProduct() {
		List<Product> products = productService.getProducts();
		return ResponseEntity.status(HttpStatus.OK).body(products);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductDetail(@PathVariable Long id) {
		Product product = productService.getById(id);
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}

	@GetMapping("/list-products")
	public ResponseEntity<Page<Product>> getAllProduct(@RequestParam("page") Optional<Integer> page) {
		Integer pageNumber = page.orElse(1);
		Page<Product> pageProduct = productService.getProductsPageable(pageNumber - 1, 2);

		return ResponseEntity.status(HttpStatus.OK).body(pageProduct);
	}

	@GetMapping("/product/search")
	public String searchProducts(
							@RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "2") Integer size,
                           @RequestParam(required = false) Integer categoryId,
                           @RequestParam(required = false) String q,
			Model model) {
		Page<Product> pageProduct = productService.search(page - 1, size, categoryId, q);

		List<Category> categories = categoryService.getCategories();
		model.addAttribute("categories", categories);

		model.addAttribute("products", pageProduct.getContent());
		model.addAttribute("productTotals", pageProduct.getTotalElements());
		model.addAttribute("totalPage", pageProduct.getTotalPages());
		model.addAttribute("currentPage", page);
		return "products";
	}
}