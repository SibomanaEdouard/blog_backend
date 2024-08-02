package com.test.qTestApp.Repository;

import com.test.qTestApp.Models.Post;
import com.test.qTestApp.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    //    @Override
    List<Post> findByAuthor(User author);

    Optional<Post> findByAuthorAndId(User user, UUID id);

}
