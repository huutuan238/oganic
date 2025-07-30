package com.oganic.oganic.blog;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private ModelMapper modelMapper;

	private final BlogService blogService;

	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}

	@GetMapping
	public ResponseEntity<ApiResponse> getBlogs() {
		List<Blog> blogs = blogService.getBlogs();
		List<BlogResponse> blogResponses = blogs.stream()
		.map(blog -> modelMapper.map(blog, BlogResponse.class))
		.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, "Get blogs success", blogResponses));
	}
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getBlogDetail(@PathVariable Long id) {
		Blog blog = blogService.getBlogDetail(id);
		BlogResponse blogResponse = modelMapper.map(blog, BlogResponse.class);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, "Get blog detail success", blogResponse));
	}

}
