package com.subproject.user_and_messages.dtos.request.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationSearchRequest {
    private String country;
    private String city;
    private String state;
    
}
