package com.knitwit.repository;

import com.knitwit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByKeycloakLogin(String keycloakLogin);
    User findByKeycloakLogin(String keycloakLogin);
}