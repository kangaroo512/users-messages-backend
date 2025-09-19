package com.subproject.user_and_messages.search.filter;

import com.subproject.user_and_messages.entities.enums.LanguageProficiency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class LanguagePracticeFilter {
    private String languageName;
    private LanguageProficiency fromLevel;
    private LanguageProficiency toLevel;
    
}
