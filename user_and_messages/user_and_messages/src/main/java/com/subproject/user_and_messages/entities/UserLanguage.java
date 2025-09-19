package com.subproject.user_and_messages.entities;

import com.subproject.user_and_messages.entities.embeddable.UserLanguageId;
import com.subproject.user_and_messages.entities.enums.LanguageProficiency;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;

@Entity
@Data
public class UserLanguage {

    	@EmbeddedId
		private UserLanguageId userLanguageId;

		@ManyToOne
		@MapsId("userId")
		@JoinColumn(name = "user_id")
		private User user;

		@ManyToOne
		@MapsId("languageId")
		@JoinColumn(name = "language_id")
		private Language language;

		@Enumerated(EnumType.STRING)
		private LanguageProficiency proficiencyLevel;
    
}
