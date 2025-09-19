package com.subproject.user_and_messages.entities;

import java.time.LocalDate;
import java.util.List;

import com.subproject.user_and_messages.audit.AuditableEntity;
import com.subproject.user_and_messages.entities.enums.Gender;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends AuditableEntity {

    	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		private String nickname;

		private String email;

		@ManyToOne
		@JoinColumn(name = "location")
		private Location location;

		@Enumerated(EnumType.STRING)
		private Gender gender;

		private LocalDate birthdate;

		@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
		private List<UserLanguage> userLanguages;
		
		@Embedded
		private TypeOfExchange typeOfExchange;

		@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
		private List<ConversationUserMetadata> conversationUserMetadata;

		@ManyToMany
		@JoinTable(
			name = "user_hobbies",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "hobbie_id")
		)
		private List<Hobby> hobbies;
		private String description;

		private String password;

    
}
