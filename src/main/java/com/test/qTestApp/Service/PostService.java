package com.test.qTestApp.Service;

import com.test.qTestApp.Dto.CreateOrUpdatePostDto;
import com.test.qTestApp.Response.ApiResponse;

import java.util.UUID;

public interface PostService {
    ApiResponse<Object> createPost(CreateOrUpdatePostDto dto) throws  Exception;
    ApiResponse<Object> updatePost(UUID id, CreateOrUpdatePostDto dto) throws  Exception;
    ApiResponse<Object> getAllPosts() throws  Exception;

    ApiResponse<Object> deletePost(UUID id) throws Exception;

    ApiResponse<Object> getMyPost(UUID id) throws Exception;

    ApiResponse<Object> getAllMyPosts() throws  Exception;



    ApiResponse<Object> getPost(UUID id) throws Exception;
}
