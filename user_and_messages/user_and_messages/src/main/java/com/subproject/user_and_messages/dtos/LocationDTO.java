package com.subproject.user_and_messages.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {
	@NotBlank(message = "You must select a country")
    private String country;

	@NotBlank(message = "You must enter a town")
	private String town;

	@NotBlank(message = "You must enter a province")
	private String stateProvince;
    
}
