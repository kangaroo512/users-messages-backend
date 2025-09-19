package com.subproject.user_and_messages.dtos.request.search;

import com.subproject.user_and_messages.entities.enums.LanguageProficiency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LanguagePracticeSearchRequest {
    private String languageName;
    private String fromLevel;
    private String toLevel;
}
