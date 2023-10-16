package com.accenture.horsefeeding.repository;

import com.accenture.horsefeeding.model.FeedingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface FeedingRecordRepository extends JpaRepository<FeedingRecord, Long> {
    /**
     * Finds the latest feeding time for a specific horse.
     *
     * @param horseId The ID of the horse.
     * @return The latest feeding time, if available.
     */
    @Query("SELECT MAX(fr.feedingEnd) FROM FeedingRecord fr WHERE fr.horse.horseId = :horseId")
    Optional<LocalTime> findLatestFeedingForHorse(@Param("horseId") Long horseId);

    /**
     * Calculates the total amount of food consumed by a specific horse.
     *
     * @param horseId The ID of the horse.
     * @return The total consumed amount.
     */
    @Query("SELECT SUM(fr.amountConsumed) FROM FeedingRecord fr WHERE fr.horse.horseId = :horseId")
    int getTotalConsumedAmountByHorseId(Long horseId);

}
