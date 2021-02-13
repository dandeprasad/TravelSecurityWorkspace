package com.chamanthi.travelSecurity.control;

import com.chamanthi.travelSecurity.Response.SocialUserResponse;
import com.chamanthi.travelSecurity.entity.User;
import com.chamanthi.travelSecurity.repository.UserRepository;
import com.chamanthi.travelSecurity.service.google.GoogleLoginService;
import com.chamanthi.travelSecurity.vo.SocialUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.security.GeneralSecurityException;


@RestController
public class ThirdPartyUserController {
    @Autowired
    GoogleLoginService googleLoginService;


    @PostMapping(path = "/api/public/user", consumes =MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SocialUserResponse> ProcessUser(@RequestBody SocialUser socialUser) throws GeneralSecurityException, IOException {
switch (socialUser.getsAuthType()) {
    case "GOOGLE":
        SocialUserResponse response = googleLoginService.verify(socialUser);
        return new ResponseEntity<SocialUserResponse>(response, HttpStatus.OK);
}
        return null;
    }

    @GetMapping(value = "/api/public/common", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SocialUserResponse> general() {

        SocialUserResponse response = new SocialUserResponse();
        response.setJwtToken("sfdgdfg");
        response.setStatus(222);
        response.setError("");
        String x = "sgsfgdfg";
        return new ResponseEntity<SocialUserResponse>(response, HttpStatus.OK);
    }
}
