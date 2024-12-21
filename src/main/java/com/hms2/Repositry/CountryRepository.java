package com.hms2.Repositry;

import com.hms2.Entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {
   Optional<Country> findByName(String name);

}