package com.chamanthi.travelSecurity.helper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ThirdPartyUserHelper {
    public String generateBcryptHash(String name) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return "{bcrypt}" + encoder.encode(name);

    }
}
