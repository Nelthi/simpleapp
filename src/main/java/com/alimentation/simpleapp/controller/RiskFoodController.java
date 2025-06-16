package com.alimentation.simpleapp.controller;

import com.alimentation.simpleapp.model.*;
import com.alimentation.simpleapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class RiskFoodController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}/dangerous-foods")
    public ResponseEntity<Set<Food>> getDangerousFoods(@PathVariable Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) return ResponseEntity.notFound().build();

        User user = userOpt.get();

        Set<Food> dangerousFoods = new HashSet<>();
        // Récupérer toutes les allergies de l'utilisateur
        user.getAllergies().forEach(allergy -> {
            dangerousFoods.addAll(allergy.getSuspectedFoods());
        });

        return ResponseEntity.ok(dangerousFoods);
    }
}
