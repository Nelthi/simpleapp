package com.alimentation.simpleapp.model;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String imageUrl;
    private String benefits;
    private String risks; // Changé de double à String
    private String nutritionFacts;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "ingredients")
    private Set<Food> foods = new HashSet<>();

    // Dans Ingredient.java
public void addFood(Food food) {
    this.foods.add(food);
    food.getIngredients().add(this);
}

}