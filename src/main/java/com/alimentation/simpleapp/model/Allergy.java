package com.alimentation.simpleapp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "allergy_suspected_foods",
        joinColumns = @JoinColumn(name = "allergy_id"),
        inverseJoinColumns = @JoinColumn(name = "food_id")
    )
    private Set<Food> suspectedFoods = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Allergy)) return false;
        Allergy allergy = (Allergy) o;
        return id != null && id.equals(allergy.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
