package com.alimentation.simpleapp.repository;

import com.alimentation.simpleapp.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    // Trouver un ingrédient par nom
    Optional<Ingredient> findByName(String name);
}