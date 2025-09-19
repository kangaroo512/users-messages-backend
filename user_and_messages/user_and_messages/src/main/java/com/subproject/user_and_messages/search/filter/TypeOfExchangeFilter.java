package com.subproject.user_and_messages.search.filter;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypeOfExchangeFilter {
    private boolean isCorrespondencePenPal;
    private boolean isFaceToFaceConversation;
    private String chatSoftware;
}
