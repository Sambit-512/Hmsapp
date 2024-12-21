package com.hms2.Repositry;

import com.hms2.Entity.City;
import com.hms2.Entity.Country;
import com.hms2.Entity.Property;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {


    // jpQl to search for properties based on city and country
    @Query("Select p FROM Property p join p.city c join p.country co where c.name= :searchparam or co.name= :searchparam")
    List<Property> searchPropertyByCityAndCountry(
            @Param("searchparam")  String searchparam);

           // find properties by name
    // (return a list of properties with the same name)



    List<Property> findByCountry(Country country);
    List<Property> findByCity(City city);
}