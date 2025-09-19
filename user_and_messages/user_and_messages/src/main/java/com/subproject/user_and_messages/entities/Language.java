package com.subproject.user_and_messages.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Language {

    	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		//@OneToMany(mappedBy = "language")
		//List<UserLanguage> userLanguages;

		private String name;
		private String isoCode;
    
}
