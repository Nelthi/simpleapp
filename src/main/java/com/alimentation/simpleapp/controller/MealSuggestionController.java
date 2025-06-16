package com.alimentation.simpleapp.controller;

import com.alimentation.simpleapp.model.*;
import com.alimentation.simpleapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class MealSuggestionController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MealRepository mealRepository;
    
    @GetMapping("/{id}/meal-suggestions")
    public ResponseEntity<List<Meal>> suggestMeals(@PathVariable Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) return ResponseEntity.notFound().build();

        User user = userOpt.get();

        // Repas déjà consommés
        Set<Meal> consumedMeals = new HashSet<>(mealRepository.findByUser(user));

        // Repas à proposer : tous sauf ceux déjà consommés (simplifié)
        List<Meal> allMeals = mealRepository.findAll();

        List<Meal> suggestions = allMeals.stream()
            .filter(m -> !consumedMeals.contains(m))
            // Filtrer repas contenant des aliments dangereux (suspectedFoods des allergies)
            .filter(m -> m.getFoods().stream()
                .noneMatch(food -> user.getAllergies().stream()
                    .anyMatch(allergy -> allergy.getSuspectedFoods().contains(food))))
            .limit(10)
            .collect(Collectors.toList());

        return ResponseEntity.ok(suggestions);
    }
}
