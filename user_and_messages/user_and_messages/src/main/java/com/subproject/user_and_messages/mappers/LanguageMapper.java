package com.subproject.user_and_messages.mappers;

import org.mapstruct.Mapper;

import com.subproject.user_and_messages.dtos.LanguageDTO;
import com.subproject.user_and_messages.entities.Language;

@Mapper(componentModel = "spring")
public interface LanguageMapper {

    Language languageDTOtOLanguage(LanguageDTO languageDTO);
    
}
