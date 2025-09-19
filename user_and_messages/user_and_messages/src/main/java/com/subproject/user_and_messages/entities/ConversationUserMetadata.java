package com.subproject.user_and_messages.entities;

import java.time.LocalDateTime;

import com.subproject.user_and_messages.entities.embeddable.ConversationUserMetadataId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import lombok.Data;
import jakarta.persistence.ManyToOne;

@Entity
@Data
public class ConversationUserMetadata {

        @EmbeddedId
		private ConversationUserMetadataId id;

		@ManyToOne
		@MapsId("conversationId")
		@JoinColumn(name = "conversation_id")
		private Conversation conversation;
	
		@ManyToOne
		@MapsId("userId")
		@JoinColumn(name = "user_id")
		private User user;

		private LocalDateTime persistedSentAt;

		private boolean isInboxed;
		private boolean isSent;
		private boolean isArchived;
		private boolean isTrashed;
		private boolean isSpam;
		private boolean isRead;
    
}
