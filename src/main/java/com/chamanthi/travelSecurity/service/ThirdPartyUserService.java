package com.chamanthi.travelSecurity.service;

import com.chamanthi.travelSecurity.Dao.ThirdPartyUserDao;
import com.chamanthi.travelSecurity.Response.SocialUserResponse;
import com.chamanthi.travelSecurity.entity.User;
import com.chamanthi.travelSecurity.helper.ThirdPartyUserHelper;
import com.chamanthi.travelSecurity.repository.RoleRepository;
import com.chamanthi.travelSecurity.repository.UserRepository;
import com.chamanthi.travelSecurity.vo.SocialUser;
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
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dandereddyprasad
 * Used for verifying/registering thrid party logins like facebook google etc.
 */
@Component
public class ThirdPartyUserService {
    Logger logger = LoggerFactory.getLogger(ThirdPartyUserService.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    public ThirdPartyUserHelper thirdPartyUserHelper;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    ThirdPartyUserDao thirdPartyUserDao;
    @Autowired
    SocialUserResponse socialUserResponse;
    public boolean userAvailability(String email) {
        boolean userBool = false;
        User user = userRepository.findByEmail(email);

        if (user != null)
            userBool = true;
        logger.info(email+" User availability verified");
        return userBool;
    }

    public SocialUserResponse generateToken(SocialUser socialUser) {
        String serverUrl = "http://localhost:9001/oauth/token";
        String responseBody = null;

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(serverUrl);


        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
            nameValuePairs.add(new BasicNameValuePair("grant_type", "password"));
            nameValuePairs.add(new BasicNameValuePair("username", socialUser.getsUserEmail()));
            nameValuePairs.add(new BasicNameValuePair("password", "Welcome@01"));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            httpPost.setHeader(HttpHeaders.AUTHORIZATION, socialUser.getsClientId());
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
            HttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            responseBody = EntityUtils.toString(response.getEntity());
            socialUserResponse.setJwtToken(responseBody);
            socialUserResponse.setStatus(statusCode);
            logger.info(socialUser.getsUserEmail()+" User token generated successfully");
        } catch (ClientProtocolException e) {
            logger.error("Exception in requesting request token",e);
        } catch (IOException e) {
            logger.error("Exception in requesting request token",e);
        }
        catch (Exception e){
            logger.error("Exception in requesting  token",e);
        }
        return  socialUserResponse;
    }

    public void registerUser(SocialUser socialUser){

        String hashCode = thirdPartyUserHelper.generateBcryptHash("Welcome@01");

/*        Permission y = new Permission();
        y.setName("can_read_user");
        List<Permission> permissions = new ArrayList();
        permissions.add(y);
        Role x = new Role();
                x.setName("role_user");
                x.setPermissions(permissions);
List<Role> roles = new ArrayList();
roles.add(x);*/


        User user = new User();
        user.setEmail(socialUser.getsUserEmail());
        user.setUserProfileName(socialUser.getsProfileName());
        user.setLoginType(socialUser.getsAuthType());
        user.setCreationDate(System.currentTimeMillis());
        user.setPassword(hashCode);
        user.setEnabled(true);
        user.setRoles(Arrays.asList(roleRepository.findByName("role_prime_user")));
        thirdPartyUserDao.saveUser(user);
        logger.info(socialUser.getsUserEmail()+" User successfully registered");
    }
}
