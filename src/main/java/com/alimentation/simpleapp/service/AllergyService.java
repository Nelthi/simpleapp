package com.alimentation.simpleapp.service;

import com.alimentation.simpleapp.model.Allergy;
import com.alimentation.simpleapp.model.Food;
import com.alimentation.simpleapp.repository.AllergyRepository;
//import com.alimentation.simpleapp.repository.FoodRepository;
//import com.alimentation.simpleapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AllergyService {

    private final AllergyRepository allergyRepository;

    // ✅ Service pour récupérer les aliments auxquels un utilisateur est allergique
    public List<Food> getAllergicFoodsForUser(Long userId) {
        List<Allergy> allergies = allergyRepository.findByUserId(userId);

        return allergies.stream()
                .flatMap(allergy -> allergy.getSuspectedFoods().stream())
                .distinct()
                .collect(Collectors.toList());
    }
}