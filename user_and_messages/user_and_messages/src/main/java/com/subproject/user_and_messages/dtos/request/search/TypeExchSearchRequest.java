package com.subproject.user_and_messages.dtos.request.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypeExchSearchRequest {
    private boolean isCorrespondencePenPal;
    private boolean isFaceToFaceConversation;
    private String chatSoftware;
    
}
