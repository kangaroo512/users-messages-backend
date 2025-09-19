package com.subproject.user_and_messages.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Conversation {
    	@Id 
        @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, orphanRemoval = true)
		private List<Message> messages;

		@OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, orphanRemoval = true)
		private List<ConversationUserMetadata> metadata;

		private LocalDateTime createdAt;
		private LocalDateTime updatedAt;
    
}
