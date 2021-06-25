package com.ss.utopia.model;

import javax.persistence.*;

@Embeddable
public class UserRole {

    @Column (name = "name", nullable = false)
    private String name;

    public UserRole(String name) {
        this.name = name;
    }

    public UserRole() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
