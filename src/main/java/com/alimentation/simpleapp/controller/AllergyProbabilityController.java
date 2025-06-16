package com.alimentation.simpleapp.controller;

import com.alimentation.simpleapp.model.Food;
import com.alimentation.simpleapp.repository.FoodRepository;
import com.alimentation.simpleapp.repository.AllergyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/foods")
public class AllergyProbabilityController {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private AllergyRepository allergyRepository;

    @GetMapping("/{id}/allergy-probability")
    public ResponseEntity<Double> getAllergyProbability(@PathVariable Long id) {
        Optional<Food> foodOpt = foodRepository.findById(id);
        if (foodOpt.isEmpty()) return ResponseEntity.notFound().build();

        Food food = foodOpt.get();

        long nbAllergies = allergyRepository.countBySuspectedFoodsContains(food);
        long nbConsumed = food.getMeals().size(); // Si relation inverse Meal->Food configurée

        if (nbConsumed == 0) return ResponseEntity.ok(0.0);

        double probability = (double) nbAllergies / nbConsumed;

        return ResponseEntity.ok(probability);
    }

  
}
