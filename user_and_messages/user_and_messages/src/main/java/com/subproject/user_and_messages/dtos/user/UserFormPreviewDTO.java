package com.subproject.user_and_messages.dtos.user;

import java.time.LocalDate;
import java.util.List;

import com.subproject.user_and_messages.dtos.LocationDTO;
import com.subproject.user_and_messages.dtos.TypeOfExchangeDTO;
import com.subproject.user_and_messages.dtos.hobby.HobbyFormPreviewDTO;
import com.subproject.user_and_messages.dtos.userLanguage.UserLanguageFormPreviewDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFormPreviewDTO {

    private String nickname;
	private String gender;
	private LocalDate birthdate;
	private List<UserLanguageFormPreviewDTO> nativeLanguages; // map manually
	private List<UserLanguageFormPreviewDTO> practicingLanguages; // map manually
	private TypeOfExchangeDTO typeOfExchange;
	private String description;
	private List<HobbyFormPreviewDTO> hobbies;
	//private Date lastLogin;
	private LocationDTO location;
    
}
