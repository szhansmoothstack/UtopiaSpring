package com.ss.utopia.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "airport")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Airport {

    @Id
    @Column (name = "iata_id")

    private String iataId;

    @Column (nullable = false)
    private String city;

    public String getIataId() {
        return iataId;
    }

    public void setIataId(String iataId) {
        this.iataId = iataId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Airport(String iataId, String city) {
        this.iataId = iataId;
        this.city = city;
    }

    public Airport() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return Objects.equals(iataId, airport.iataId) && Objects.equals(city, airport.city);
    }

}
