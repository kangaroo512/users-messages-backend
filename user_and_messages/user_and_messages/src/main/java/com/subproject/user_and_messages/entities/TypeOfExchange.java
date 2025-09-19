package com.subproject.user_and_messages.entities;

import com.subproject.user_and_messages.entities.enums.ChatSoftware;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class TypeOfExchange {

    private Boolean faceToFaceConversation;
    private Boolean correspondancePenPal;

    @Enumerated(EnumType.STRING)
    private ChatSoftware chatSoftware;
    
}
