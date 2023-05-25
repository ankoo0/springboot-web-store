package com.project.springbootwebstore.security;

import com.project.springbootwebstore.model.repository.UserRepository;
import com.project.springbootwebstore.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

//   private UserService userService;
//
//   @Autowired
//    public CustomUserDetailsService(UserService userService) {
//        this.userService = userService;
//    }
    @Autowired
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = (User) CustomUserDetails.mapUserDetails(userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("No Such User")));
        System.out.println(user.getUsername() + "===========" + user.getPassword());
        return CustomUserDetails.mapUserDetails(userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("No Such User")));
    }
}
