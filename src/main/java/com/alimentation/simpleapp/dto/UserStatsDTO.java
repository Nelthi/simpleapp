package com.alimentation.simpleapp.dto;

import java.util.List;

public class UserStatsDTO {
    private int totalMeals;
    private double avgCalories;
    private List<String> topIngredients;
    private List<String> topFoods;

    public UserStatsDTO(int totalMeals, double avgCalories, List<String> topIngredients, List<String> topFoods) {
        this.totalMeals = totalMeals;
        this.avgCalories = avgCalories;
        this.topIngredients = topIngredients;
        this.topFoods = topFoods;
    }

    public int getTotalMeals() {
        return totalMeals;
    }

    public double getAvgCalories() {
        return avgCalories;
    }

    public List<String> getTopIngredients() {
        return topIngredients;
    }

    public List<String> getTopFoods() {
        return topFoods;
    }
}
