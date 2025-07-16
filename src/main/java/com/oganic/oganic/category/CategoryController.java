package com.oganic.oganic.category;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    
	private final CategoryService categoryService;
	
	CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
    @GetMapping
    public ResponseEntity<List<Category>> getMethodName() {
        List<Category> categories = categoryService.getCategories();
        return ResponseEntity.status(HttpStatus.CREATED).body(categories);
    }
    
}
