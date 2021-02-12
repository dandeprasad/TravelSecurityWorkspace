package com.chamanthi.travelSecurity.service.google;

import com.chamanthi.travelSecurity.Response.SocialUserResponse;
import com.chamanthi.travelSecurity.entity.User;
import com.chamanthi.travelSecurity.service.ThirdPartyUserService;
import com.chamanthi.travelSecurity.vo.SocialUser;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class GoogleLoginService {
    Logger logger = LoggerFactory.getLogger(GoogleLoginService.class);

    private static GoogleLoginService googleLoginService;
    @Autowired
    private ThirdPartyUserService thirdPartyUserService;

    public static GoogleLoginService newInstance() {
        if (googleLoginService == null) {
            googleLoginService = new GoogleLoginService();
        }
        return googleLoginService;
    }

    /**
     * @param socialUser
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public SocialUserResponse verify(SocialUser socialUser) throws GeneralSecurityException, IOException {
        SocialUserResponse socialUserResponse = null;
        try{
        logger.info("Inside Google Service verification");
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        NetHttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList("161704558794-3k4mo1dlfpn4inr2la9i84bp51vkgqt8.apps.googleusercontent.com"))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();

// (Receive idTokenString by HTTPS POST)

        GoogleIdToken idToken = verifier.verify(socialUser.getsTokenId());
        if (idToken != null) {
            logger.info("Google Payload is not null,authentication successful");
            Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            logger.debug("User ID: " + userId);
            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");
            logger.debug("Google User Email after authentication"+email);
            socialUser.setsUserEmail(email);
            socialUser.setsProfileName(name);
            socialUser.setsPictureUrl(pictureUrl);


            // Use or store profile information
            // ...
            if (thirdPartyUserService.userAvailability(email)) {
                socialUserResponse =thirdPartyUserService.generateToken(socialUser);
                logger.info(socialUser.getsUserEmail()+" User token sent successfully");
            } else {
                thirdPartyUserService.registerUser(socialUser);
                socialUserResponse = thirdPartyUserService.generateToken(socialUser);
                logger.info(socialUser.getsUserEmail()+" User token sent successfully");
            }


        } else {
            logger.info("Invalid ID token.");
        }
    }
    catch(Exception ex) {
        logger.error("Exception Inside Google Service verification "+ex.getMessage(), ex);
    }
        return socialUserResponse;
    }
}
