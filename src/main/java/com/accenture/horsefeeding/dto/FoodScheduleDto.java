package com.accenture.horsefeeding.dto;

public class FoodScheduleDto {
    private Long foodScheduleId;
    private Long scheduleId;
    private String foodName;
    private int foodQuantity;

    public FoodScheduleDto() {
    }

    public Long getFoodScheduleId() {
        return foodScheduleId;
    }

    public void setFoodScheduleId(Long foodScheduleId) {
        this.foodScheduleId = foodScheduleId;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(int foodQuantity) {
        this.foodQuantity = foodQuantity;
    }
}

