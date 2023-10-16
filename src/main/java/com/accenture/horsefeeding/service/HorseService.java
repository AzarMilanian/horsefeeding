package com.accenture.horsefeeding.service;

import com.accenture.horsefeeding.mapper.HorseMapper;
import com.accenture.horsefeeding.model.FeedingSchedule;
import com.accenture.horsefeeding.model.Horse;
import com.accenture.horsefeeding.dto.HorseDto;
import com.accenture.horsefeeding.model.Owner;
import com.accenture.horsefeeding.model.Stable;
import com.accenture.horsefeeding.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HorseService {
    @Autowired
    private HorseRepository horseRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private StableRepository stableRepository;
    @Autowired
    private HorseMapper horseMapper;
    @Autowired
    private FeedingRecordRepository feedingRecordRepository;
    @Autowired
    private FeedingScheduleRepository feedingScheduleRepository;
    @Autowired
    private FoodScheduleRepository foodScheduleRepository;

    /**
     * Create a Horse entity and persist it to the database.
     *
     * @param horseDto The data transfer object containing horse details.
     * @return HorseDto The persisted horse details.
     * @throws EntityNotFoundException If related owner or stable is not found.
     */
    public HorseDto createHorse(HorseDto horseDto) {
        Horse horse = horseMapper.toEntity(horseDto);
        try {
            // Ensure owner and stable are not null before creating a horse.
        Owner owner = ownerRepository.findById(horseDto.getOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("Owner not found"));
        Stable stable = stableRepository.findById(horseDto.getStableId())
                .orElseThrow(() -> new IllegalArgumentException("Stable not found"));

        horse.setOwner(owner);
        horse.setStable(stable);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        horse = horseRepository.save(horse);
        return horseMapper.toDto(horse);
    }

        /**
         * Finds a horse by ID.
         *
         * @param horseId the ID of the horse to find
         * @return the horse as a data transfer object
         * @throws EntityNotFoundException if horse is not found
         */
        public HorseDto findHorseById(Long horseId) {
            Horse horse = horseRepository.findById(horseId)
                    .orElseThrow(() -> new EntityNotFoundException("Horse with ID " + horseId + " not found."));
            return horseMapper.toDto(horse);
        }

        /**
         * Updates a horse's details.
         *
         * @param horseId  the ID of the horse to update
         * @param horseDto the data transfer object containing the new details
         * @return the updated horse as a data transfer object
         * @throws EntityNotFoundException if horse is not found
         */
        public HorseDto updateHorse(Long horseId, HorseDto horseDto) {
            Horse horse = horseRepository.findById(horseId)
                    .orElseThrow(() -> new EntityNotFoundException("Horse with ID " + horseId + " not found."));
            horse.setGuid(horseDto.getGuid());
            horse.setOfficialName(horseDto.getOfficialName());
            horse.setNickname(horseDto.getNickname());
            horse.setBreed(horseDto.getBreed());
            horse.setOwner(ownerRepository.findById(horseDto.getOwnerId())
                    .orElseThrow(() -> new EntityNotFoundException("Owner with ID " + horseDto.getOwnerId() + " not found.")));
            horse.setStable(stableRepository.findById(horseDto.getStableId())
                    .orElseThrow(() -> new EntityNotFoundException("Stable with ID " + horseDto.getStableId() + " not found.")));
            horse = horseRepository.save(horse);
            return horseMapper.toDto(horse);
        }

        /**
         * Deletes a horse by ID.
         *
         * @param horseId the ID of the horse to delete
         * @throws EntityNotFoundException if horse is not found
         */
        public void deleteHorse(Long horseId) {
            Horse horse = horseRepository.findById(horseId)
                    .orElseThrow(() -> new EntityNotFoundException("Horse with ID " + horseId + " not found."));
            horseRepository.delete(horse);
        }

    /**
     * Returns a list of horses that haven't been fed even when eligible past a certain time today.
     *
     * @param hours The threshold time.
     * @return List of HorseDTOs that haven't been fed past the threshold.
     */
    public List<HorseDto> getHorsesNotFedForHours(int hours) {
        LocalTime thresholdTime = LocalTime.now().minusHours(hours);
        List<Horse> allHorses = horseRepository.findAll();

        List<Horse> unfedHorses = allHorses.stream()
                .filter(horse -> {
                    Optional<LocalTime> lastFedTimeOpt = feedingRecordRepository.findLatestFeedingForHorse(horse.getHorseId());
                    return lastFedTimeOpt.isEmpty() || lastFedTimeOpt.get().isBefore(thresholdTime);
                })
                .toList();

        return unfedHorses.stream()
                .map(horseMapper::toDto) // Assuming you have a horseMapper set up with MapStruct
                .collect(Collectors.toList());
    }


    /**
     * Returns a list of horses that didn't complete their meals for the day.
     *
     * @return List of HorseDto representing horses that didn't complete their meals.
     */
    public List<HorseDto> getHorsesWithIncompleteMeals() {
        List<Horse> allHorses = horseRepository.findAll();

        List<Horse> incompleteMealHorses = allHorses.stream().filter(horse -> {
            Optional<FeedingSchedule> feedingScheduleOpt = feedingScheduleRepository.findByHorse(horse); // Fetching schedule using the horse

            if (feedingScheduleOpt.isEmpty()) {
                return false; // If the horse doesn't have an associated schedule, we can't determine if it completed its meals, so skip it.
            }

            FeedingSchedule feedingSchedule = feedingScheduleOpt.get();
            int expectedAmount = foodScheduleRepository.getTotalFoodAmountByScheduleId(feedingSchedule.getScheduleId());
            int consumedAmount = feedingRecordRepository.getTotalConsumedAmountByHorseId(horse.getHorseId());

            return consumedAmount < expectedAmount;
        }).toList();

        return incompleteMealHorses.stream().map(horseMapper::toDto).collect(Collectors.toList());
    }

}



