package com.hms2.Controller;


import com.hms2.Entity.Country;
import com.hms2.Repositry.CountryRepository;
import com.hms2.Service.CountryService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/country")
public class CountryController {

    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    //add country
    //http://localhost:8080/api/country/addcountry

    @PostMapping("/addcountry")
    public ResponseEntity<Country> addCountry
            (@RequestBody Country country) {
        Country country1 = countryService.addCountry(country);
        return new ResponseEntity<>(country1, HttpStatus.CREATED);
    }

        //delete country
    //http://localhost:8080/api/country/deletecountry/{countryname}

    @DeleteMapping("/deletecountry/{countryname}")
    public String DeleteCountry(
            @PathVariable String countryname
    )
        {
        countryService.deleteCountry(countryname);
        return "Deleted country";
    }



}