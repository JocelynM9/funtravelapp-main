package com.funtravelapp.main.tokenAuth.dto;

import com.funtravelapp.main.responseMapper.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetTokenResponse {
    private Response.Error error;
    private GetUserDTO data;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Error {
        private String message;
    }
}
