package com.funtravelapp.main.authenticationservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_token")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserToken {

    @Id
    @Column(name = "token_id")
    private Integer tokenId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "token")
    private String token;

    @Column(name = "expired_time")
    private LocalDateTime expiredTime;

    @Column(name = "is_expired")
    private boolean expired;

}
