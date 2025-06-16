package com.alimentation.simpleapp.dto;

import com.alimentation.simpleapp.model.Meal;

import lombok.Data;

import java.util.List;
@Data
public class WeeklyPlanDTO {
    private Long userId;
    private List<Meal> meals;

    public WeeklyPlanDTO(Long userId, List<Meal> meals) {
        this.userId = userId;
        this.meals = meals;
    }

    // getters et setters
}
