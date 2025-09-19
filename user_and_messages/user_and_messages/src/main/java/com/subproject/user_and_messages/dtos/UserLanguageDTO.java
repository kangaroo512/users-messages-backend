package com.subproject.user_and_messages.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLanguageDTO {

    @Valid
    @NotNull(message="A language is required")
    private LanguageDTO language;

    @Pattern(
    regexp = 
    "Beginner|Elementary|Preintermediate|Intermediate|Upperintermediate|Advanced|Proficiency|Native",
    message="You must choose a valid proficiency")
    private String proficiencyLevel;
    
}
