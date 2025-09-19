package com.subproject.user_and_messages.dtos.request.search;

import java.util.List;

import com.subproject.user_and_messages.entities.enums.Gender;
import com.subproject.user_and_messages.search.filter.LocationFilter;
import com.subproject.user_and_messages.search.filter.TypeOfExchangeFilter;
import com.subproject.user_and_messages.validators.interfaces.Adult;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserSearchRequest {

    private String nickname;
    private String gender;
    private Integer FromAge;
    private Integer ToAge;
    //private List<String> hobbyNames;
    private LocationSearchRequest location;
    private LanguagePracticeSearchRequest practiceLanguage;
    private LanguageSpokenSearchRequest spokenLanguage;
    private TypeExchSearchRequest contactMethod;
    
}
