package com.test.qTestApp.Repository;

import com.test.qTestApp.Models.Comment;
import com.test.qTestApp.Models.Post;
import com.test.qTestApp.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    Optional<Comment> findByAuthorAndPost(User author, Post post);

    List<Comment> findByPost(Post post);
}

