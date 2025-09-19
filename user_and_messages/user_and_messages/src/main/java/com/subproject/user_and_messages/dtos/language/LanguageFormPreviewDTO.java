package com.subproject.user_and_messages.dtos.language;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LanguageFormPreviewDTO {

    private String name;
    private String isoCode;
    
}
