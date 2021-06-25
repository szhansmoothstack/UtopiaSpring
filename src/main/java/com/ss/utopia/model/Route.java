package com.ss.utopia.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "route_generator")
    private Long id;
    @ManyToOne (cascade = {CascadeType.REMOVE}, optional = false, targetEntity = Airport.class)
    @JoinColumn (name = "origin_iata", nullable = false)
    private Airport origin;
    @ManyToOne (cascade = {CascadeType.REMOVE}, optional = false, targetEntity = Airport.class)
    @JoinColumn (name = "destination_iata", nullable = false)
    private Airport destination;

    public Route(Airport origin, Airport destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public Route(Long id, Airport origin, Airport destination) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
    }

    public Route() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(id, route.id) && Objects.equals(origin, route.origin) && Objects.equals(destination, route.destination);
    }
}
