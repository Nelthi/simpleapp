package com.alimentation.simpleapp.service;

import com.alimentation.simpleapp.model.Food;
import com.alimentation.simpleapp.model.Meal;
import com.alimentation.simpleapp.model.User;
import com.alimentation.simpleapp.repository.MealRepository;
import com.alimentation.simpleapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class MealPlanningService {

    private final UserRepository userRepository;
    private final MealRepository mealRepository;
    private final NutritionCalculator nutritionCalculator;

    public MealPlanningService(UserRepository userRepository,
                               MealRepository mealRepository,
                               NutritionCalculator nutritionCalculator) {
        this.userRepository = userRepository;
        this.mealRepository = mealRepository;
        this.nutritionCalculator = nutritionCalculator;
    }

    public List<Meal> generateWeeklyPlan(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        List<Meal> weeklyMeals = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            LocalDateTime date = LocalDate.now().plusDays(i).atStartOfDay();

            // Recommandation d'aliments pour un jour donné
            Set<Food> recommendedFoods = nutritionCalculator.recommendFoods(user);

            Meal meal = new Meal();
            meal.setUser(user);
            meal.setDate(date);
            meal.setFoods(recommendedFoods);

            mealRepository.save(meal); //uutiliser pour enregistrer les repas

            weeklyMeals.add(meal);
        }

        return weeklyMeals;
    }

    public List<Meal> getWeeklyMeals(Long userId) {
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = start.plusDays(7);
        return mealRepository.findByUserIdAndDateBetween(userId, start, end);
    }
}