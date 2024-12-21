package com.hms2.payload;

import com.hms2.Entity.City;
import com.hms2.Entity.Country;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PropertyDto {
    private long id;
    private String name;
    private Integer noOfBedroom;
    private Integer noOfBathroom;
    private City city;
    private Country country;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNoOfBedroom() {
        return noOfBedroom;
    }

    public void setNoOfBedroom(Integer noOfBedroom) {
        this.noOfBedroom = noOfBedroom;
    }

    public Integer getNoOfBathroom() {
        return noOfBathroom;
    }

    public void setNoOfBathroom(Integer noOfBathroom) {
        this.noOfBathroom = noOfBathroom;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}