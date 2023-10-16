package com.accenture.horsefeeding.service;

import com.accenture.horsefeeding.dto.FeedingScheduleDto;
import com.accenture.horsefeeding.dto.HorseDto;
import com.accenture.horsefeeding.dto.ScheduleRangeDto;
import com.accenture.horsefeeding.mapper.HorseMapper;
import com.accenture.horsefeeding.model.FeedingSchedule;
import com.accenture.horsefeeding.model.Horse;
import com.accenture.horsefeeding.model.ScheduleRange;
import com.accenture.horsefeeding.repository.FeedingScheduleRepository;
import com.accenture.horsefeeding.repository.HorseRepository;
import com.accenture.horsefeeding.repository.ScheduleRangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FeedingScheduleService {

    private static final Logger log = LoggerFactory.getLogger(FeedingScheduleService.class);

    @Autowired
    private FeedingScheduleRepository feedingScheduleRepository;

    @Autowired
    private ScheduleRangeRepository scheduleRangeRepository;
    @Autowired
    private HorseRepository horseRepository;
    @Autowired
    private HorseMapper horseMapper;

    /**
     * Create a new feeding schedule while validating it doesn't violate constraints.
     *
     * @param feedingScheduleDTO contains data to create a new feeding schedule
     * @throws IllegalArgumentException if constraints are violated
     */
    public void createFeedingSchedule(FeedingScheduleDto feedingScheduleDTO) throws IllegalArgumentException {
        log.info("Creating feeding schedule for horseId: {}", feedingScheduleDTO.getHorseId());

        Long horseId = feedingScheduleDTO.getHorseId();
        Horse horse = horseRepository.findById(horseId).orElseThrow(() -> new IllegalArgumentException("Invalid horse ID."));

        // Check if the horse already has a feeding schedule
        Optional<FeedingSchedule> existingSchedule = feedingScheduleRepository.findByHorse(horse);
        if (existingSchedule.isPresent()) {
            throw new IllegalArgumentException("The horse already has a feeding schedule.");
        }

        List<ScheduleRangeDto> rangeDTOs = feedingScheduleDTO.getRanges();

        // Check that the number of feeding ranges doesn't exceed the limit
        if (rangeDTOs.size() > 5) {
            throw new IllegalArgumentException("The number of feeding ranges exceeds the allowed limit.");
        }

        List<ScheduleRange> ranges = rangeDTOs.stream().map(dto -> new ScheduleRange(
                null, // Since this is a new range, ID should be null.
                null, // This will be set later once the FeedingSchedule is saved.
                dto.getFeedingRangeStart(),
                dto.getFeedingRangeEnd()
        )).collect(Collectors.toList());

        // Check for overlapping times
        for (int i = 0; i < ranges.size(); i++) {
            for (int j = i + 1; j < ranges.size(); j++) {
                ScheduleRange r1 = ranges.get(i);
                ScheduleRange r2 = ranges.get(j);
                if (r1.getFeedingRangeStart().isBefore(r2.getFeedingRangeEnd()) && r1.getFeedingRangeEnd().isAfter(r2.getFeedingRangeStart())) {
                    throw new IllegalArgumentException("The feeding ranges have overlapping times.");
                }
            }
        }

        // Create a new FeedingSchedule with the horse and ranges.
        FeedingSchedule feedingSchedule = new FeedingSchedule(horse, ranges);
        for (ScheduleRange range : ranges) {
            range.setFeedingSchedule(feedingSchedule);  // Set the feeding schedule for each range
        }

        feedingScheduleRepository.save(feedingSchedule);
    }

    /**
     * Updates the feeding schedule for the specified horse.
     *
     * @param horseId The ID of the horse whose schedule is to be updated.
     * @param feedingScheduleDTO DTO containing the new feeding schedule details.
     * @throws IllegalArgumentException If no feeding schedule is found for the given horse ID.
     */
    public void updateFeedingSchedule(Long horseId, FeedingScheduleDto feedingScheduleDTO) throws IllegalArgumentException {
        FeedingSchedule existingSchedule = feedingScheduleRepository.findByHorse_HorseId(horseId)
                .orElseThrow(() -> new IllegalArgumentException("No feeding schedule found for the given horse ID."));

        // Clear existing ranges and set updated ones
        existingSchedule.getScheduleRanges().clear();
        List<ScheduleRange> newRanges = feedingScheduleDTO.getRanges().stream().map(dto -> new ScheduleRange(
                null,
                existingSchedule,
                dto.getFeedingRangeStart(),
                dto.getFeedingRangeEnd()
        )).collect(Collectors.toList());
        existingSchedule.getScheduleRanges().addAll(newRanges);

        // Use the validate method from the service
        this.validateFeedingSchedule(newRanges, existingSchedule.getHorse());

        feedingScheduleRepository.save(existingSchedule);
    }

    /**
     * Validate the feeding schedule ensuring no time overlap and maximum of 5 feedings.
     *
     * @param ranges feeding ranges to validate
     * @throws IllegalArgumentException if constraints are violated
     */
    private void validateFeedingSchedule(List<ScheduleRange> ranges, Horse horse) throws IllegalArgumentException{

        if (ranges.size() > 5) {
            throw new IllegalArgumentException("Cannot schedule more than 5 feedings a day.");
        }

        for (ScheduleRange range : ranges) {
            List<ScheduleRange> overlapping = scheduleRangeRepository.findOverlappingRanges(
                    range.getFeedingRangeStart(), range.getFeedingRangeEnd(), range.getFeedingSchedule().getHorse());
            if (!overlapping.isEmpty()) {
                throw new IllegalArgumentException("Feeding ranges must not overlap with existing ones.");
            }
        }
    }

    /**
     * Retrieves the feeding schedule with schedule ranges for the specified horse.
     *
     * @param horseId The ID of the horse.
     * @return The feeding schedule for the specified horse.
     * @throws IllegalArgumentException If no feeding schedule is found for the given horse ID.
     */
    public FeedingSchedule getFeedingScheduleByHorseId(Long horseId) {
        return feedingScheduleRepository.findByHorseIdWithScheduleRanges(horseId)
                .orElseThrow(() -> new IllegalArgumentException("No feeding schedule found for the given horse ID."));
    }

    /**
     * Finds and returns the feeding schedule with schedule ranges for the specified horse.
     *
     * @param horseId The ID of the horse.
     * @return An optional feeding schedule for the specified horse.
     */
    public Optional<FeedingSchedule> findFeedingScheduleByHorseIdWithScheduleRanges(Long horseId) {
        return feedingScheduleRepository.findByHorseIdWithScheduleRanges(horseId);
    }

    /**
     * Retrieves a list of horses eligible for feeding at the specified time.
     *
     * @param time The time to check for horse feeding eligibility.
     * @return A list of DTO representations of eligible horses.
     */
    public List<HorseDto> findEligibleHorses(LocalTime time) {
        List<Horse> horses = feedingScheduleRepository.findHorsesEligibleForFeeding(time);
        return horses.stream().map(horseMapper::toDto).collect(Collectors.toList());
    }

}
