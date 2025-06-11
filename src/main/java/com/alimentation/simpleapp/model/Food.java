package com.alimentation.simpleapp.model;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "foods")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String imageUrl;
    private double calories;
    private String category; // Correction orthographique
    
    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "food_ingredients",
        joinColumns = @JoinColumn(name = "food_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private Set<Ingredient> ingredients = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "foods")
    private Set<Meal> meals = new HashSet<>();

    // Dans Food.java
public void addIngredient(Ingredient ingredient) {
    this.ingredients.add(ingredient);
    ingredient.getFoods().add(this);
}

}
