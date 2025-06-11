package com.alimentation.simpleapp.repository;

import com.alimentation.simpleapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Trouver un utilisateur par email
    Optional<User> findByEmail(String email);
}
