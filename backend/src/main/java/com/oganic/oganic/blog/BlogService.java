package com.oganic.oganic.blog;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> getBlogs() {
        return blogRepository.findAll();
    }

    public Blog getBlogDetail(Long id) {
        return blogRepository.findById(id).orElse(null);
    }
}
