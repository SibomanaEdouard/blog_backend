package com.test.qTestApp.Controller;

import com.test.qTestApp.Auth.AuthService;
import com.test.qTestApp.Dto.LoginDto;
import com.test.qTestApp.Dto.RegisterUserDto;
import com.test.qTestApp.Response.ApiResponse;
import com.test.qTestApp.ServiceImpl.UserServiceImpl;
import com.test.qTestApp.utils.GetLoggedUser;
import com.test.qTestApp.utils.ResponseHandler;
import com.test.qTestApp.Exceptions.UnauthorisedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/users")
public class UserControllers {
    private final UserServiceImpl userServiceImpl;
    private final AuthService authService;
    private final GetLoggedUser getLoggedUser;

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

    //this is for logout
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Object>> logoutUser(HttpServletRequest request) {
        ApiResponse<Object> response = authService.logoutUser(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    //this is to get the current logged-in user
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<Object>> getCurrentUser() {
        try {
            Object user = getLoggedUser.getLoggedUser();
            return ResponseHandler.success(user, HttpStatus.OK);
        } catch (UnauthorisedException e) {
            return ResponseHandler.error(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseHandler.error("User not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseHandler.error("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}