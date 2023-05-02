package com.funtravelapp.main.responseMapper;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
public class ResponseMapper {

    public static ResponseEntity<?> ok(String errorMsg, Object body){
        return ResponseEntity.ok(Response.builder()
                .error(Response.Error.builder()
                        .msg(errorMsg)
                        .build())
                .data(body)
                .build());
    }

    public static ResponseEntity<?> badRequest(String errorMessage, Object body) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.builder()
                .error(Response.Error.builder()
                        .msg(errorMessage)
                        .build())
                .data(body)
                .build());
    }
}
