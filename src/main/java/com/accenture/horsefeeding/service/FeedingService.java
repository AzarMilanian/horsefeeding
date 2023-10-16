package com.accenture.horsefeeding.service;

import com.accenture.horsefeeding.dto.FeedingRecordDto;
import com.accenture.horsefeeding.mapper.FeedingRecordMapper;
import com.accenture.horsefeeding.model.FeedingSchedule;
import com.accenture.horsefeeding.model.Horse;
import com.accenture.horsefeeding.repository.FeedingRecordRepository;
import com.accenture.horsefeeding.repository.FeedingScheduleRepository;
import com.accenture.horsefeeding.repository.HorseRepository;
import com.accenture.horsefeeding.model.FeedingRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Optional;

@Service
public class FeedingService {
    @Autowired
    private HorseRepository horseRepository;
    @Autowired
    private FeedingRecordRepository feedingRecordRepository;

    @Autowired
    private FeedingScheduleRepository feedingScheduleRepository;
    @Autowired
    private FeedingRecordMapper feedingRecordMapper;

    /**
     * Creates a feeding record for a horse by its ID.
     *
     * @param horseId The ID of the horse for which the feeding record is to be created.
     * @return The DTO representation of the created feeding record.
     * @throws RuntimeException if the horse doesn't have a feeding schedule,
     *         if the feeding time is out of range, or if the horse is not found.
     */
    public FeedingRecordDto createFeedingRecord(Long horseId) {
        LocalTime now = LocalTime.now();

        Optional<FeedingSchedule> feedingScheduleOpt = feedingScheduleRepository.findByHorse_HorseId(horseId);
        if (feedingScheduleOpt.isEmpty()) {
            throw new RuntimeException("Horse does not have a feeding schedule.");
        }

        FeedingSchedule feedingSchedule = feedingScheduleOpt.get();
        boolean isWithinRange = feedingSchedule.getScheduleRanges().stream()
                .anyMatch(range -> now.isAfter(range.getFeedingRangeStart()) && now.isBefore(range.getFeedingRangeEnd()));

        if (!isWithinRange) {
            throw new RuntimeException("The horse is not eligible to eat at this time.");
        }

        FeedingRecord feedingRecord = new FeedingRecord();
        feedingRecord.setHorse(horseRepository.findById(horseId).orElseThrow(() -> new RuntimeException("Horse not found.")));
        feedingRecord.setFeedingBegin(now);
        feedingRecord.setFeedingSchedule(feedingSchedule);
        feedingRecord.setAmountConsumed(1);
        feedingRecord.setStatus("Done");

        feedingRecord = feedingRecordRepository.save(feedingRecord);

        // Convert the feeding record to DTO
        FeedingRecordDto dto = new FeedingRecordDto();
        dto.setRecordId(feedingRecord.getRecordId());
        dto.setHorseId(horseId);
        dto.setFeedingBegin(now);
        dto.setStatus(feedingRecord.getStatus());
        dto.setAmountConsumed(feedingRecord.getAmountConsumed());

        return dto;
    }

    /**
     * Updates a feeding record.
     *
     * @param feedingRecordDto The DTO containing the updated information.
     * @return Updated FeedingRecord.
     */
    public FeedingRecordDto updateFeedingRecord(FeedingRecordDto feedingRecordDto) {
        // Get the feeding record to be edited
        FeedingRecord feedingRecord = feedingRecordRepository.findById(feedingRecordDto.getRecordId())
                .orElseThrow(() -> new RuntimeException("Feeding Record not found."));

        // Update properties
        Horse horse = horseRepository.findById(feedingRecordDto.getHorseId())
                .orElseThrow(() -> new RuntimeException("Horse not found."));
        feedingRecord.setHorse(horse);

        feedingRecord.setFeedingBegin(feedingRecordDto.getFeedingBegin());
        feedingRecord.setFeedingEnd(feedingRecordDto.getFeedingEnd());

        FeedingSchedule feedingSchedule = feedingScheduleRepository.findById(feedingRecordDto.getFeedingScheduleId())
                .orElseThrow(() -> new RuntimeException("Feeding Schedule not found."));
        feedingRecord.setFeedingSchedule(feedingSchedule);

        feedingRecord.setAmountConsumed(feedingRecordDto.getAmountConsumed());
        feedingRecord.setStatus(feedingRecordDto.getStatus());

        FeedingRecord updatedRecord = feedingRecordRepository.save(feedingRecord);

        return feedingRecordMapper.toDto(updatedRecord);
    }

}

