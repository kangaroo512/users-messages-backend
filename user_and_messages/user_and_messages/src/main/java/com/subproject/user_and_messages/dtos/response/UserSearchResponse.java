package com.subproject.user_and_messages.dtos.response;

import java.util.Date;
import java.util.List;

import com.subproject.user_and_messages.dtos.LocationDTO;
import com.subproject.user_and_messages.dtos.TypeOfExchangeDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSearchResponse {
	private String nickname;
	private String gender;
	private Integer age;
	private List<String> nativeLanguages;
	private List<String> practicingLanguages;
	private TypeExSearchResponse typeOfExchange;
	private String description;
	private List<String> hobbies;
	private Date profileDate;
	private LocationSearchResponse location;
    
}
