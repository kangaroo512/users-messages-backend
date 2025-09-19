package com.subproject.user_and_messages.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subproject.user_and_messages.entities.Hobby;

public interface HobbyRepository extends JpaRepository<Hobby, Long> {
    
    Optional<Hobby> findByName(String name);
    
}
