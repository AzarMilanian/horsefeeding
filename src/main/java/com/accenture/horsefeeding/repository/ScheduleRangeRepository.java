package com.accenture.horsefeeding.repository;

import com.accenture.horsefeeding.model.Horse;
import com.accenture.horsefeeding.model.ScheduleRange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface ScheduleRangeRepository extends JpaRepository<ScheduleRange, Long> {
    /**
     * Finds the overlapping feeding schedule ranges based on the given time range and horse.
     *
     * @param start The starting time.
     * @param end The ending time.
     * @param horse The horse in question.
     * @return A list of overlapping schedule ranges.
     */
    @Query("SELECT sr FROM ScheduleRange sr " +
            "WHERE :start < sr.feedingRangeEnd AND :end > sr.feedingRangeStart " +
            "AND sr.feedingSchedule.horse = :horse")
    List<ScheduleRange> findOverlappingRanges(@Param("start") LocalTime start,
                                              @Param("end") LocalTime end,
                                              @Param("horse") Horse horse);
}


