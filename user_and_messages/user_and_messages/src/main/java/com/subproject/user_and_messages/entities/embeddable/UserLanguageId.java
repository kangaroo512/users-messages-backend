package com.subproject.user_and_messages.entities.embeddable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLanguageId {

    @EqualsAndHashCode.Include
    private Long userId;

    @EqualsAndHashCode.Include
	private Long languageId;

    
}
