package com.alimentation.simpleapp.controller;

import com.alimentation.simpleapp.model.*;
import com.alimentation.simpleapp.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class MealHistoryController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MealRepository mealRepository;

    @GetMapping("/{id}/meals-by-date")
public ResponseEntity<List<Meal>> getMealsByDate(
    @PathVariable Long id,
    @RequestParam(value = "date", required = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

    Optional<User> userOpt = userRepository.findById(id);
    if (userOpt.isEmpty()) return ResponseEntity.notFound().build();

    User user = userOpt.get();

    List<Meal> meals;

    if (date != null) {
        meals = mealRepository.findByUserAndDate(user, date);
    } else {
        meals = mealRepository.findByUser(user);
    }

    return ResponseEntity.ok(meals);
}

}
