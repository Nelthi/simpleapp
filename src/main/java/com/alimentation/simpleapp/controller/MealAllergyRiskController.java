package com.alimentation.simpleapp.controller;

import com.alimentation.simpleapp.model.*;
import com.alimentation.simpleapp.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class MealAllergyRiskController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private AllergyRepository allergyRepository;

    @GetMapping("/{id}/meals-allergy-risk")
    public ResponseEntity<Map<Meal, Double>> getMealsAllergyRisk(@PathVariable Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) return ResponseEntity.notFound().build();

        User user = userOpt.get();

        List<Meal> meals = mealRepository.findByUser(user);
        Map<Meal, Double> mealProbabilities = new HashMap<>();

        for (Meal meal : meals) {
            Set<Food> foods = meal.getFoods();
            if (foods.isEmpty()) {
                mealProbabilities.put(meal, 0.0);
                continue;
            }

            double totalProb = 0.0;

            for (Food food : foods) {
                long nbAllergies = allergyRepository.countBySuspectedFoodsContaining(food);
                long nbConsumed = food.getMeals().size();

                double prob = (nbConsumed == 0) ? 0.0 : (double) nbAllergies / nbConsumed;
                totalProb += prob;
            }

            double averageProb = totalProb / foods.size();
            mealProbabilities.put(meal, averageProb);
        }

        return ResponseEntity.ok(mealProbabilities);
    }
}
