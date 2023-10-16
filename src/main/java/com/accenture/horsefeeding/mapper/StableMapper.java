package com.accenture.horsefeeding.mapper;

import com.accenture.horsefeeding.dto.StableDto;
import com.accenture.horsefeeding.model.Stable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StableMapper {
    StableDto toDto(Stable stable);
    Stable toEntity(StableDto dto);
}
