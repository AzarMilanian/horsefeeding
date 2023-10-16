package com.accenture.horsefeeding.mapper;

import com.accenture.horsefeeding.dto.HorseDto;
import com.accenture.horsefeeding.model.Horse;
import com.accenture.horsefeeding.model.Owner;
import com.accenture.horsefeeding.model.Stable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface HorseMapper {
    Horse toEntity(HorseDto dto);
    @Mapping(source = "owner.ownerId", target = "ownerId")
    @Mapping(source = "stable.stableId", target = "stableId")
    HorseDto toDto(Horse entity);

}


/*
@Mapper(componentModel = "spring")
public interface HorseMapper {

    @Mapping(source = "ownerId", target = "owner", qualifiedByName = "ownerFromId")
    @Mapping(source = "stableId", target = "stable", qualifiedByName = "stableFromId")
    Horse toEntity(HorseDto dto);

    @Mapping(source = "owner.ownerId", target = "ownerId")
    @Mapping(source = "stable.stableId", target = "stableId")
    HorseDto toDto(Horse entity);

    @Named("stableFromId")
    default Stable stableFromId(Long id) {
        if (id == null) {
            return null;
        }
        Stable stable = new Stable();
        stable.setStableId(id);
        return stable;
    }

    @Named("ownerFromId")
    default Owner ownerFromId(Long id) {
        if (id == null) {
            return null;
        }
        Owner owner = new Owner();
        owner.setOwnerId(id);
        return owner;
    }
}
*/
