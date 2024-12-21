package com.hms2.Controller;

import com.hms2.Entity.Property;
import com.hms2.Service.PropertyService;
import com.hms2.payload.PropertyDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {
    private PropertyService propertyService;


    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }


// AddProperty based on cityname & countryname
//   http://localhost:8080/api/v1/property/{cityname}/{countryname}

    @PostMapping("{cityname}/{countryname}")
    public ResponseEntity<PropertyDto> AddProperty(
            @RequestBody PropertyDto propertyDto,
            @PathVariable  String cityname,
            @PathVariable  String countryname

            ){
            PropertyDto propertyDto1 = propertyService.AddProperty(propertyDto, cityname, countryname);
            return new ResponseEntity<>(propertyDto1, HttpStatus.CREATED);
        }

// DeleteProperty based on id
    //  http://localhost:8080/api/v1/property/deleteproperty

    @DeleteMapping("/deleteproperty")
    public String DeleteByProperty(
            @RequestParam Long id
    ) {
        propertyService.deleteById(id);
        return "delete property";
    }

    // UpdateProperty based on id ,city,country
    //  http://localhost:8080/api/v1/property/propertyupdate/{id}/{cityname}/{countryname}

    @PutMapping("/propertyupdate/{id}/{cityname}/{countryname}")
    public ResponseEntity<PropertyDto>UpdateProperty(
            @PathVariable long id,
            @RequestBody PropertyDto propertyDto,
            @PathVariable String cityname,
            @PathVariable String countryname

    ){
        PropertyDto propertyDto1 = propertyService.updateProperty(id, propertyDto, cityname, countryname);
         return  new ResponseEntity<>(propertyDto1, HttpStatus.OK);
    }






// serach property based on city & country
    //   http://localhost:8080/api/v1/property/{searchParam}

    @GetMapping("/{searchParam}")
    public ResponseEntity<List<PropertyDto>> searchProperty(
            @PathVariable String searchParam
    ){
        List<PropertyDto> propertyDto = propertyService.searchProperty(searchParam);
        return  new ResponseEntity<>(propertyDto,HttpStatus.OK);
    }
}