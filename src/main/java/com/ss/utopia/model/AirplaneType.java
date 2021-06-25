package com.ss.utopia.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "airplane_type")
public class AirplaneType {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "airplane_type_id")
    private Long id;
    @Column (name = "max_capacity", nullable = false)
    private int capacity;

    public AirplaneType(Long id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public AirplaneType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirplaneType that = (AirplaneType) o;
        return capacity == that.capacity && Objects.equals(id, that.id);
    }
}
