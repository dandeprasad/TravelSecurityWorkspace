package com.chamanthi.travelSecurity.Response;

import org.springframework.stereotype.Component;

@Component
public class SocialUserResponse {
    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setError(String error) {
        this.error = error;
    }

    String jwtToken;
    int status;
    String error;

}
