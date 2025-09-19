package com.subproject.user_and_messages.search.filter;

import com.subproject.user_and_messages.entities.enums.LanguageProficiency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpokenLanguageFilter {
    private String name;
    private LanguageProficiency level;
}
