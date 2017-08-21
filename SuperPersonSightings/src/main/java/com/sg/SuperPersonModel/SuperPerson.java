/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperPersonModel;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class SuperPerson {
    
    private int SuperPersonid;
    private String name;
    private String description;
    private boolean hero;
    private List<Power> superPower;

    public int getSuperPersonid() {
        return SuperPersonid;
    }

    public void setSuperPersonid(int SuperPersonid) {
        this.SuperPersonid = SuperPersonid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHero() {
        return hero;
    }

    public void setHero(boolean hero) {
        this.hero = hero;
    }

    public List<Power> getSuperPower() {
        return superPower;
    }

    public void setSuperPower(List<Power> superPower) {
        this.superPower = superPower;
    }
    
    public String getFormattedPower() {
        return this.superPower.stream().map(Object::toString).collect(Collectors.joining(", "));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.SuperPersonid;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.description);
        hash = 89 * hash + (this.hero ? 1 : 0);
        hash = 89 * hash + Objects.hashCode(this.superPower);
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
        final SuperPerson other = (SuperPerson) obj;
        if (this.SuperPersonid != other.SuperPersonid) {
            return false;
        }
        if (this.hero != other.hero) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.superPower, other.superPower)) {
            return false;
        }
        return true;
    }
    
    

    
}
