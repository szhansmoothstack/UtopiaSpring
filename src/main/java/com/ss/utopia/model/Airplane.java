package com.ss.utopia.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "airplane")
public class Airplane {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "airplane_generator")
    @Column (name = "airplane_id")
    private Long id;
    @ManyToOne (optional = false, targetEntity = AirplaneType.class)
    @JoinColumn (name = "airplane_type_id", nullable = false)
    private AirplaneType airplaneType;

    public Airplane(Long id, AirplaneType airplaneType) {
        this.id = id;
        this.airplaneType = airplaneType;
    }

    public Airplane() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AirplaneType getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(AirplaneType airplaneType) {
        this.airplaneType = airplaneType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airplane airplane = (Airplane) o;
        return id.equals(airplane.id) && airplaneType.equals(airplane.airplaneType);
    }
}
