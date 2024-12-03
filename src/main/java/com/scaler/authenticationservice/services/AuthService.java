package com.scaler.authenticationservice.services;

import com.scaler.authenticationservice.exceptions.UserAlreadyExistException;
import com.scaler.authenticationservice.model.User;
import com.scaler.authenticationservice.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class AuthService {
    private final UserRepository userRepository;
     private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public boolean  signUp(String email, String password) throws UserAlreadyExistException {

        if (userRepository.findByEmail(email).isPresent()){
           throw  new UserAlreadyExistException("User with email:"+email +" already present");
        }else {
            User user = new User();
            user.setEmail(email);
            user.setPassword(bCryptPasswordEncoder.encode(password));

            userRepository.save(user);
        }
        return true;
    }
    public String login(String email,String password){
        return "token";
    }
}
