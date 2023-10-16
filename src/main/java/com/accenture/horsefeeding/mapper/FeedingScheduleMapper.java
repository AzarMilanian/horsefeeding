package com.accenture.horsefeeding.mapper;

import com.accenture.horsefeeding.dto.FeedingScheduleDto;
import com.accenture.horsefeeding.model.FeedingSchedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for converting between {@link FeedingSchedule} entities and {@link FeedingScheduleDto} DTOs.
 */
@Mapper(componentModel = "spring", uses = ScheduleRangeMapper.class)
public interface FeedingScheduleMapper {

    /**
     * Converts a {@link FeedingSchedule} entity to a {@link FeedingScheduleDto}.
     *
     * @param feedingSchedule the entity to convert
     * @return the converted DTO
     */
    FeedingScheduleDto toDTO(FeedingSchedule feedingSchedule);

    /**
     * Converts a {@link FeedingSchedule} entity to a {@link FeedingScheduleDto},
     * with minimal horse details.
     *
     * @param feedingSchedule the entity to convert
     * @return the converted DTO with minimal horse details
     */
    @Mapping(target = "horseId", source = "horse.horseId")
    @Mapping(target = "ranges", source = "scheduleRanges")
    FeedingScheduleDto toFeedingScheduleDTOWithMinimalHorseDetails(FeedingSchedule feedingSchedule);
}


