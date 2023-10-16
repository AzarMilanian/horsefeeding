package com.accenture.horsefeeding.mapper;

import com.accenture.horsefeeding.dto.ScheduleRangeDto;
import com.accenture.horsefeeding.model.ScheduleRange;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ScheduleRangeMapper {

    @Mapping(target = "scheduleId", source = "feedingSchedule.scheduleId")
    ScheduleRangeDto toDTO(ScheduleRange scheduleRange);

}

