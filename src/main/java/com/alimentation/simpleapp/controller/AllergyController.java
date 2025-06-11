package com.alimentation.simpleapp.controller;

import com.alimentation.simpleapp.model.Allergy;
import com.alimentation.simpleapp.repository.AllergyRepository;
import com.alimentation.simpleapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/allergies")
public class AllergyController {

    @Autowired
    private AllergyRepository allergyRepository;

    @Autowired
    private UserRepository userRepository;

    // 1. Créer une allergie
    @PostMapping
    public ResponseEntity<Allergy> createAllergy(@RequestBody Allergy allergy) {
        try {
            // Vérifie que l'utilisateur existe
            if (allergy.getUser() == null || allergy.getUser().getId() == null || 
                !userRepository.existsById(allergy.getUser().getId())) {
                return ResponseEntity.badRequest().build();
            }

            Allergy savedAllergy = allergyRepository.save(allergy);
            return ResponseEntity.ok(savedAllergy);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // 2. Lister toutes les allergies
    @GetMapping
    public List<Allergy> getAllAllergies() {
        return allergyRepository.findAll();
    }

    // 3. Récupérer une allergie par ID
    @GetMapping("/{id}")
    public ResponseEntity<Allergy> getAllergyById(@PathVariable Long id) {
        Optional<Allergy> allergy = allergyRepository.findById(id);
        return allergy.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    // 4. Mettre à jour une allergie
    @PutMapping("/{id}")
    public ResponseEntity<Allergy> updateAllergy(
            @PathVariable Long id, 
            @RequestBody Allergy allergyDetails) {
        
        return allergyRepository.findById(id)
            .map(existingAllergy -> {
                existingAllergy.setDescription(allergyDetails.getDescription());
                existingAllergy.setDate(allergyDetails.getDate());
                
                // Mise à jour de l'utilisateur si fourni
                if (allergyDetails.getUser() != null) {
                    existingAllergy.setUser(allergyDetails.getUser());
                }
                
                Allergy updatedAllergy = allergyRepository.save(existingAllergy);
                return ResponseEntity.ok(updatedAllergy);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // 5. Supprimer une allergie
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAllergy(@PathVariable Long id) {
        return allergyRepository.findById(id)
            .map(allergy -> {
                allergyRepository.delete(allergy);
                return ResponseEntity.ok().build();
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // 6. Récupérer les allergies d'un utilisateur
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Allergy>> getAllergiesByUser(@PathVariable Long userId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allergyRepository.findByUserId(userId));
    }

    



}