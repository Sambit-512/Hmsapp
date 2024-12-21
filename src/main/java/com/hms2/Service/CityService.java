package com.hms2.Service;

import com.hms2.Entity.City;
import com.hms2.Entity.Property;
import com.hms2.Entity.Review;
import com.hms2.Repositry.CityRepository;
import com.hms2.Repositry.PropertyRepository;

import com.hms2.Repositry.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private CityRepository cityRepository;
    private PropertyRepository propertyRepository;
    private ReviewRepository reviewRepository;

    public CityService(CityRepository cityRepository, PropertyRepository propertyRepository, ReviewRepository reviewRepository) {
        this.cityRepository = cityRepository;

        this.propertyRepository = propertyRepository;
        this.reviewRepository = reviewRepository;
    }



    public City addCity(City city) {
        City city1= cityRepository.save(city);
        return city1;

    }

    public void deleteCity(String cityname) {
        City city = cityRepository.findByName(cityname).orElseThrow(
                () -> new RuntimeException("Could not find city :" + cityname));
                 List<Property> properties= propertyRepository.findByCity(city);
                 for(Property property : properties){

                     List<Review> review = reviewRepository.findByProperty(property);
                     if (!review.isEmpty()) {
                         reviewRepository.deleteAll(review);
                     }

                     // Delete the property
                     propertyRepository.delete(property);
                 }

        // Delete the country
       cityRepository.delete(city);
    }
}




