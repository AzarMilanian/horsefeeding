package com.accenture.horsefeeding.repository;

import com.accenture.horsefeeding.model.FoodSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for managing {@link FoodSchedule} entities.
 */
@Repository
public interface FoodScheduleRepository extends JpaRepository<FoodSchedule, Long> {
    /**
     * Calculates the total food quantity for a specific feeding schedule ID.
     *
     * @param feedingScheduleId The ID of the feeding schedule.
     * @return The total food quantity.
     */
    @Query("SELECT SUM(fs.foodQuantity) FROM FoodSchedule fs WHERE fs.feedingSchedule.scheduleId = :feedingScheduleId")
    int getTotalFoodAmountByScheduleId(Long feedingScheduleId);
}


