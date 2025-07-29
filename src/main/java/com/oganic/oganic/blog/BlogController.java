package com.oganic.oganic.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {
	
	@GetMapping("/blog")
	public String blog(Model model) {
		
		return "blog";
	}
	
	@GetMapping("/blog-details")
	public String blogDetails(Model model) {
		
		return "blog-details";
	}
}
