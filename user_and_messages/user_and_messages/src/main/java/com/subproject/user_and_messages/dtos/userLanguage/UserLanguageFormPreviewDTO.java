package com.subproject.user_and_messages.dtos.userLanguage;

import com.subproject.user_and_messages.dtos.language.LanguageFormPreviewDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLanguageFormPreviewDTO {

    private LanguageFormPreviewDTO language;

    private String proficiencyLevel;
    
    
}
