package com.subproject.user_and_messages.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HobbyDTO {

    @NotBlank(message = "You must enter at least one hobby")
    private String name;
    
}
