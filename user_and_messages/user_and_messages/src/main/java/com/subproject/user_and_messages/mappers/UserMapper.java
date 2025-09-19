package com.subproject.user_and_messages.mappers;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.subproject.user_and_messages.dtos.HobbyDTO;
import com.subproject.user_and_messages.dtos.LanguageDTO;
import com.subproject.user_and_messages.dtos.LocationDTO;
import com.subproject.user_and_messages.dtos.TypeOfExchangeDTO;
import com.subproject.user_and_messages.dtos.UserLanguageDTO;
import com.subproject.user_and_messages.dtos.hobby.HobbyFormPreviewDTO;
import com.subproject.user_and_messages.dtos.user.UserCreateDTO;
import com.subproject.user_and_messages.dtos.user.UserFormPreviewDTO;
import com.subproject.user_and_messages.dtos.user.UserPreviewDTO;
import com.subproject.user_and_messages.dtos.user.UserUpdateDTO;
import com.subproject.user_and_messages.entities.Hobby;
import com.subproject.user_and_messages.entities.Language;
import com.subproject.user_and_messages.entities.Location;
import com.subproject.user_and_messages.entities.TypeOfExchange;
import com.subproject.user_and_messages.entities.User;
import com.subproject.user_and_messages.entities.UserLanguage;
import com.subproject.user_and_messages.entities.enums.Gender;
import com.subproject.user_and_messages.entities.enums.LanguageProficiency;

@Mapper(componentModel = "spring", uses = {
    LocationMapper.class,
    HobbyMapper.class
})
public interface UserMapper {


