package com.oganic.oganic.blog;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.oganic.oganic.category.CategoryResponse;
import com.oganic.oganic.user.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogResponse {
    private Long id;
    private String title;
    private String content;
    private String imageUrl;

    @JsonFormat(pattern = "MMM d, yyyy")
    private Timestamp updatedAt;
    private UserResponse author;
    private CategoryResponse category;
}
