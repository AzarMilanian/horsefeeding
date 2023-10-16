package com.accenture.horsefeeding.controller;

import com.accenture.horsefeeding.dto.FeedingScheduleDto;
import com.accenture.horsefeeding.dto.HorseDto;
import com.accenture.horsefeeding.mapper.FeedingScheduleMapper;
import com.accenture.horsefeeding.model.FeedingSchedule;
import com.accenture.horsefeeding.repository.FeedingScheduleRepository;
import com.accenture.horsefeeding.service.FeedingScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/feeding-schedules")
public class FeedingScheduleController {

    private static final Logger logger = LoggerFactory.getLogger(FeedingScheduleController.class);
    @Autowired
    private FeedingScheduleService feedingScheduleService;
    @Autowired
    private FeedingScheduleRepository feedingScheduleRepository;
    @Autowired
    private FeedingScheduleMapper feedingScheduleMapper;

    /**
     * Creates a new feeding schedule based on the provided DTO.
     *
     * @param feedingScheduleDto The DTO containing the details of the feeding schedule to be created.
     * @return ResponseEntity with a success message if the feeding schedule is created successfully,
     *         ResponseEntity with a bad request status and relevant error message if there's an illegal argument,
     *         ResponseEntity with an internal server error status and generic error message for other exceptions.
     *
     * @throws IllegalArgumentException If there's an error in the provided feeding schedule details.
     */
    @PostMapping
    public ResponseEntity<?> createFeedingSchedule(@RequestBody FeedingScheduleDto feedingScheduleDto) {
        try {
            feedingScheduleService.createFeedingSchedule(feedingScheduleDto);
            return ResponseEntity.ok("Feeding schedule created successfully!");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            logger.error("Error creating feeding schedule: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while creating the feeding schedule.");
        }
    }

    /**
     * Retrieves the feeding schedule for the specified horse.
     *
     * @param horseId the ID of the horse.
     * @return the feeding schedule of the horse.
     */
    @GetMapping("/{horseId}")
    public FeedingScheduleDto getFeedingSchedule(@PathVariable Long horseId) {
        FeedingSchedule feedingSchedule = feedingScheduleService.findFeedingScheduleByHorseIdWithScheduleRanges(horseId)
                .orElseThrow(() -> new IllegalArgumentException("No feeding schedule found for the given horse ID."));

        return feedingScheduleMapper.toFeedingScheduleDTOWithMinimalHorseDetails(feedingSchedule);
    }

    /**
     * Deletes the feeding schedule for the specified horse.
     *
     * @param horseId the ID of the horse.
     */
    @DeleteMapping("/{horseId}")
    public void deleteFeedingSchedule(@PathVariable Long horseId) {
        FeedingSchedule feedingSchedule = feedingScheduleRepository.findByHorse_HorseId(horseId)
                .orElseThrow(() -> new IllegalArgumentException("No feeding schedule found for the given horse ID."));
        feedingScheduleRepository.delete(feedingSchedule);
    }

    /**
     * Modifies the feeding schedule for the specified horse.
     *
     * @param horseId the ID of the horse.
     * @param feedingScheduleDTO the updated feeding schedule data.
     */
    @PutMapping("/{horseId}")
    public ResponseEntity<?> modifyFeedingSchedule(@PathVariable Long horseId, @RequestBody FeedingScheduleDto feedingScheduleDTO) {
        try {
            feedingScheduleService.updateFeedingSchedule(horseId, feedingScheduleDTO);
            return ResponseEntity.ok("Feeding schedule updated successfully!");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            logger.error("Error updating feeding schedule: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the feeding schedule.");
        }
    }

    /**
     * Retrieves a list of horses eligible for feeding at the specified time.
     *
     * <p>If no time is provided in the request parameters, the current system time
     * is used as the default. This method fetches horses that are eligible for feeding
     * based on the provided or default time.</p>
     *
     * @param time An optional {@link LocalTime} parameter representing the time to
     *             check the eligibility. If not provided, the current system time is used.
     * @return A list of {@link HorseDto} representing the eligible horses for feeding.
     */
    @GetMapping("/eligible-horses")
    public List<HorseDto> getEligibleHorses(@RequestParam(required = false) LocalTime time) {
        if (time == null) {
            time = LocalTime.now();
        }
        return feedingScheduleService.findEligibleHorses(time);
    }

}
