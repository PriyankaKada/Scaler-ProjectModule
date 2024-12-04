package com.scaler.authenticationservice.exceptions;

public class WrongPasswordException extends Exception {
    public WrongPasswordException(String entredPasswordIsWrong) {
        super(entredPasswordIsWrong);
    }
}
