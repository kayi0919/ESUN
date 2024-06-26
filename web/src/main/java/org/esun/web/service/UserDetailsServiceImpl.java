package org.esun.web.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.esun.database.entity.User;
import org.esun.database.repository.UserRepository;
import org.esun.web.entity.SessionData;

@Log4j2
public class UserDetailsServiceImpl implements UserDetailsService {

    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("username: {}", username);
        User user = userRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        log.debug("user: {}", user);
        SessionData sessionData = new SessionData();
        sessionData.setUsername(user.getUsername());

        sessionData.setPassword(passwordEncoder.encode(user.getPassword()));
        //sessionData.setPassword(user.getPassword());
        return sessionData;
    }
}