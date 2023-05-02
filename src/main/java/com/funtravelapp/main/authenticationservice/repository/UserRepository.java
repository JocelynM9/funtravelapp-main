package com.funtravelapp.main.authenticationservice.repository;

import com.funtravelapp.main.authenticationservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsernameAndPassword(String username, String password);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User findByUsername(String username);
}
