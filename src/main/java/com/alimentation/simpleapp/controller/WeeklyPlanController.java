package com.alimentation.simpleapp.controller;

import com.alimentation.simpleapp.model.*;
import com.alimentation.simpleapp.repository.*;

import com.alimentation.simpleapp.dto.WeeklyPlanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class WeeklyPlanController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MealRepository mealRepository;

    @GetMapping("/{id}/weekly-plan1")
    public ResponseEntity<WeeklyPlanDTO> generateWeeklyPlan(@PathVariable Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) return ResponseEntity.notFound().build();

        User user = userOpt.get();

        // Simplification : On choisit des repas faibles calories non allergènes
        List<Meal> allMeals = mealRepository.findAll();

        List<Meal> filteredMeals = new ArrayList<>();
        for (Meal meal : allMeals) {
            boolean safe = meal.getFoods().stream()
                .noneMatch(food -> user.getAllergies().stream()
                    .anyMatch(allergy -> allergy.getSuspectedFoods().contains(food)));
            if (safe) filteredMeals.add(meal);
        }

        // On génère un plan simple : 7 repas différents
        List<Meal> weeklyMeals = filteredMeals.stream().limit(7).toList();

        WeeklyPlanDTO plan = new WeeklyPlanDTO(user.getId(), weeklyMeals);

        return ResponseEntity.ok(plan);
    }
}
