package com.subproject.user_and_messages.dtos.user;

import java.time.LocalDate;
import java.util.List;

import com.subproject.user_and_messages.dtos.HobbyDTO;
import com.subproject.user_and_messages.dtos.LocationDTO;
import com.subproject.user_and_messages.dtos.TypeOfExchangeDTO;
import com.subproject.user_and_messages.dtos.UserLanguageDTO;
import com.subproject.user_and_messages.validators.interfaces.Adult;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {
	@NotBlank(message="You must enter a nickname")
    private String nickname;

	@NotBlank(message="You must enter a gender")
	private String gender;

	@Valid
	@NotNull(message = "A Locatio is required")
	private LocationDTO location;

	@Adult
	@Past(message = "the birthdate must be in the past")
	private LocalDate birthDate;

	@Valid
	@NotEmpty(message="at least one language is required")
	private List<UserLanguageDTO> userLanguageDTOs;

	@Valid
	@NotNull(message = "the type of exchanged is required")
	private TypeOfExchangeDTO typeOfExchange;

	@NotBlank(message = "You must enter a description")
	@Size(max = 500, message = "The description must have 500 characters at most")
	private String description;

	@Valid
	@NotEmpty(message = "There must be at least one hobby")
	private List<HobbyDTO> hobbies;
	//private Date lastLogin;
	//private Date profileDate;
	@NotBlank(message = "You must enter a password")
	private String password;

	@NotBlank(message = "You must enter an email")
	@Email(message = "You must enter a valid email")
	private String email;
    
}
