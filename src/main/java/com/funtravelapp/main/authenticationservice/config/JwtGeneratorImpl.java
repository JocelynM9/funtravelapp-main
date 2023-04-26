package com.funtravelapp.main.authenticationservice.config;

import com.funtravelapp.main.authenticationservice.dto.LoginInputDTO;
import com.funtravelapp.main.authenticationservice.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtGeneratorImpl implements JwtGeneratorInterface{

    @Value("${jwt.secret}")
    private String secret;

    public static final long JWT_TOKEN_VALIDITY = 10*60*60;


    @Override
    public String generateToken(User user) {

        String jwtToken= Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000))
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();
//        Map<String, String> jwtTokenGen = new HashMap<>();
//        jwtTokenGen.put("token", jwtToken);
//        jwtTokenGen.put("message", message);
        return jwtToken;
    }
}
