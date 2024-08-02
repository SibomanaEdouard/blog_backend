package com.test.qTestApp.Controller;


import com.test.qTestApp.Auth.AuthService;
import com.test.qTestApp.Dto.LoginDto;
import com.test.qTestApp.Dto.RegisterUserDto;
import com.test.qTestApp.Response.ApiResponse;
import com.test.qTestApp.ServiceImpl.UserServiceImpl;
import com.test.qTestApp.utils.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/users")
public class UserControllers {
    private final UserServiceImpl userServiceImpl;
    private final AuthService authService;

    //this is to register user
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Object>> createNewCompany(@Valid @RequestBody RegisterUserDto dto) throws Exception {
        Object ob = userServiceImpl.createAccount(dto);
        return ResponseHandler.success(ob, HttpStatus.CREATED);
    }

    //this is to login the user
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Object>> loginUser(@Valid @RequestBody LoginDto dto) throws Exception{
        Object ob = authService.loginUser(dto);
        return ResponseHandler.success(ob, HttpStatus.OK);
    }
}
