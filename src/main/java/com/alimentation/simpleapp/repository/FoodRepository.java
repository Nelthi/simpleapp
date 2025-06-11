package com.alimentation.simpleapp.repository;

import com.alimentation.simpleapp.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    // Méthodes personnalisées (optionnel)
    List<Food> findByCategory(String category);
    List<Food> findByCaloriesLessThan(double maxCalories);
    List<Food> findByCategoryAndIdBetween(String category, Long minId, Long maxId);
    
}
