package com.subproject.user_and_messages.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.subproject.user_and_messages.dtos.LocationDTO;
import com.subproject.user_and_messages.entities.Location;
import com.subproject.user_and_messages.entities.enums.Country;

@Mapper(componentModel = "spring")
public interface LocationMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(source = "country" ,target = "country", qualifiedByName = "mapCountry")
    Location LocationDTOToLocation(LocationDTO LocationDTO);

    @Named("mapCountry")
    default Country mapCountry(String country) {
        return Country.valueOf(country.toUpperCase());
    }
    
}
