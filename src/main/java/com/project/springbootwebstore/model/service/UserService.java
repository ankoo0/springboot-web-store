package com.project.springbootwebstore.model.service;

//import com.project.springbootwebstore.model.entity.users.Role;
import com.project.springbootwebstore.model.entity.users.User;
import com.project.springbootwebstore.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public Optional<User> getUserByUsername(String username){
//        System.out.println(username);
//         System.out.println(Role.valueOf(username));
        return userRepository.findByUsername(username);
    }


}
