package com.hms2.Service;

import com.hms2.Entity.City;
import com.hms2.Entity.Country;
import com.hms2.Entity.Property;
import com.hms2.Repositry.CityRepository;
import com.hms2.Repositry.CountryRepository;
import com.hms2.Repositry.PropertyRepository;
import com.hms2.payload.PropertyDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {
    private PropertyRepository propertyRepository;
    private CityRepository cityRepository;
    private CountryRepository countryRepository;
    private ModelMapper modelMapper;

    public PropertyService(PropertyRepository propertyRepository, CityRepository cityRepository, CountryRepository countryRepository, ModelMapper modelMapper) {
        this.propertyRepository = propertyRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
    }


    private Property mapToEntity(PropertyDto propertyDto) {
        Property property = modelMapper.map(propertyDto, Property.class);
        return property;
    }

    private PropertyDto mapToDto(Property property) {
        PropertyDto propertyDto = modelMapper.map(property, PropertyDto.class);
        return propertyDto;
    }


    public PropertyDto AddProperty(PropertyDto propertyDto, String cityname, String countryname) {
        Country country = countryRepository.findByName(countryname).
                orElseThrow(() -> new RuntimeException
                        ("Country with name " + countryname + " not found"));
        City city = cityRepository.findByName(cityname).
                orElseThrow(() -> new RuntimeException
                        ("City with city " + cityname + " not found"));
        Property property = mapToEntity(propertyDto);
        property.setCountry(country);
        property.setCity(city);
        Property save = propertyRepository.save(property);

        PropertyDto propertyDto1 = mapToDto(save);
        return propertyDto1;
    }

    public void deleteById(Long id) {
        propertyRepository.deleteById(id);

    }

    public PropertyDto updateProperty(long id, PropertyDto propertyDto, String cityname, String countryname) {
        Property property = propertyRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Property not found : " + id)
        );
        Country country = countryRepository.findByName(countryname).orElseThrow(
                  () -> new RuntimeException("Country not found : " + countryname)
          );
          City city = cityRepository.findByName(cityname).orElseThrow(
                  () -> new RuntimeException("City not found : " + cityname)
          );

          property.setName(propertyDto.getName());
          property.setNoOfBathroom(propertyDto.getNoOfBathroom());
          property.setNoOfBedroom(propertyDto.getNoOfBedroom());
          property.setCity(propertyDto.getCity());
          property.setCountry(propertyDto.getCountry());

          property.setCity(city);
          property.setCountry(country);

        Property save = propertyRepository.save(property);
        PropertyDto propertyDto1 = mapToDto(save);
        return propertyDto1;

    }

    public List<PropertyDto> searchProperty(String searchParam) {
        List<Property> property = propertyRepository.searchPropertyByCityAndCountry(searchParam);
        List<PropertyDto> propertyDto = property.stream().map(e -> mapToDto(e)).collect(Collectors.toList());

        return propertyDto;

    }



}

