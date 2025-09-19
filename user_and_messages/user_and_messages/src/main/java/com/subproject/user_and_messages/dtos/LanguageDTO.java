package com.subproject.user_and_messages.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LanguageDTO {

    @NotBlank(message="You must enter a language")
    private String name;

    
    @NotBlank(message="You must include a language code")
    private String isoCode;
    
}
