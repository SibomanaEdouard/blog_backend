package com.test.qTestApp.ServiceImpl;

import com.test.qTestApp.Dto.CreateOrUpdateCommentDto;
import com.test.qTestApp.Dto.CreateOrUpdatePostDto;
import com.test.qTestApp.Models.Comment;
import com.test.qTestApp.Models.Post;
import com.test.qTestApp.Models.User;
import com.test.qTestApp.Repository.CommentRepository;
import com.test.qTestApp.Repository.PostRepository;
import com.test.qTestApp.Repository.UserRepository;
import com.test.qTestApp.Response.ApiResponse;
import com.test.qTestApp.Response.UserResponse;
import com.test.qTestApp.Service.CommentService;
import com.test.qTestApp.utils.GetLoggedUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final GetLoggedUser getLoggedUser;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

//    @Transactional
    @Override
    public ApiResponse<Object> createComment(UUID postId, CreateOrUpdateCommentDto dto) throws Exception {
        try {
            // Retrieve the currently logged-in user
            UserResponse userResponse = getLoggedUser.getLoggedUser();
            if (userResponse == null) {
                throw new Exception("Login to continue ...");
            }

            // Fetch the User entity by userResponse.getId()
            Optional<User> optionalUser = userRepository.findById(userResponse.getId());
            if (!optionalUser.isPresent()) {
                return ApiResponse.builder()
                        .data("User not found")
                        .success(false)
                        .build();
            }

            User user = optionalUser.get(); // Logged-in user is the author of the comment

            // Fetch the Post entity by postId from the method parameter
            Optional<Post> optionalPost = postRepository.findById(postId);
            if (!optionalPost.isPresent()) {
                return ApiResponse.builder()
                        .message("Post not found")
                        .success(false)
                        .build();
            }

            Post post = optionalPost.get(); // The post to which the comment is being added

            // Validate the comment content
            if (dto.getContent() == null || dto.getContent().trim().isEmpty()) {
                return ApiResponse.builder()
                        .data("Please provide the body of your comment")
                        .success(false)
                        .build();
            }

            // Create and save the new Comment entity
            Comment comment = new Comment();
            comment.setAuthor(user);
            comment.setContent(dto.getContent());
            comment.setPost(post);
            Comment savedComment = commentRepository.save(comment);


            // Return a successful response
            return ApiResponse.builder()
                    .message("Your comment has been successfully added to the post")
                    .data(savedComment)
                    .success(true)
                    .build();

        } catch (Exception e) {
            // Provide more detailed error information
            throw new Exception("Internal server error: " + e.getMessage(), e);
        }
    }


    @Override
    public ApiResponse<Object> getComment(UUID postId) throws Exception {
        try {
            // Fetch the Post entity by postId
            Optional<Post> optionalPost = postRepository.findById(postId);
            if (!optionalPost.isPresent()) {
                return ApiResponse.builder()
                        .data("Post not found")
                        .success(false)
                        .build();
            }

            // Retrieve all comments associated with the Post
            List<Comment> comments = commentRepository.findByPost(optionalPost.get());

            // Return a successful response with the comments
            return ApiResponse.builder()
                    .message("Comments retrieved successfully")
                    .data(comments)
                    .success(true)
                    .build();
        } catch (Exception e) {
            throw new Exception("Internal server error ...", e.getCause());
        }
    }

    //this the controller for updating the post
    @Override
    public ApiResponse<Object> updateComment(UUID commentId, CreateOrUpdateCommentDto dto) throws Exception {
        try {
            // Retrieve the currently logged-in user
            UserResponse userResponse = getLoggedUser.getLoggedUser();
            if (userResponse == null) {
                throw new Exception("Login to continue ...");
            }

            // Fetch the User entity by userResponse.getId()
            Optional<User> optionalUser = userRepository.findById(userResponse.getId());
            if (!optionalUser.isPresent()) {
                return ApiResponse.builder()
                        .data("User not found")
                        .success(false)
                        .build();
            }

            User user = optionalUser.get();


            // Fetch the Comment entity by commentId
            Optional<Comment> optionalComment = commentRepository.findById(commentId);
            if (!optionalComment.isPresent()) {
                return ApiResponse.builder()
                        .data("Comment not found")
                        .success(false)
                        .build();
            }

            Comment comment = optionalComment.get();
            //this is to check if the logged use is the author of the comment
            if(user.getId()!=comment.getAuthor().getId()){
                throw new Exception("You can only update your comment");

            }
            // Update the comment's content
            if (!dto.getContent().isEmpty()) {
                comment.setContent(dto.getContent());
                Comment updatedComment = commentRepository.save(comment);

                // Return a successful response
                return ApiResponse.builder()
                        .message("Comment updated successfully")
                        .data(updatedComment)
                        .success(true)
                        .build();
            } else {
                return ApiResponse.builder()
                        .data("Content cannot be empty")
                        .success(false)
                        .build();
            }
        } catch (Exception e) {
            throw new Exception("Internal server error ...", e.getCause());
        }
    }



    @Override
    public ApiResponse<Object> deleteComment(UUID commentId) throws Exception {
        try {
            // Retrieve the currently logged-in user
            UserResponse userResponse = getLoggedUser.getLoggedUser();
            if (userResponse == null) {
                throw new Exception("Login to continue ...");
            }

            // Fetch the User entity by userResponse.getId()
            Optional<User> optionalUser = userRepository.findById(userResponse.getId());
            if (!optionalUser.isPresent()) {
                return ApiResponse.builder()
                        .data("User not found")
                        .success(false)
                        .build();
            }
            // Fetch the Comment entity by commentId
            Optional<Comment> optionalComment = commentRepository.findById(commentId);
            if (!optionalComment.isPresent()) {
                return ApiResponse.builder()
                        .data("Comment not found")
                        .success(false)
                        .build();
            }
            User user=optionalUser.get();
            //this is to check if the logged user is the owner of the commet
            if(user!=optionalComment.get().getAuthor()){
                throw new Exception("You can only delete your own comment");
            }

            // Delete the comment
            commentRepository.deleteById(commentId);

            // Return a successful response
            return ApiResponse.builder()
                    .message("Comment deleted successfully")
                    .success(true)
                    .build();
        } catch (Exception e) {
            throw new Exception("Internal server error ...", e.getCause());
        }
    }
}
