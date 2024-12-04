package com.scaler.authenticationservice.repository;

import com.scaler.authenticationservice.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Session save(Session session);
}