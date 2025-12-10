package com.example.TripTailor.service;

import com.example.TripTailor.entity.User;
import com.example.TripTailor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(User user){
        System.out.println(user);
        String encodedPw = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPw);
        //user.setUserCd(UUID.randomUUID().toString().replace("-", "").substring(0, 12));
        user.setRole("ROLE_USER");
        user.setProvider("Local");
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(String userCd){
        userRepository.deleteByUserCd(userCd);
    }
}
