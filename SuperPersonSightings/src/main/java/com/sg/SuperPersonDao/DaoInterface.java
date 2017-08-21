/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperPersonDao;

import com.sg.SuperPersonModel.Location;
import com.sg.SuperPersonModel.Organization;
import com.sg.SuperPersonModel.Sighting;
import com.sg.SuperPersonModel.SuperPerson;
import com.sg.SuperPersonModel.Power;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface DaoInterface {
    
    //SuperPerson methods:
    public void addSuperPerson(SuperPerson superPerson);
    
    public void deleteSuperPerson(int id);
    
    public void updateSuperPerson(SuperPerson superperson);
    
    public SuperPerson getSuperPersonByid(int id);
    
    public List<SuperPerson> getAllSuperPeople();
    
    public List<Organization> getAllOrganizationsBySuperPersonid(int id);
    
    public List<Power> getAllSuperPowersBySuperPersonid(int id);
    
    //Organization methods:
    public void addOrganization(Organization organization);
    
    public void deleteOrganization(int id);
    
    public void updateOrganization(Organization organization);
    
    public Organization getOrganizationByid(int id);
    
    public List<Organization> getAllOrganizations();
    
    public List<SuperPerson> getAllMembers(int id);
    
    //Sighting methods:
    public void addSighting(Sighting sighting);
    
    public void deleteSighting(int id);
    
    public void updateSighting(Sighting sighting);
    
    public Sighting getSightingByid(int id);
    
    public List<Sighting> getAllSightings();
    
    public List<Sighting> getAllSightingsByDate(LocalDate date);
    
    //Location methods:
    public void addLocation(Location location);
    
    public void deleteLocation(int id);
    
    public void updateLocation(Location location);
    
    public Location getLocationByid(int id);
    
    public List<Location> getAllLocations();
    
    public List<Location> getAllSightedLocationsBySuperPersonid(int id);
    
    public List<SuperPerson> getAllSuperPeopleBySightedLocationid(int id);
    
    //SuperPower methods:
    public void addPower(Power Power);
    
    public void deletePower(int id);
    
    public void updatePower(Power Power);
    
    public Power getPowerByid(int id);
    
    public List<Power> getAllPowers();
    
    public List<SuperPerson> getAllSuperPeopleByPowerid(int id);
}
