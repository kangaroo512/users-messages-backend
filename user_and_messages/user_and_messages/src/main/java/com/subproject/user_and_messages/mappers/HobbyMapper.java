package com.subproject.user_and_messages.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.subproject.user_and_messages.dtos.HobbyDTO;
import com.subproject.user_and_messages.entities.Hobby;

@Mapper(componentModel = "spring")
public interface HobbyMapper {

    @Mapping(target = "id", ignore = true)
    Hobby hobbyDTOToHobby(HobbyDTO hobbyDTO);

    
}