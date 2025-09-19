package com.subproject.user_and_messages.entities;

import com.subproject.user_and_messages.entities.enums.Country;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Location {

    	@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Enumerated(EnumType.STRING)
		private Country country;

		private String town;
	
		private String stateProvince;
    
}
