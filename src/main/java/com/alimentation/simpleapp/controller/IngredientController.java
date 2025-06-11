package com.alimentation.simpleapp.controller;

import com.alimentation.simpleapp.model.Ingredient;
import com.alimentation.simpleapp.model.Food;
import com.alimentation.simpleapp.repository.IngredientRepository;
import com.alimentation.simpleapp.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private FoodRepository foodRepository;

    // 1. Créer un ingrédient (sans associer d'aliments directement)
    @PostMapping
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
        try {
            // On ne gère pas les foods ici, on les ajoutera via l'API Food
            ingredient.setFoods(new HashSet<>());
            Ingredient savedIngredient = ingredientRepository.save(ingredient);
            return ResponseEntity.ok(savedIngredient);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // 2. Associer un ingrédient à un aliment
    @PostMapping("/{ingredientId}/foods/{foodId}")
    public ResponseEntity<Ingredient> addIngredientToFood(
            @PathVariable Long ingredientId,
            @PathVariable Long foodId) {
        
        Optional<Ingredient> ingredientOpt = ingredientRepository.findById(ingredientId);
        Optional<Food> foodOpt = foodRepository.findById(foodId);

        if (ingredientOpt.isEmpty() || foodOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Ingredient ingredient = ingredientOpt.get();
        Food food = foodOpt.get();

        // Ajout bidirectionnel
        ingredient.getFoods().add(food);
        food.getIngredients().add(ingredient);

        ingredientRepository.save(ingredient);
        foodRepository.save(food);

        return ResponseEntity.ok(ingredient);
    }

    // 3. Récupérer tous les ingrédients
    @GetMapping
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    // 4. Récupérer un ingrédient avec ses aliments associés
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable Long id) {
        return ingredientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. Récupérer les aliments d'un ingrédient
    @GetMapping("/{id}/foods")
    public ResponseEntity<Set<Food>> getFoodsByIngredient(@PathVariable Long id) {
        return ingredientRepository.findById(id)
                .map(ingredient -> ResponseEntity.ok(ingredient.getFoods()))
                .orElse(ResponseEntity.notFound().build());
    }

    // 6. Supprimer un ingrédient
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIngredient(@PathVariable Long id) {
        return ingredientRepository.findById(id)
                .map(ingredient -> {
                    // Retire l'ingrédient de tous ses aliments associés
                    for (Food food : ingredient.getFoods()) {
                        food.getIngredients().remove(ingredient);
                        foodRepository.save(food);
                    }
                    ingredientRepository.delete(ingredient);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}