package com.hms2.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "no_of_bedroom", nullable = false)
    private Integer noOfBedroom;

    @Column(name = "no_of_bathroom", nullable = false)
    private Integer noOfBathroom;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

//    @Column(name = "no_of_guest" ,nullable = false)
//    private Integer noOfGuest;






    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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