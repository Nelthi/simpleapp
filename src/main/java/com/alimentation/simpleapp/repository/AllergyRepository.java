package com.alimentation.simpleapp.repository;

import com.alimentation.simpleapp.model.Allergy;
import com.alimentation.simpleapp.model.Food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AllergyRepository extends JpaRepository<Allergy, Long> {
    // Trouver les allergies d'un utilisateur
    List<Allergy> findByUserId(Long userId);

    long countBySuspectedFoodsContains(Food food);

    long countBySuspectedFoodsContaining(Food food);
}
