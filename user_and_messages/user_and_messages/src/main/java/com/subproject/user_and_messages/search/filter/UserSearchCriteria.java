package com.subproject.user_and_messages.search.filter;

import java.util.List;

import com.subproject.user_and_messages.entities.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchCriteria {
    private String nickname;
    private Gender gender;
    private Integer fromAge;
    private Integer toAge;
    private List<String> hobbyNames;
    private LocationFilter location;
    private SpokenLanguageFilter spokenLanguage;
    private LanguagePracticeFilter practiceLanguage;
    private TypeOfExchangeFilter contactMethod;
}
