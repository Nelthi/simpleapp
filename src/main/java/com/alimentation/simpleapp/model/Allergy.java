package com.alimentation.simpleapp.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Data
@Entity
@Table(name = "allergies")
public class Allergy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDateTime date;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "allergy_suspected_foods",
        joinColumns = @JoinColumn(name = "allergy_id"),
        inverseJoinColumns = @JoinColumn(name = "food_id")
    )
    private Set<Food> suspectedFoods = new HashSet<>();
}

