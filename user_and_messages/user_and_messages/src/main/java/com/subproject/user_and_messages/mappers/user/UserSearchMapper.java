package com.subproject.user_and_messages.mappers.user;


import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.subproject.user_and_messages.dtos.request.search.LocationSearchRequest;
import com.subproject.user_and_messages.dtos.request.search.UserSearchRequest;
import com.subproject.user_and_messages.dtos.response.LocationSearchResponse;
import com.subproject.user_and_messages.dtos.response.TypeExSearchResponse;
import com.subproject.user_and_messages.dtos.response.UserSearchResponse;
import com.subproject.user_and_messages.entities.Language;
import com.subproject.user_and_messages.entities.User;
import com.subproject.user_and_messages.entities.enums.Gender;
import com.subproject.user_and_messages.entities.enums.LanguageProficiency;
import com.subproject.user_and_messages.search.filter.LanguagePracticeFilter;
import com.subproject.user_and_messages.search.filter.LocationFilter;
import com.subproject.user_and_messages.search.filter.SpokenLanguageFilter;
import com.subproject.user_and_messages.search.filter.TypeOfExchangeFilter;
import com.subproject.user_and_messages.search.filter.UserSearchCriteria;

@Component
public class UserSearchMapper {

    private static final Logger logger = LoggerFactory.getLogger(UserSearchMapper.class);

    public UserSearchCriteria requestToCriteria(UserSearchRequest request) {

        //Type of Exchange
        TypeOfExchangeFilter newContactMethod = TypeOfExchangeFilter.builder()
            .chatSoftware(request.getContactMethod().getChatSoftware())
            .isCorrespondencePenPal(request.getContactMethod().isCorrespondencePenPal())
            .isFaceToFaceConversation(request.getContactMethod().isFaceToFaceConversation())
            .build();

        //spoken language
        SpokenLanguageFilter spokenFilter = SpokenLanguageFilter.builder()
        .name(request.getSpokenLanguage().getName())
        //.level(LanguageProficiency.valueOf(request.getSpokenLanguage().getProficiencyLevel()))
        .build();

        if(request.getSpokenLanguage().getProficiencyLevel() != null && !request.getSpokenLanguage().getProficiencyLevel().isEmpty()) {
            spokenFilter.setLevel(LanguageProficiency.valueOf(request.getSpokenLanguage().getProficiencyLevel()));
        }

        
        //Log message for the proficiency levels
        logger.info("🚨🚨🚨 [IMPORTANT]" + request.getPracticeLanguage().getFromLevel().toUpperCase());

        //practice language
        String fromLevel = request.getPracticeLanguage().getFromLevel();
        String toLevel = request.getPracticeLanguage().getToLevel();



        LanguagePracticeFilter practiceFilter = LanguagePracticeFilter.builder()
        .languageName(request.getPracticeLanguage().getLanguageName())
        //.fromLevel(LanguageProficiency.valueOf(request.getPracticeLanguage().getFromLevel().toUpperCase()))
        //.toLevel(LanguageProficiency.valueOf(request.getPracticeLanguage().getToLevel().toUpperCase()))
        .build();

        if(fromLevel != null && !fromLevel.isEmpty() ) {
            LanguageProficiency fromLevelEnum = LanguageProficiency.valueOf(fromLevel.toUpperCase());
            practiceFilter.setFromLevel(fromLevelEnum);
        }




        //location
        LocationFilter locationFilter = LocationFilter.builder()
        .city(request.getLocation().getCity())
        .country(request.getLocation().getCountry())
        .state(request.getLocation().getState())
        .build();





        //Map the easy ones
        UserSearchCriteria criteria = UserSearchCriteria.builder()
        .nickname(request.getNickname())
        .fromAge(request.getFromAge())
        .toAge(request.getToAge())
        .contactMethod(newContactMethod)
        .spokenLanguage(spokenFilter)
        .practiceLanguage(practiceFilter)
        .location(locationFilter)
        .build();

        //Gender
        String genderReq = request.getGender();
        if(genderReq != null && !genderReq.isEmpty()) {
            Gender genderReqEnum = Gender.valueOf(genderReq.toUpperCase());
            criteria.setGender(genderReqEnum);
        }


        return criteria;
    }
    
    public UserSearchResponse userToResponse(User user) {

        //hobbies
        List<String> hobbyList = user.getHobbies().stream()
        .map(p -> p.getName())
        .collect(Collectors.toList());

        //Location
        LocationSearchResponse locationRes = LocationSearchResponse.builder()
        .city(user.getLocation().getTown())
        .country(toPascalCase(user.getLocation().getCountry().name()))
        .state(user.getLocation().getStateProvince())
        .build();


        //Native languages (in string)
        List<String> nativeLanguageList = user.getUserLanguages().stream()
        .filter(p -> p.getProficiencyLevel().equals(LanguageProficiency.NATIVE))
        .map(p -> p.getLanguage().getName())
        .collect(Collectors.toList());

        //Practice langyuages (in string)
        List<String> practiceLanguageList = user.getUserLanguages().stream()
        .filter(p -> p.getProficiencyLevel() != LanguageProficiency.NATIVE)
        .map(p -> p.getLanguage().getName())
        .collect(Collectors.toList());

        //Type of exchange
        TypeExSearchResponse typeExResponse = TypeExSearchResponse.builder()
        .chatSoftware(toPascalCase(user.getTypeOfExchange().getChatSoftware().name()))
        .correspondencePenPal(user.getTypeOfExchange().getCorrespondancePenPal())
        .faceToFaceConversation(user.getTypeOfExchange().getFaceToFaceConversation())
        .build();
        


        UserSearchResponse response = UserSearchResponse.builder()
        .age(Integer.valueOf(Period.between(user.getBirthdate(), LocalDate.now()).getYears()))
        .description(user.getDescription())
        .gender(String.valueOf(user.getGender()))
        .hobbies(hobbyList)
        .location(locationRes)
        .nickname(user.getNickname())
        .profileDate(user.getFirstCreated())
        .nativeLanguages(nativeLanguageList)
        .practicingLanguages(practiceLanguageList)
        .typeOfExchange(typeExResponse)
        .build();

        return new UserSearchResponse();

    }

    private String toPascalCase(String input) {
        
        if(input == null || input.isEmpty()) return input;

        String[] words = input.split("\\s+|_+|-+");
        StringBuilder sb = new StringBuilder();

        for(String word : words) {
            if(word.isEmpty()) continue;
            sb.append(Character.toUpperCase(word.charAt(0)));
            if(word.length() > 1) {
                sb.append(word.substring(1).toLowerCase());
            }
        }

        return sb.toString();
        

    }
    
}
