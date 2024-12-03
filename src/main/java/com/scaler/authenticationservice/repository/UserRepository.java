package com.scaler.authenticationservice.repository;

import com.scaler.authenticationservice.model.User;
import jakarta.transaction.UserTransaction;
import org.hibernate.boot.model.internal.JPAXMLOverriddenAnnotationReader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Override
    User save(User user);

    Optional<User> findByEmail(String email);

}
