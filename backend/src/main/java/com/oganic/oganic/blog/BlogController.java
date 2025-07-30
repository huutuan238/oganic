package com.oganic.oganic.blog;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oganic.oganic.exception.ApiResponse;


@RestController
@RequestMapping("/api/blog")
public class BlogController {
	private final BlogService blogService;

	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}

	@GetMapping
	public ResponseEntity<ApiResponse> getBlogs() {
		List<Blog> blogs = blogService.getBlogs();
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, "Get blogs success", blogs));
	}
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getBlogDetail(@PathVariable Long id) {
		Blog blog = blogService.getBlogDetail(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, "Get blog detail success", blog));
	}

}
