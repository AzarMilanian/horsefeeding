package com.accenture.horsefeeding.mapper;

import com.accenture.horsefeeding.dto.FeedingRecordDto;
import com.accenture.horsefeeding.model.FeedingRecord;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for {@link FeedingRecord} and {@link FeedingRecordDto} conversions.
 */
@Mapper(componentModel = "spring", uses = {HorseMapper.class, FeedingScheduleMapper.class})
public interface FeedingRecordMapper {
    /**
     * Maps {@link FeedingRecord} to {@link FeedingRecordDto}.
     *
     * @param feedingRecord the source entity
     * @return the mapped DTO
     */
    @Mapping(source = "feedingRecord.horse.horseId", target = "horseId")
    @Mapping(source = "feedingRecord.feedingSchedule.scheduleId", target = "feedingScheduleId")
    FeedingRecordDto toDto(FeedingRecord feedingRecord);


    /**
     * Maps {@link FeedingRecordDto} to {@link FeedingRecord}.
     *
     * @param feedingRecordDto the source DTO
     * @return the mapped entity
     */
    @InheritInverseConfiguration
    FeedingRecord toEntity(FeedingRecordDto feedingRecordDto);
}

