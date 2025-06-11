package com.alimentation.simpleapp.controller;

import com.alimentation.simpleapp.model.User;
import com.alimentation.simpleapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // 1. Créer un utilisateur
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User savedUser = userRepository.save(user);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // créer plusieurs utilisateurs à la fois

    @PostMapping("/bulk")
    public ResponseEntity<List<User>> createUsers(@RequestBody List<User> users) {
        try {
            List<User> savedUsers = userRepository.saveAll(users);
            return ResponseEntity.ok(savedUsers);
    } catch (Exception e) {
            return ResponseEntity.badRequest().build();
    }
}

    // 2. Lire tous les utilisateurs
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 3. Lire un utilisateur par ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok)
                 .orElse(ResponseEntity.notFound().build());
    }

    // 4. Mettre à jour un utilisateur
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userRepository.findById(id)
            .map(existingUser -> {
                existingUser.setName(userDetails.getName());
                existingUser.setEmail(userDetails.getEmail());
                existingUser.setAge(userDetails.getAge());
                existingUser.setWeight(userDetails.getWeight());
                existingUser.setHealthInfo(userDetails.getHealthInfo());
                User updatedUser = userRepository.save(existingUser);
                return ResponseEntity.ok(updatedUser);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // 5. Supprimer un utilisateur
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userRepository.findById(id)
            .map(user -> {
                userRepository.delete(user);
                return ResponseEntity.ok().build();
            })
            .orElse(ResponseEntity.notFound().build());
    }
}