package com.subproject.user_and_messages.validators;

import java.time.LocalDate;
import java.time.Period;

import com.subproject.user_and_messages.validators.interfaces.Adult;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AdultValidator implements ConstraintValidator<Adult, LocalDate> {
    
    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext context) {
        if(birthDate == null) {
            return false;
        }
        return Period.between(birthDate, LocalDate.now()).getYears() >= 16;

    }
}
