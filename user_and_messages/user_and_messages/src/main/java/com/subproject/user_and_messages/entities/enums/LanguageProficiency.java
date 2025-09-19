package com.subproject.user_and_messages.entities.enums;

import com.subproject.user_and_messages.entities.Language;

public enum LanguageProficiency {

    	BEGINNER(1),
		ELEMENTARY(2),
		PREINTERMEDIATE(3),
		INTERMEDIATE(4),
		UPPERINTERMEDIATE(5),
		ADVANCED(6),
		PROFICIENCY(7),
		NATIVE(8);

	private final int level;

	LanguageProficiency(int level) {
		this.level = level;
	}

	public int getLevel() {
		return this.level;
	}

	public boolean isHigherThan(LanguageProficiency other) {
		return this.level > other.level;
	}
    
	public boolean isLowerThan(LanguageProficiency other) {
		return this.level < other.level;
	}
}
