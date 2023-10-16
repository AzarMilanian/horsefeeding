package com.accenture.horsefeeding.mapper;

import com.accenture.horsefeeding.dto.FoodScheduleDto;
import com.accenture.horsefeeding.model.FoodSchedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodScheduleMapper {
    FoodScheduleDto toDTO(FoodSchedule foodSchedule);
}

