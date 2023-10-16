package com.accenture.horsefeeding.mapper;

import com.accenture.horsefeeding.dto.OwnerDto;
import com.accenture.horsefeeding.model.Owner;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OwnerMapper {
    OwnerDto toDTO(Owner owner);
}

