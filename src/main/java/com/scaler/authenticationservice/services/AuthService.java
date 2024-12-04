package com.scaler.authenticationservice.services;

import com.scaler.authenticationservice.exceptions.UserAlreadyExistException;
import com.scaler.authenticationservice.exceptions.WrongPasswordException;
import com.scaler.authenticationservice.model.Session;
import com.scaler.authenticationservice.model.User;
import com.scaler.authenticationservice.repository.SessionRepository;
import com.scaler.authenticationservice.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

@Service

public class AuthService {
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
     private BCryptPasswordEncoder bCryptPasswordEncoder;
    SecretKey secretKey = Jwts.SIG.HS256.key().build();

    public AuthService(UserRepository userRepository, SessionRepository sessionRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
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
    public String login(String email,String password) throws WrongPasswordException {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()){
            throw new UsernameNotFoundException("User With email "+email+" does not exist ");
        }
       boolean matches= bCryptPasswordEncoder.matches(
                password,
                userOptional.get().getPassword()
        );

        if (matches) {
            String token =  createJwtToken(userOptional.get().getId(),
                    new ArrayList<>(),
                    userOptional.get().getEmail());

            Session session = new Session();
            session.setToken(token);
            session.setUser(userOptional.get());

            Calendar calendar = Calendar.getInstance();
            Date currentDate = calendar.getTime();

            calendar.add(Calendar.DAY_OF_MONTH, 30);
            Date datePlus30Days = calendar.getTime();
            session.setExpriringAt(datePlus30Days);

            sessionRepository.save(session);

            return token;
        } else {
            throw new WrongPasswordException("Wrong password.");
        }

    }


    public boolean validate(String token){
        try {
            Jws<Claims> claimsJws= Jwts.parser().verifyWith(secretKey)
                    .build().parseSignedClaims(token);

            Date expiry = claimsJws.getPayload().getExpiration();
            Long user_id =claimsJws.getPayload().get("user_id",Long.class);
        }catch (Exception e){
            return false;
        }




       return true;
    }

    private String createJwtToken(Integer userId, List<String> roles, String email){
        Map<String, Object> dataJwt = new HashMap<>();
        dataJwt.put("userId",userId);
        dataJwt.put("Roles",roles);
        dataJwt.put("email",email);

        Calendar calendar = Calendar.getInstance();
        Date currentDate= calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH,30);
        Date datePlus30 = calendar.getTime();



        return Jwts.builder().claims(dataJwt)
                .issuer("Priyanka")
                .expiration(datePlus30)
                .signWith(secretKey)
                .issuedAt(new Date()).compact();



    }
}
