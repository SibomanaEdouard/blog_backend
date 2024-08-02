package com.test.qTestApp.Service;


import com.test.qTestApp.Dto.RegisterUserDto;
import com.test.qTestApp.Response.ApiResponse;

public interface UserService {
    ApiResponse<Object> createAccount(RegisterUserDto dto) throws  Exception;
}
