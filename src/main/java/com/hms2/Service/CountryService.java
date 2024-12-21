package com.hms2.Service;

import com.hms2.Entity.Country;
import com.hms2.Entity.Property;
import com.hms2.Entity.Review;
import com.hms2.Repositry.CountryRepository;
import com.hms2.Repositry.PropertyRepository;
import com.hms2.Repositry.ReviewRepository;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class
CountryService {
    private CountryRepository countryRepository;
    private final PropertyRepository propertyRepository;
    private ReviewRepository reviewRepository;

    public CountryService(CountryRepository countryRepository,
                          PropertyRepository propertyRepository, ReviewRepository reviewRepository) {
        this.countryRepository = countryRepository;

        this.propertyRepository = propertyRepository;
        this.reviewRepository = reviewRepository;
    }


    public Country addCountry(Country country) {
        Optional<Country> opName = countryRepository.findByName(country.getName());
        if (opName.isPresent()) {
            throw new RuntimeException("Country already exists");
        }
        Country country1= countryRepository.save(country);

        return country1;

    }





    @Transactional
    public void deleteCountry(String countryname) {
        // Fetch the country by name
        Country country = countryRepository.findByName(countryname)
                .orElseThrow(() -> new RuntimeException("Could not find country: " + countryname));

        // Fetch properties associated with the country
        List<Property> properties = propertyRepository.findByCountry(country);
        for (Property property : properties) {
            // Fetch and delete reviews associated with the property
            List<Review> review = reviewRepository.findByProperty(property);
            if (!review.isEmpty()) {
                reviewRepository.deleteAll(review);
            }

            // Delete the property
            propertyRepository.delete(property);
        }

        // Delete the country
        countryRepository.delete(country);
    }
}
