package com.funtravelapp.main.repository;

import com.funtravelapp.main.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(Integer i);

}