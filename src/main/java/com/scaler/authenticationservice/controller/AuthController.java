package com.scaler.authenticationservice.controller;

import com.scaler.authenticationservice.dto.*;
import com.scaler.authenticationservice.exceptions.WrongPasswordException;
import com.scaler.authenticationservice.model.User;
import com.scaler.authenticationservice.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }
    @PostMapping("/sign_up")
    public ResponseEntity<SignupResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        SignupResponseDto signupResponseDto = new SignupResponseDto();
        try {
            if (authService.signUp(signUpRequestDto.getEmail(),signUpRequestDto.getPassword())){
                signupResponseDto.setRequestStatus(RequestStatus.SUCCESS);
            }else {
                signupResponseDto.setRequestStatus(RequestStatus.FAILED);
            }
            return  new ResponseEntity<>(signupResponseDto,HttpStatus.OK);

        }catch (Exception e){
            signupResponseDto.setRequestStatus(RequestStatus.FAILED);
            return  new ResponseEntity<>(signupResponseDto,HttpStatus.CONFLICT);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> signUp(@RequestBody LoginRequestDto loginRequestDto) throws WrongPasswordException {
        LoginResponseDto loginResponseDto = new LoginResponseDto();

        try
        {
            String token = authService.login(loginRequestDto.getEmail(),loginRequestDto.getPassword());

            if(authService.validate(token)) {

                loginResponseDto.setRequestStatus(RequestStatus.SUCCESS);

                MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
                headers.add("AUTH_Token", token);

                return new ResponseEntity<>(
                        loginResponseDto, headers, HttpStatus.OK
                );
            }else {
                loginResponseDto.setRequestStatus(RequestStatus.FAILED);
                return new ResponseEntity<>(
                        loginResponseDto, HttpStatus.BAD_REQUEST
                );
            }
        }catch (UsernameNotFoundException | WrongPasswordException e){
            loginResponseDto.setRequestStatus(RequestStatus.FAILED);
            return new ResponseEntity<>(
                    loginResponseDto, HttpStatus.BAD_REQUEST
            );

        }


    }


}
