package com.test.qTestApp.ServiceImpl;



import com.test.qTestApp.Dto.CreateOrUpdatePostDto;
import com.test.qTestApp.Dto.PostResponseDto;
import com.test.qTestApp.Models.Post;
import com.test.qTestApp.Models.User;
import com.test.qTestApp.Repository.PostRepository;
import com.test.qTestApp.Repository.UserRepository;
import com.test.qTestApp.Response.ApiResponse;
import com.test.qTestApp.Response.UserResponse;
import com.test.qTestApp.Service.PostService;
import com.test.qTestApp.utils.GetLoggedUser;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final GetLoggedUser getLoggedUser;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public ApiResponse<Object> createPost(CreateOrUpdatePostDto dto) throws Exception {
        try {
            // Check if the user is logged in
            UserResponse userResponse = getLoggedUser.getLoggedUser();
            if (userResponse == null) {
                return ApiResponse.builder()
                        .data("Login to continue.....")
                        .success(false)
                        .build();
            }

            // Validate the input data
            validateInput(dto);

            // Fetch the User entity by userResponse.getId()
            Optional<User> optionalUser = userRepository.findById(userResponse.getId());
            if (!optionalUser.isPresent()) {
                return ApiResponse.builder()
                        .data("User not found")
                        .success(false)
                        .build();
            }

            User user = optionalUser.get();

            // Convert DTO to entity
            Post newPost = new Post();
            newPost.setTitle(dto.getTitle());
            newPost.setContent(dto.getContent());
            newPost.setAuthor(user);

            // Save the created post
            Post savedPost = postRepository.save(newPost);

            // Return success response
            return ApiResponse.builder()
                    .data(savedPost)
                    .success(true)
                    .message("Post is successfully posted")
                    .build();

        } catch (Exception e) {
            throw new Exception("Internal server error: " + e.getMessage());
        }
    }

    // Validate the input
    private void validateInput(CreateOrUpdatePostDto dto) throws BadRequestException {
        if (dto.getContent() == null || dto.getTitle() == null) {
            throw new BadRequestException("Please type the title and content of the post");
        }
    }

    @Override
    public ApiResponse<Object> updatePost(UUID id, CreateOrUpdatePostDto dto) throws Exception {
        try {
            // Check if the user is logged in
            UserResponse userResponse = getLoggedUser.getLoggedUser();
            if (userResponse == null) {
                return ApiResponse.builder()
                        .data("Login to continue...")
                        .success(false)
                        .build();
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

            // Find the post by author and ID
            Optional<Post> optionalPost = postRepository.findByAuthorAndId(user, id);
            if (optionalPost.isEmpty()) {
                return ApiResponse.builder()
                        .message("Post not found")
                        .success(false)
                        .build();
            }

            Post post = optionalPost.get();

            // Update the post details
            post.setTitle(dto.getTitle());
            post.setContent(dto.getContent());

            // Save the updated post
            Post updatedPost = postRepository.save(post);

            // Return success response
            return ApiResponse.builder()
                    .data(updatedPost)
                    .success(true)
                    .message("Post is successfully updated")
                    .build();

        } catch (Exception e) {
            throw new Exception("Internal server error: " + e.getMessage(), e);
        }
    }


    @Override
    public ApiResponse<Object> deletePost(UUID id) throws Exception {
        try {
            // Check if the user is logged in
            UserResponse userResponse = getLoggedUser.getLoggedUser();
            if (userResponse == null) {
                return ApiResponse.builder()
                        .data("Login to continue.....")
                        .success(false)
                        .build();
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

            // Find the post by author and id
            Optional<Post> optionalPost = postRepository.findByAuthorAndId(user, id);
            if (!optionalPost.isPresent()) {
                return ApiResponse.builder()
                        .data("Post not found")
                        .success(false)
                        .build();
            }

            Post post = optionalPost.get();

            // Delete the post
            postRepository.delete(post);

            // Return success response
            return ApiResponse.builder()
                    .message("Post is successfully deleted")
                    .success(true)
                    .build();

        } catch (Exception e) {
            throw new Exception("Internal server error: " + e.getMessage());
        }
    }


    @Override
    public ApiResponse<Object> getMyPost(UUID id) throws Exception {
        try {
            // Check if the user is logged in
            UserResponse userResponse = getLoggedUser.getLoggedUser();
            if (userResponse == null) {
                return ApiResponse.builder()
                        .data("Login to continue.....")
                        .success(false)
                        .build();
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

            // Find the post by author and id
            Optional<Post> optionalPost = postRepository.findByAuthorAndId(user, id);
            if (!optionalPost.isPresent()) {
                return ApiResponse.builder()
                        .message("Post not found")
                        .success(false)
                        .build();
            }

            Post myPost = optionalPost.get();

            // Return success response
            return ApiResponse.builder()
                    .data(myPost)
                    .success(true)
                    .build();

        } catch (Exception e) {
            throw new Exception("Internal server error: " + e.getMessage());
        }
    }


    @Override
    public ApiResponse<Object> getAllMyPosts() throws Exception {
        try {
            // Check if the user is logged in
            UserResponse userResponse = getLoggedUser.getLoggedUser();
            if (userResponse == null) {
                return ApiResponse.builder()
                        .data("Login to continue...")
                        .success(false)
                        .build();
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

            // Find all posts by the logged user
            List<Post> posts = postRepository.findByAuthor(user);

            // Return success response
            return ApiResponse.builder()
                    .data(posts)
                    .success(true)
                    .build();

        } catch (Exception e) {
            throw new Exception("Internal server error: " + e.getMessage());
        }
    }


    @Override
    public ApiResponse<Object> getAllPosts() throws Exception {
        try {
            // Find all posts
            List<Post> posts = postRepository.findAll();

            // Convert posts to PostResponseDto
            List<PostResponseDto> postResponseDtos = posts.stream().map(post -> {
                PostResponseDto dto = new PostResponseDto();
                dto.setId(post.getId());
                dto.setTitle(post.getTitle());
                dto.setContent(post.getContent());
                dto.setUsername(post.getAuthor().getFirstname()+ " "+post.getAuthor().getLastname());
                return dto;
            }).collect(Collectors.toList());

            // Return success response
            return ApiResponse.builder()
                    .data(postResponseDtos)
                    .success(true)
                    .build();

        } catch (Exception e) {
            throw new Exception("Internal server error: " + e.getMessage());
        }
    }


    @Override
    public ApiResponse<Object> getPost(UUID id) throws Exception {
        try {
            // Find the post by ID
            Optional<Post> optionalPost = postRepository.findById(id);
            if (!optionalPost.isPresent()) {
                return ApiResponse.builder()
                        .data("Post not found")
                        .success(false)
                        .build();
            }

            Post post = optionalPost.get();

            // Return success response
            return ApiResponse.builder()
                    .data(post)
                    .success(true)
                    .build();

        } catch (Exception e) {
            throw new Exception("Internal server error: " + e.getMessage());
        }
    }
}
