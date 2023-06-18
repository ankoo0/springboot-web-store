package com.project.springbootwebstore.service;

//import com.project.springbootwebstore.model.entity.users.Role;
import com.project.springbootwebstore.entity.users.User;
import com.project.springbootwebstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserByUsername(String username){
//        System.out.println(username);
//         System.out.println(Role.valueOf(username));
        return userRepository.findByUsername(username);
    }


}
