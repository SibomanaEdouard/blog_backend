package com.test.qTestApp.Auth;//package com.example.demo.Auth;



import com.test.qTestApp.Dto.LoginDto;
import com.test.qTestApp.Models.User;
import com.test.qTestApp.Repository.UserRepository;
import com.test.qTestApp.Response.ApiResponse;
import com.test.qTestApp.Service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ApiResponse<Object> loginUser(LoginDto dto) throws Exception {
        if (dto.getEmail() == null || dto.getPassword() == null) {
            throw new BadRequestException("All credentials are required!");
        }

        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

        if (!auth.isAuthenticated()) {
            throw new BadRequestException("Authentication failed");
        }

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> {
                    return new ChangeSetPersister.NotFoundException();
                });



        var token = jwtService.generateToken(user);
        return ApiResponse.builder()
                .success(true)
                .data(token)
                .message("Thanks for logging in your account")
                .status(HttpStatus.OK)
                .build();

    }

    public ApiResponse<Object> logoutUser(HttpServletRequest request) {
        // Clear the security context
        SecurityContextHolder.clearContext();

        // Get the JWT token from the request header
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            // Invalidate the token
            jwtService.invalidateToken(token);
        }

        return ApiResponse.builder()
                .success(true)
                .data(null)
                .message("Logged out successfully")
                .status(HttpStatus.OK)
                .build();
    }
}

