package com.hms2.Controller;

import com.hms2.Entity.City;
import com.hms2.Repositry.CityRepository;
import com.hms2.Service.CityService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/city")
public class CityController {

    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    // add city
    //http://localhost:8080/api/city/addcity

    @PostMapping("/addcity")
    public ResponseEntity<City> addCity(@RequestBody City city) {
        City city1 = cityService.addCity(city);
        return new ResponseEntity<>(city1, HttpStatus.CREATED);
    }
 // deletecity
    //http://localhost:8080/api/city/deletecity/{cityname}

    @DeleteMapping("deletecity/{cityname}")
    public ResponseEntity<String>DeleteCity(
            @PathVariable String cityname)
    {
        cityService.deleteCity(cityname);
        return new ResponseEntity<>("City deleted successfully", HttpStatus.OK);
    }

}







