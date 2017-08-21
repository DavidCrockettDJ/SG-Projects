/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperPersonModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class Sighting {
    
    private int sightingid;
    private LocalDate date;
    private Location location;
    private List<SuperPerson> superPeople;

    public int getSightingid() {
        return sightingid;
    }

    public void setSightingid(int sightingid) {
        this.sightingid = sightingid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<SuperPerson> getSuperPeople() {
        return superPeople;
    }

    public void setSuperPeople(List<SuperPerson> superPeople) {
        this.superPeople = superPeople;
    }
    
    public String getFormattedSuperPerson() {
        return this.superPeople.stream().map(Object::toString).collect(Collectors.joining(", "));
    }
    
    public String getFormattedLocation() {
        return this.location.getName();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.sightingid;
        hash = 67 * hash + Objects.hashCode(this.date);
        hash = 67 * hash + Objects.hashCode(this.location);
        hash = 67 * hash + Objects.hashCode(this.superPeople);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sighting other = (Sighting) obj;
        if (this.sightingid != other.sightingid) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.superPeople, other.superPeople)) {
            return false;
        }
        return true;
    }
    
    
}
