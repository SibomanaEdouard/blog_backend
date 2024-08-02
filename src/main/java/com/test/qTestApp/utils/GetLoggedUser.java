package com.test.qTestApp.utils;



import java.util.Optional;

import com.test.qTestApp.Exceptions.JwtExpiredException;
import com.test.qTestApp.Exceptions.UnauthorisedException;
import com.test.qTestApp.Models.User;
import com.test.qTestApp.Repository.UserRepository;
import com.test.qTestApp.Response.UserResponse;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GetLoggedUser {
    private final UserRepository userRepository;

    public UserResponse getLoggedUser() throws Exception {
        try {
            if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
                throw new UnauthorisedException(("You are not logged in"));
            }

            String email;
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal instanceof UserDetails) {
                email = ((UserDetails) principal).getUsername();
            } else {
                email = principal.toString();
            }

            Optional<User> user = userRepository.findByEmail(email);
            UserResponse u;
            if (!user.isPresent()) {
                throw new ChangeSetPersister.NotFoundException();
            } else {

                u = UserResponse.builder()
                        .firstname(user.get().getFirstname())
                        .email(user.get().getEmail())
                        .lastname(user.get().getLastname())
                        .id(user.get().getId())
                        .build();
            }
            return u;
        } catch (JwtExpiredException e) {
            throw new JwtExpiredException("Jwt expired: " + e.getMessage());
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new ChangeSetPersister.NotFoundException();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
}
