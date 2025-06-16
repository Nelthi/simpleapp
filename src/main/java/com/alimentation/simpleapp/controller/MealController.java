package com.alimentation.simpleapp.controller;

import com.alimentation.simpleapp.model.Meal;
import com.alimentation.simpleapp.model.Food;
import com.alimentation.simpleapp.model.User;
import com.alimentation.simpleapp.repository.MealRepository;
import com.alimentation.simpleapp.repository.FoodRepository;
import com.alimentation.simpleapp.repository.UserRepository;
import com.alimentation.simpleapp.service.MealPlanningService;
import com.alimentation.simpleapp.service.NutritionCalculator;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/api/meals")
public class MealController {

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private UserRepository userRepository;

    // 1. Créer un repas (sans aliments initiaux)
    @PostMapping
    public ResponseEntity<Meal> createMeal(
            @RequestBody Meal meal,
            @RequestParam(required = false) Long userId) {
        
        try {
            // Associer l'utilisateur si fourni
            if (userId != null) {
                Optional<User> user = userRepository.findById(userId);
                if (user.isEmpty()) {
                    return ResponseEntity.badRequest().build();
                }
                meal.setUser(user.get());
            }

            meal.setFoods(new HashSet<>());
            meal.setDate(LocalDateTime.now());
            Meal savedMeal = mealRepository.save(meal);
            return ResponseEntity.ok(savedMeal);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // 2. Ajouter un aliment à un repas
    @PostMapping("/{mealId}/foods/{foodId}")
    public ResponseEntity<Meal> addFoodToMeal(
            @PathVariable Long mealId,
            @PathVariable Long foodId) {
        
        Optional<Meal> mealOpt = mealRepository.findById(mealId);
        Optional<Food> foodOpt = foodRepository.findById(foodId);

        if (mealOpt.isEmpty() || foodOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Meal meal = mealOpt.get();
        Food food = foodOpt.get();

        // Ajout bidirectionnel
        meal.getFoods().add(food);
        food.getMeals().add(meal);

        mealRepository.save(meal);
        foodRepository.save(food);

        return ResponseEntity.ok(meal);
    }

    // 3. Lister tous les repas
    @GetMapping
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    // 4. Récupérer un repas spécifique
    @GetMapping("/{id}")
    public ResponseEntity<Meal> getMealById(@PathVariable Long id) {
        return mealRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. Récupérer les aliments d'un repas
    @GetMapping("/{id}/foods")
    @Transactional
    public ResponseEntity<Set<Food>> getMealFoods(@PathVariable Long id) {
        return mealRepository.findById(id)
                .map(meal -> ResponseEntity.ok(meal.getFoods()))
                .orElse(ResponseEntity.notFound().build());
    }

    // 6. Supprimer un repas
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMeal(@PathVariable Long id) {
        return mealRepository.findById(id)
                .map(meal -> {
                    // Nettoyer les références dans les aliments
                    for (Food food : meal.getFoods()) {
                        food.getMeals().remove(meal);
                        foodRepository.save(food);
                    }
                    mealRepository.delete(meal);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 7. Rechercher les repas d'un utilisateur
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Meal>> getUserMeals(@PathVariable Long userId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mealRepository.findByUserId(userId));
    }

    // 8. Retirer un aliment d'un repas
    @DeleteMapping("/{mealId}/foods/{foodId}")
    public ResponseEntity<Meal> removeFoodFromMeal(
            @PathVariable Long mealId,
            @PathVariable Long foodId) {
        
        Optional<Meal> mealOpt = mealRepository.findById(mealId);
        Optional<Food> foodOpt = foodRepository.findById(foodId);

        if (mealOpt.isEmpty() || foodOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Meal meal = mealOpt.get();
        Food food = foodOpt.get();

        // Retrait bidirectionnel
        meal.getFoods().remove(food);
        food.getMeals().remove(meal);

        mealRepository.save(meal);
        foodRepository.save(food);

        return ResponseEntity.ok(meal);
    }
    // Dans MealController.java
    @GetMapping("/{id}/total-calories")
    @Transactional
    public ResponseEntity<Double> getTotalCalories(@PathVariable Long id) {
    return mealRepository.findById(id)
            .map(meal -> {
                double total = meal.getFoods().stream()
                        .mapToDouble(Food::getCalories)
                        .sum();
                return ResponseEntity.ok(total);
            })
            .orElse(ResponseEntity.notFound().build());
}
    


    private final MealPlanningService mealPlanningService;
    private final NutritionCalculator nutritionCalculator;

    public MealController(MealPlanningService mealPlanningService,
                        NutritionCalculator nutritionCalculator) {
        this.mealPlanningService = mealPlanningService;
        this.nutritionCalculator = nutritionCalculator;
    }

    // Générer un planning hebdomadaire pour un utilisateur
    @PostMapping("/generate-weekly/{userId}")
    public ResponseEntity<List<Meal>> generateWeeklyPlan(@PathVariable Long userId) {
        List<Meal> weeklyMeals = mealPlanningService.generateWeeklyPlan(userId);
        return ResponseEntity.ok(weeklyMeals);
    }

    // Obtenir le planning existant de la semaine
    @GetMapping("/weekly/{userId}")
    public ResponseEntity<List<Meal>> getWeeklyMeals(@PathVariable Long userId) {
        List<Meal> meals = mealPlanningService.getWeeklyMeals(userId);
        return ResponseEntity.ok(meals);
    
}
    
    
    
    @GetMapping("/{mealId}/calories")
    public ResponseEntity<Double> getMealCalories(@PathVariable Long mealId) {
        return mealRepository.findById(mealId).map(meal->ResponseEntity.ok(nutritionCalculator.calculateTotalCalories(meal))).orElse(ResponseEntity.notFound().build());
}
    
    
    
}