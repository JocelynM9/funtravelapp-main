package com.funtravelapp.main.tokenAuth.service;

import com.funtravelapp.main.tokenAuth.dto.GetTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetTokenApi {

    private String mainURL;

    public GetTokenResponse getToken(String authorizationHeader) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, authorizationHeader);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        ResponseEntity<?> result = restTemplate.exchange(this.mainURL + "/", HttpMethod.GET, entity, GetTokenResponse.class);

        if (result.getStatusCode() != HttpStatusCode.valueOf(200)){
            throw new Exception("Unauthorized");
        }

        return (GetTokenResponse) result.getBody();
    }
}
