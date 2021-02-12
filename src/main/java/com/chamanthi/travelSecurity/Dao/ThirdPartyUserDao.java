package com.chamanthi.travelSecurity.Dao;

import com.chamanthi.travelSecurity.entity.User;
import com.chamanthi.travelSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ThirdPartyUserDao {
    @Autowired
    UserRepository userRepository;

    public User saveUser(User user) {

        return userRepository.save(user);
    }
}
