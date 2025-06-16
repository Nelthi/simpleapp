package com.alimentation.simpleapp.repository;

import com.alimentation.simpleapp.model.Meal;
import com.alimentation.simpleapp.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    // Trouver les repas d'un utilisateur
    List<Meal> findByUserId(Long userId);
    
    // Trouver les repas par date
    List<Meal> findByDateBetween(LocalDateTime start, LocalDateTime end);
    List<Meal> findByUserIdAndDateBetween(Long userId, LocalDateTime start, LocalDateTime end);   
    List<Meal> findByUser(User user);
    List<Meal> findByUserAndDate(User user, LocalDate date);

}
