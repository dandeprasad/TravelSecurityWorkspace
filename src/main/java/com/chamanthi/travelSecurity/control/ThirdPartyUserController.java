package com.chamanthi.travelSecurity.control;

import com.chamanthi.travelSecurity.Response.SocialUserResponse;
import com.chamanthi.travelSecurity.service.google.GoogleLoginService;
import com.chamanthi.travelSecurity.vo.SocialUser;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void ProcessUser(@RequestBody SocialUser socialUser) throws GeneralSecurityException, IOException {
switch (socialUser.getsAuthType()) {
    case "GOOGLE":
        SocialUserResponse response = googleLoginService.verify(socialUser);
        ResponseEntity.ok().body(response);
}
    }

    @GetMapping("/v1/guest/common")
    public String general() {
        return "common api success";
    }
}
