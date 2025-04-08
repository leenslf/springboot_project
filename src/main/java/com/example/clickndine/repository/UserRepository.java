package com.example.clickndine.repository;

import com.example.clickndine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom method to find a user by their email
    User findByEmail(String email);
}
