package com.leaflytics.repository;

import com.leaflytics.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data repository for BlogPost.
 */
@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    /** Return posts sorted newest-first. */
    List<BlogPost> findAllByOrderByCreatedAtDesc();
}
