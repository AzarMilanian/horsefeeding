package com.accenture.horsefeeding.model;

import jakarta.persistence.*;

@Entity
public class FoodSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodScheduleId;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private FeedingSchedule feedingSchedule;
    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;
    private int foodQuantity;

    public FoodSchedule() {
    }

    public FoodSchedule(Long foodScheduleId, FeedingSchedule feedingSchedule, Food food, int foodQuantity) {
        this.foodScheduleId = foodScheduleId;
        this.feedingSchedule = feedingSchedule;
        this.food = food;
        this.foodQuantity = foodQuantity;
    }

    public Long getFoodScheduleId() {
        return foodScheduleId;
    }

    public void setFoodScheduleId(Long foodScheduleId) {
        this.foodScheduleId = foodScheduleId;
    }

    public FeedingSchedule getFeedingSchedule() {
        return feedingSchedule;
    }

    public void setFeedingSchedule(FeedingSchedule feedingSchedule) {
        this.feedingSchedule = feedingSchedule;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(int foodQuantity) {
        this.foodQuantity = foodQuantity;
    }
}