    @Mapping(source = "nickname", target = "nickname")
    @Mapping(source = "gender", target = "gender", qualifiedByName = "mapGender")
    @Mapping(source = "birthdate", target = "age", qualifiedByName = "mapAge")
    @Mapping(source = "userLanguages", target = "nativeLanguages", qualifiedByName = "mapNativeLanguage")
    @Mapping(source = "userLanguages", target = "practicingLanguages", qualifiedByName = "mapPracticeLanguage")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "hobbies", target = "hobbies", qualifiedByName = "mapHobbies")
    @Mapping(source = "typeOfExchange", target = "typeOfExchange", qualifiedByName = "mapExchangeType")
    @Mapping(source = "firstCreated", target = "profileDate")
    @Mapping(source ="location", target ="location", qualifiedByName = "mapLocation")
    UserPreviewDTO userToPreview(User user);

    @Mapping(source = "nickname", target = "nickname")
    @Mapping(source = "gender", target = "gender", qualifiedByName = "mapGender")
    @Mapping(source = "birthdate", target = "birthdate")
    @Mapping(source = "userLanguages", target = "nativeLanguages", ignore = true)
    @Mapping(source = "userLanguages", target = "practicingLanguages", ignore = true)
    @Mapping(source = "description", target = "description")
    @Mapping(source = "hobbies", target = "hobbies", qualifiedByName = "mapHobbyFormPreview")
    @Mapping(source = "typeOfExchange", target = "typeOfExchange", qualifiedByName = "mapExchangeType")
    @Mapping(source ="location", target ="location", qualifiedByName = "mapLocation")
    UserFormPreviewDTO userToUserFormPreviewDTO(User user);

    @Named("mapHobbyFormPreview")
    default List<HobbyFormPreviewDTO> mapHobbyFormPreview(List<Hobby> hobbies) {
        return hobbies.stream()
        .map(p -> HobbyFormPreviewDTO.builder().name(p.getName()).build())
        .collect(Collectors.toList());
    }


    @Named("mapLocation")
    default LocationDTO mapLocation(Location location) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setCountry(location.getCountry().toString());
        locationDTO.setStateProvince(location.getStateProvince());
        locationDTO.setTown(location.getTown());
        return locationDTO;

    }

    @Named("mapExchangeType")
    default TypeOfExchangeDTO mapExchangeType(TypeOfExchange type) {
        TypeOfExchangeDTO typeDTO = new TypeOfExchangeDTO();
        typeDTO.setChatSoftware(type.getChatSoftware().toString());
        typeDTO.setCorrespondencePenPal(type.getCorrespondancePenPal());
        typeDTO.setFaceToFaceConversation(type.getFaceToFaceConversation());
        return typeDTO;


    }

    @Named("mapGender")
    default String mapGender(Enum<?> gender) {
        return gender != null ? gender.name() : null;
    }

    @Named("mapAge")
    default Integer mapAge(LocalDate birthdate) {
        return birthdate != null ? Period.between(birthdate, LocalDate.now()).getYears() : null;
    }

    @Named("mapNativeLanguage")
    default List<String> mapNativeLanguages(List<UserLanguage> langs) {
        if(langs == null) return Collections.emptyList();

        return langs.stream()
        .filter(lang -> lang.getProficiencyLevel() == LanguageProficiency.NATIVE)
        .map(lang -> lang.getLanguage().getName())
        .collect(Collectors.toList());
    }
    
    @Named("mapPracticeLanguage")
    default List<String> mapPracticingLanguages(List<UserLanguage> langs) {
        if(langs == null) return Collections.emptyList();

        return langs.stream()
        .filter(lang -> lang.getProficiencyLevel() != LanguageProficiency.NATIVE)
        .map(lang -> lang.getLanguage().getName())
        .collect(Collectors.toList());
    }

    @Named("mapHobbies")
    default List<String> mapHobbies(List<Hobby> hobbies) {
        if(hobbies == null) return Collections.emptyList();

        return hobbies.stream()
        .map(hobby -> hobby.getName())
        .collect(Collectors.toList());
    }


    @Mapping(target = "id", ignore = true)
    @Mapping(source = "nickname", target = "nickname")
    @Mapping(source = "gender", target = "gender", qualifiedByName = "mapStringToGender")
    @Mapping(source = "location", target = "location", ignore = true)
    @Mapping(source = "birthDate", target = "birthdate")
    @Mapping(source = "userLanguageDTOs", target = "userLanguages", ignore = true)
    @Mapping(source = "typeOfExchange", target = "typeOfExchange", ignore = true) // add user
    @Mapping(source = "description", target = "description")
    @Mapping(source = "hobbies", target = "hobbies", ignore = true) //dto
    public void userUpdateDTOToUser(UserUpdateDTO userUpdateDTO, @MappingTarget User user);

    @Named("mapStringToGender")
    default Gender mapStringToGender(String gender) {
        return Gender.valueOf(gender.toUpperCase());
    }
    



    @Mapping(source = "gender", target = "gender", qualifiedByName = "mapGender")
    @Mapping(source = "location", target = "location", qualifiedByName = "mapToLocationDTO")
    @Mapping(source = "typeOfExchange", target = "typeOfExchange", qualifiedByName = "mapToTypeOfExchangeDTO")
    @Mapping(source = "userLanguages", target = "userLanguageDTOs")
    @Mapping(source = "hobbies", target ="hobbies", ignore = true )
    UserCreateDTO userToUserCreateDto(User user);

    @Named("mapToLocationDTO")
    default LocationDTO mapToLocationDTO(Location location) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setCountry(location.getCountry().toString());
        locationDTO.setStateProvince(location.getStateProvince());
        locationDTO.setTown(location.getTown());
        return locationDTO;
        
    }

    @Named("mapToTypeOfExchangeDTO")
    default TypeOfExchangeDTO mapToTypeOfExchangeDTO(TypeOfExchange typeOfExchange) {
        TypeOfExchangeDTO typeOfExchangeDTO = new TypeOfExchangeDTO();
        typeOfExchangeDTO.setChatSoftware(typeOfExchangeDTO.getChatSoftware().toString());
        typeOfExchangeDTO.setCorrespondencePenPal(typeOfExchangeDTO.getCorrespondencePenPal());
        typeOfExchangeDTO.setFaceToFaceConversation(typeOfExchangeDTO.getFaceToFaceConversation());
        return typeOfExchangeDTO;

    }

    @Named("mapToHobbyDTOs")
    default List<HobbyDTO> mapToHobbyDTOs(List<Hobby> hobbies) {
        return hobbies.stream()
        .map(p -> {
            HobbyDTO hobbyDTO = new HobbyDTO();
            hobbyDTO.setName(p.getName());
            return hobbyDTO;
        })
        .collect(Collectors.toList());
    }


    @Named("mapToListUserLanguageDTO")
    default List<UserLanguageDTO> mapToListUserLanguageDTO(List<UserLanguage> userLanguages) {
        return userLanguages.stream()
        .map(this::mapToUserLanguageDTO)
        .collect(Collectors.toList());

    }

    default UserLanguageDTO mapToUserLanguageDTO(UserLanguage userLanguage) {
        UserLanguageDTO userLanguageDTO = new UserLanguageDTO();
        userLanguageDTO.setLanguage(mapToLanguageDTO(userLanguage.getLanguage()));
        userLanguageDTO.setProficiencyLevel(userLanguage.getProficiencyLevel().toString());
        return userLanguageDTO;
    }

    default LanguageDTO mapToLanguageDTO(Language language) {
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setIsoCode(language.getIsoCode());
        languageDTO.setName(language.getName());
        return languageDTO;

    }

    


}
