package com.oganic.oganic.product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	@Autowired
	private ModelMapper modelMapper;

	private final ProductService productService;

	ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<List<ProductResponse>> getAllProduct() {
		List<Product> products = productService.getProducts();
		List<ProductResponse> productResponses = products.stream()
				.map(product -> modelMapper.map(product, ProductResponse.class))
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(productResponses);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> getProductDetail(@PathVariable Long id) {
		Product product = productService.getById(id);
		ProductResponse productResponse = modelMapper.map(product, ProductResponse.class);
		return ResponseEntity.status(HttpStatus.OK).body(productResponse);
	}

	@GetMapping("/list-products")
	public ResponseEntity<Page<ProductResponse>> getAllProduct(@RequestParam("page") Optional<Integer> page) {
		Integer pageNumber = page.orElse(1);
		Page<Product> pageProduct = productService.getProductsPageable(pageNumber - 1, 3);
		Page<ProductResponse> responsePage = pageProduct
				.map(product -> modelMapper.map(product, ProductResponse.class));
		return ResponseEntity.status(HttpStatus.OK).body(responsePage);
	}

	@GetMapping("/latest-products")
	public ResponseEntity<List<ProductResponse>> getLatestProducts() {
		List<Product> latestProducts = productService.getLatestProducts();
		List<ProductResponse> productResponses = latestProducts.stream()
				.map(product -> modelMapper.map(product, ProductResponse.class))
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(productResponses);
	}

	@GetMapping("/discount")
	public ResponseEntity<List<ProductResponse>> getDiscountProducts() {
		List<Product> discountProducts = productService.getDiscountProducts();
		List<ProductResponse> productResponses = discountProducts.stream()
				.map(product -> modelMapper.map(product, ProductResponse.class))
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(productResponses);
	}

	@GetMapping("/search")
	public ResponseEntity<Page<ProductResponse>> searchProducts(
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "4") Integer size,
			@RequestParam(required = false) Long categoryId,
			@RequestParam(required = false) String q) {
		Page<Product> pageProduct = productService.search(page - 1, size, categoryId, q);
		Page<ProductResponse> responsePage = pageProduct
				.map(product -> modelMapper.map(product, ProductResponse.class));
		return ResponseEntity.status(HttpStatus.OK).body(responsePage);
	}
}