package com.test.qTestApp.Service;

import com.test.qTestApp.Dto.CreateOrUpdateCommentDto;
import com.test.qTestApp.Dto.CreateOrUpdatePostDto;
import com.test.qTestApp.Models.Post;
import com.test.qTestApp.Models.User;
import com.test.qTestApp.Response.ApiResponse;

import java.util.UUID;

public interface CommentService {
    ApiResponse<Object> createComment(UUID post, CreateOrUpdateCommentDto dto) throws  Exception;
    ApiResponse<Object> getComment(UUID postId) throws  Exception;

    ApiResponse<Object> updateComment(UUID commentId,CreateOrUpdateCommentDto dto) throws  Exception;

    ApiResponse<Object> deleteComment(UUID commentId) throws  Exception;

}
