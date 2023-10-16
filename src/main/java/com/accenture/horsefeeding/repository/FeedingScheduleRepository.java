package com.accenture.horsefeeding.repository;

import com.accenture.horsefeeding.model.FeedingSchedule;
import com.accenture.horsefeeding.model.Horse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FeedingScheduleRepository extends JpaRepository<FeedingSchedule, Long> {
    /**
     * Finds the feeding schedule for a specific horse.
     *
     * @param horse The horse entity.
     * @return The feeding schedule, if available.
     */
    Optional<FeedingSchedule> findByHorse(Horse horse);

    /**
     * Finds the feeding schedule by the horse's ID.
     *
     * @param horseId The ID of the horse.
     * @return The feeding schedule, if available.
     */
    Optional<FeedingSchedule> findByHorse_HorseId(Long horseId);

    /**
     * Finds the feeding schedule with schedule ranges for a specific horse ID.
     *
     * @param horseId The ID of the horse.
     * @return The feeding schedule with schedule ranges, if available.
     */
    @Query("SELECT fs FROM FeedingSchedule fs JOIN FETCH fs.scheduleRanges WHERE fs.horse.horseId = :horseId")
    Optional<FeedingSchedule> findByHorseIdWithScheduleRanges(@Param("horseId") Long horseId);

    /**
     * Lists horses that are eligible for feeding at a specific time.
     *
     * @param time The time to check against.
     * @return A list of eligible horses.
     */
    @Query("SELECT fs.horse FROM FeedingSchedule fs JOIN fs.scheduleRanges sr WHERE sr.feedingRangeStart <= :time AND sr.feedingRangeEnd >= :time")
    List<Horse> findHorsesEligibleForFeeding(@Param("time") LocalTime time);
}

