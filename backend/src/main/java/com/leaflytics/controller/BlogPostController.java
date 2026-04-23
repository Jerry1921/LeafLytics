package com.leaflytics.controller;

import com.leaflytics.entity.BlogPost;
import com.leaflytics.service.BlogPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST controller for the simple blog system.
 *
 * Base URL: /api/blogs
 */
@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogPostController {

    private final BlogPostService blogPostService;

    // ── GET /api/blogs ───────────────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<List<BlogPost>> getAllPosts() {
        return ResponseEntity.ok(blogPostService.getAllPosts());
    }

    // ── GET /api/blogs/{id} ──────────────────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(blogPostService.getPostById(id));
    }

    // ── POST /api/blogs ──────────────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<BlogPost> createPost(@Valid @RequestBody BlogPost post) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(blogPostService.createPost(post));
    }

    // ── DELETE /api/blogs/{id} ───────────────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletePost(@PathVariable Long id) {
        blogPostService.deletePost(id);
        return ResponseEntity.ok(Map.of("message", "Post deleted"));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(Map.of("error", ex.getMessage()));
    }
}
