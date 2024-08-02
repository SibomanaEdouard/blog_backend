package com.test.qTestApp.Repository;


import com.test.qTestApp.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
//    @Override
    Optional<User> findByEmail(String email);
}
