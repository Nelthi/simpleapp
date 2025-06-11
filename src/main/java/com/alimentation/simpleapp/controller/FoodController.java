package com.alimentation.simpleapp.controller;

import com.alimentation.simpleapp.model.Food;
import com.alimentation.simpleapp.repository.FoodRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    // Create
    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody Food food) {
        Food savedFood = foodRepository.save(food);
        return ResponseEntity.ok(savedFood);
    }
    // create more than one food
     // créer plusieurs utilisateurs à la fois

    @PostMapping("/bulk")
    public ResponseEntity<List<Food>> createUsers(@RequestBody List<Food> food) {
        try {
            List<Food> savedFood = foodRepository.saveAll(food);
            return ResponseEntity.ok(savedFood);
    } catch (Exception e) {
            return ResponseEntity.badRequest().build();
    }
}

    // Read All
    @GetMapping
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    // Read One
    @GetMapping("/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable Long id) {
        Optional<Food> food = foodRepository.findById(id);
        return food.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFood(@PathVariable Long id, @RequestBody Food foodDetails) {
        return foodRepository.findById(id)
            .map(existingFood -> {
                existingFood.setName(foodDetails.getName());
                existingFood.setCategory(foodDetails.getCategory());
                Food updatedFood = foodRepository.save(existingFood);
                return ResponseEntity.ok(updatedFood);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFood(@PathVariable Long id) {
        return foodRepository.findById(id)
            .map(food -> {
                foodRepository.delete(food);
                return ResponseEntity.ok().build();
            })
            .orElse(ResponseEntity.notFound().build());
        }

   
    
}