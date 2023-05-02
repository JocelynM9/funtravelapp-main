package com.funtravelapp.main.authenticationservice.repository;

import com.funtravelapp.main.authenticationservice.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Integer> {

    Boolean existsByUserId(Integer id);

    @Query("SELECT ut FROM UserToken ut WHERE ut.userId = ?1")
    UserToken findUserTokenByUserId(Integer id);
}


