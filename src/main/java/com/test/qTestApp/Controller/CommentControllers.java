package com.test.qTestApp.Controller;


import com.test.qTestApp.Dto.CreateOrUpdateCommentDto;
import com.test.qTestApp.Repository.UserRepository;
import com.test.qTestApp.Response.ApiResponse;
import com.test.qTestApp.ServiceImpl.CommentServiceImpl;
import com.test.qTestApp.utils.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/comment") //the base path for the end points
public class CommentControllers {
 private final CommentServiceImpl commentServiceImpl;


 //this is for create the comment
    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> createComment(@PathVariable UUID id , @Valid @RequestBody CreateOrUpdateCommentDto dto) throws Exception {
        Object ob = commentServiceImpl.createComment(id,dto);
        return ResponseHandler.success(ob, HttpStatus.CREATED);
    }

    //this is to get the comment associated to the post
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> getComment(@PathVariable UUID id ) throws Exception {
        Object ob = commentServiceImpl.getComment(id).getData();
        return ResponseHandler.success(ob, HttpStatus.CREATED);
    }

    //this the controller to update the comment
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteComment(@PathVariable UUID id) throws Exception {
        Object ob = commentServiceImpl.deleteComment(id).getMessage();
        return ResponseHandler.success(ob, HttpStatus.CREATED);
    }

    //this is the controller to delete my comment
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> updateComment(@PathVariable UUID id , @Valid @RequestBody CreateOrUpdateCommentDto dto) throws Exception {
        Object ob = commentServiceImpl.updateComment(id,dto).getData();
        return ResponseHandler.success(ob, HttpStatus.CREATED);
    }
}

