package com.funtravelapp.main.responseMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private Error error;

    private Object data;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Error{
        private String msg;
    }
}
