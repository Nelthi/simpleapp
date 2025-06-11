package com.alimentation.simpleapp.service;

import com.alimentation.simpleapp.model.Food;
import com.alimentation.simpleapp.model.Meal;
import com.alimentation.simpleapp.model.User;
import com.alimentation.simpleapp.repository.FoodRepository;
import org.springframework.stereotype.Component;


import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class NutritionCalculator {

    private final FoodRepository foodRepository;

    public NutritionCalculator(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Set<Food> recommendFoods(User user) {
        Set<Food> recommendedFoods = new HashSet<>();

        Long minId = 1L;
        Long maxId = 30L;

        // Logique améliorée avec filtre d'ID
        if (user.getWeight() < 70) {
            List<Food> proteines = foodRepository.findByCategoryAndIdBetween("Protéines", minId, maxId);
            recommendedFoods.addAll(proteines);
        }

        List<Food> legumes = foodRepository.findByCategoryAndIdBetween("Légumes", minId, maxId);
        recommendedFoods.addAll(legumes);

        return recommendedFoods;
    }

    public double calculateTotalCalories(Meal meal){
        return meal.getFoods().stream().mapToDouble(Food::getCalories).sum();
    }
}