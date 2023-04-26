package com.funtravelapp.main.authenticationservice.repository;

import com.funtravelapp.main.authenticationservice.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, String> {
}
