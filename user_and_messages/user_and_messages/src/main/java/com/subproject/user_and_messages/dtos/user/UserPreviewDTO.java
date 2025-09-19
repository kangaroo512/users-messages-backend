package com.subproject.user_and_messages.dtos.user;


import java.util.Date;
import java.util.List;

import com.subproject.user_and_messages.dtos.LocationDTO;
import com.subproject.user_and_messages.dtos.TypeOfExchangeDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPreviewDTO {
	private String nickname;
	private String gender;
	private Integer age;
	private List<String> nativeLanguages;
	private List<String> practicingLanguages;
	private TypeOfExchangeDTO typeOfExchange;
	private String description;
	private List<String> hobbies;
	private Date profileDate;
	private LocationDTO location;
}
