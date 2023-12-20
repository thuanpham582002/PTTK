package com.thuan.pttk.security;

import com.thuan.pttk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.thuan.pttk.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceCustom implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).get();
        if (user == null) {
            var exception = new UsernameNotFoundException("Username not found");
            exception.printStackTrace();
            throw exception;
        } else {
            UserDetailsCustom userDetailsCusom = new UserDetailsCustom(user);
            return userDetailsCusom;
        }
    }
}
