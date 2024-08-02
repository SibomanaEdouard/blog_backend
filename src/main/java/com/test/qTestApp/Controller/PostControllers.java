package com.test.qTestApp.Controller;

import com.test.qTestApp.Dto.CreateOrUpdatePostDto;
import com.test.qTestApp.Response.ApiResponse;
import com.test.qTestApp.ServiceImpl.PostServiceImpl;
import com.test.qTestApp.utils.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/posts")

public class PostControllers {
    private final PostServiceImpl postServiceImpl;

    //this is the controller for creating new post
    @PostMapping()
    public ResponseEntity<ApiResponse<Object>> createPost(@Valid @RequestBody CreateOrUpdatePostDto dto) throws Exception {
        Object ob = postServiceImpl.createPost(dto);
        return ResponseHandler.success(ob, HttpStatus.CREATED);
    }

    //this is to get the list of my posts
    @GetMapping()
    public ResponseEntity<ApiResponse<Object>> getAllMyPost() throws Exception {
        Object ob = postServiceImpl.getAllMyPosts().getData();
        return ResponseHandler.success(ob, HttpStatus.OK);
    }

    //this is to get my post by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> getMyPost(@PathVariable UUID id) throws Exception {
        Object ob = postServiceImpl.getMyPost(id);
        return ResponseHandler.success(ob, HttpStatus.OK);
    }

    //all posts
    @GetMapping("/all-posts")
    public ResponseEntity<ApiResponse<Object>> getAllPosts() throws Exception {
        Object ob = postServiceImpl.getAllPosts().getData();
        return ResponseHandler.success(ob, HttpStatus.OK);
    }

    //this is to update my post
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> updatePost(@Valid @RequestBody CreateOrUpdatePostDto dto,@PathVariable UUID id) throws Exception {
        Object ob = postServiceImpl.updatePost(id, dto);
        return ResponseHandler.success(ob, HttpStatus.CREATED);
    }


    //this is to delete my post
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteMyPost(@PathVariable UUID id) throws Exception {
        Object ob = postServiceImpl.deletePost(id);
        return ResponseHandler.success(ob, HttpStatus.OK);
    }


}
