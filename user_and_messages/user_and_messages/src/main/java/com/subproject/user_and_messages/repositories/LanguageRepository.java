package com.subproject.user_and_messages.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subproject.user_and_messages.entities.Language;

public interface LanguageRepository extends JpaRepository<Language, Long> {

    public Optional<Language> findByIsoCode(String isoCode);
    
}
