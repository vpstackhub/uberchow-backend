package com.uberchow.repository;

import com.uberchow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndPasswordAndIsAdminTrue(String username, String password);
    Optional<User> findByUsernameAndPasswordAndIsAdminFalse(String username, String password);
    Optional<User> findByEmailAndPassword(String email, String password);

}

