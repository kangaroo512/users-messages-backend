package com.subproject.user_and_messages.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.subproject.user_and_messages.entities.User;

public interface UserRepository extends JpaRepository <User, Long>, JpaSpecificationExecutor<User> {

    @Query("SELECT u FROM User u WHERE u.nickname = :nickname")
    Optional<User> getByNickname(@Param("nickname") String nickname);
    
}
