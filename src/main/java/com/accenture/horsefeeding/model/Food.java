package com.accenture.horsefeeding.model;

import jakarta.persistence.*;
import java.util.List;
@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId;
    private String name;

    public Food() {
    }

    public Food(Long foodId, String name) {
        this.foodId = foodId;
        this.name = name;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
