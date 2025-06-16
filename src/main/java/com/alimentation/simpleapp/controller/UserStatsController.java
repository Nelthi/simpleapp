package com.alimentation.simpleapp.controller;

import com.alimentation.simpleapp.model.*;
import com.alimentation.simpleapp.repository.*;
import com.alimentation.simpleapp.dto.UserStatsDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserStatsController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MealRepository mealRepository;

    @GetMapping("/{id}/stats")
    public ResponseEntity<UserStatsDTO> getUserStats(@PathVariable Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) return ResponseEntity.notFound().build();

        User user = userOpt.get();

        List<Meal> meals = mealRepository.findByUser(user);

        int totalMeals = meals.size();

        double avgCalories = meals.stream()
            .flatMap(m -> m.getFoods().stream())
            .mapToDouble(f -> f.getCalories())
            .average()
            .orElse(0.0);

        Map<String, Long> ingredientCounts = meals.stream()
            .flatMap(m -> m.getFoods().stream())
            .flatMap(f -> f.getIngredients().stream())
            .collect(Collectors.groupingBy(i -> i.getName(), Collectors.counting()));

        List<String> topIngredients = ingredientCounts.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(5)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        Map<String, Long> foodCounts = meals.stream()
            .flatMap(m -> m.getFoods().stream())
            .collect(Collectors.groupingBy(Food::getName, Collectors.counting()));

        List<String> topFoods = foodCounts.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(5)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        UserStatsDTO stats = new UserStatsDTO(totalMeals, avgCalories, topIngredients, topFoods);

        return ResponseEntity.ok(stats);
    }
}

