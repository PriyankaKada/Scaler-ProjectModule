package com.scaler.authenticationservice.exceptions;

import com.scaler.authenticationservice.repository.UserRepository;

public class UserAlreadyExistException extends Exception{

    public UserAlreadyExistException(String message){
        super(message);
    }
}
