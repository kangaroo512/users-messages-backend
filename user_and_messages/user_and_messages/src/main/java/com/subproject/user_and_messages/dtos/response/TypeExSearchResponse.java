package com.subproject.user_and_messages.dtos.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypeExSearchResponse {
    private Boolean correspondencePenPal;
    private Boolean faceToFaceConversation;
    private String chatSoftware;
}
    
