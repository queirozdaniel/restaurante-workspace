package com.danielqueiroz.restauranteoauth.domain.service;

import com.danielqueiroz.restauranteoauth.api.feingclients.UserFeingClient;
import com.danielqueiroz.restauranteoauth.domain.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private static Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserFeingClient userFeingClient;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userFeingClient.findByEmail(email).getBody();
        if (user == null ) {
            LOG.error("Email not found" + email);
            throw new UsernameNotFoundException("Email not found");
        }
        return user;
    }
}
