package com.subproject.user_and_messages.entities.embeddable;


import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class ConversationUserMetadataId {

    @EqualsAndHashCode.Include
    private Long conversationId;


    @EqualsAndHashCode.Include
	private Long userId;




    
}
