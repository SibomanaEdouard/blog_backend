package com.test.qTestApp.ServiceImpl;

import com.test.qTestApp.Dto.RegisterUserDto;
import com.test.qTestApp.Models.User;
import com.test.qTestApp.Repository.UserRepository;
import com.test.qTestApp.Response.ApiResponse;
import com.test.qTestApp.Service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ApiResponse<Object> createAccount(RegisterUserDto dto) throws Exception {
        try {
            if (dto.getEmail() == null || dto.getFirstname() == null
                    || dto.getLastname() == null || dto.getPassword() == null) {
                throw new Exception("All details are required please!");
            }

            // Check if the account exists
            Optional<User> existingUser = userRepository.findByEmail(dto.getEmail());
            if (existingUser.isPresent()) {
                return ApiResponse.builder()
                        .data("Account already exists. Please login or use another email to continue.")
                        .success(false)
                        .build();
            }

            // Create a new account for user
            User newUser =new  User();
            newUser.setEmail(dto.getEmail());
            newUser.setPassword(passwordEncoder.encode(dto.getPassword()));
            newUser.setFirstname(dto.getFirstname());
            newUser.setLastname(dto.getLastname());


            // Save the new account for user
            User savedUser = userRepository.save(newUser);

            // Return success response
            return ApiResponse.builder()
                    .data(savedUser)
                    .success(true)
                    .build();

        } catch (Exception e) {
            throw new Exception("Internal server error: " + e.getMessage());
        }
    }
}
