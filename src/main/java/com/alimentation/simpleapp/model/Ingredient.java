package com.alimentation.simpleapp.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String imageUrl;
    private String benefits;
    private String risks; // Déjà corrigé
    private String nutritionFacts;

    @JsonIgnore
    @ManyToMany(mappedBy = "ingredients")
    private Set<Food> foods = new HashSet<>();

    public void addFood(Food food) {
        this.foods.add(food);
        food.getIngredients().add(this);
    }

    // Sécurité : equals/hashCode sur id uniquement
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient)) return false;
        Ingredient that = (Ingredient) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
