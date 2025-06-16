package com.alimentation.simpleapp.controller;

import com.alimentation.simpleapp.model.*;
import com.alimentation.simpleapp.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserAllergyRiskController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AllergyRepository allergyRepository;

    @GetMapping("/allergy-risk")
    public ResponseEntity<List<User>> getUsersWithHighAllergyRisk(@RequestParam double threshold) {
        List<User> users = userRepository.findAll();
        List<User> filteredUsers = new ArrayList<>();

        for (User user : users) {
            boolean hasHighRisk = user.getMeals().stream().anyMatch(meal ->
                meal.getFoods().stream().anyMatch(food -> {
                    double probability = calculateAllergyProbability(food);
                    System.out.println("User " + user.getId() + ", Food: " + food.getName() + ", Probability: " + probability);
                    return probability > threshold;
                })
            );

            if (hasHighRisk) {
                filteredUsers.add(user);
            }
        }

        return ResponseEntity.ok(filteredUsers);
    }

    // ⚠️ Méthode interne pour calculer la probabilité d'allergie d'un aliment
    private double calculateAllergyProbability(Food food) {
        long nbAllergies = allergyRepository.countBySuspectedFoodsContains(food);
        long nbConsumed = food.getMeals() != null ? food.getMeals().size() : 0;

        if (nbConsumed == 0) return 0.0;

        return (double) nbAllergies / nbConsumed;
    }
}
