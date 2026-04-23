package com.leaflytics.service;

import com.leaflytics.entity.BlogPost;
import com.leaflytics.repository.BlogPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Business logic for the blog system.
 */
@Service
@RequiredArgsConstructor
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;

    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAllByOrderByCreatedAtDesc();
    }

    public BlogPost getPostById(Long id) {
        return blogPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog post not found with id: " + id));
    }

    public BlogPost createPost(BlogPost post) {
        // Auto-generate a short excerpt from the first 180 characters of content
        if (post.getExcerpt() == null || post.getExcerpt().isBlank()) {
            String raw = post.getContent().replaceAll("\\s+", " ").trim();
            post.setExcerpt(raw.length() > 180 ? raw.substring(0, 177) + "…" : raw);
        }
        return blogPostRepository.save(post);
    }

    public void deletePost(Long id) {
        blogPostRepository.deleteById(id);
    }
}
