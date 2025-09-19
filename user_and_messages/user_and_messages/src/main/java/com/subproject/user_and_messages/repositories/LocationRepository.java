package com.subproject.user_and_messages.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subproject.user_and_messages.entities.Location;
import com.subproject.user_and_messages.entities.enums.Country;

public interface LocationRepository extends JpaRepository<Location, Long>{

    Optional<Location> findByCountryAndTownIgnoreCaseAndStateProvinceIgnoreCase(
    Country country,
    String town,
    String stateProvince
    );

    
}
