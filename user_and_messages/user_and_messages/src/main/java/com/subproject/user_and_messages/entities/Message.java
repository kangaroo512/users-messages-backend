package com.subproject.user_and_messages.entities;

import java.time.OffsetDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Entity
@Data
public class Message {

    	@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@ManyToOne
		@JoinColumn(name = "sender_id")
		private User sender;

		@ManyToOne
		@JoinColumn(name = "recipient_id")
		private User recipient;

		@ManyToOne
		@JoinColumn(name = "conversation_id")
		private Conversation conversation;
		
		
		private String content;
		private OffsetDateTime sentAt;
		
    
}
