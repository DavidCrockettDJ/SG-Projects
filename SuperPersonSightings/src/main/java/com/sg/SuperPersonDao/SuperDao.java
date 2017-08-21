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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class SuperDao implements DaoInterface {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //SuperPeople
    private final String SQL_INSERT_SUPERPERSON
            = "insert into SuperPerson (SuperName, Description, Hero) "
            + "values (?, ?, ?)";

    private final String SQL_DELETE_SUPERPERSON
            = "delete from SuperPerson where SuperPersonID = ?";

    private final String SQL_UPDATE_SUPERPERSON
            = "update SuperPerson set SuperName = ?, Description = ?, Hero = ? where SuperPersonID = ? ";

    private final String SQL_SELECT_SUPERPERSON
            = "select * from SuperPerson where SuperPersonID = ?";

    private final String SQL_SELECT_ALL_SUPERPEOPLE
            = "select * from SuperPerson";

    //Organization
    private final String SQL_INSERT_ORGANIZATION
            = "insert into Organization ( OrganizationName, Email, Description, Address)"
            + " values (?, ?, ?, ?)";

    private final String SQL_DELETE_ORGANIZATION
            = "delete from Organization where OrganizationID = ?";

    private final String SQL_UPDATE_ORGANIZATION
            = "update Organization set OrganizationName = ?, Email = ?, Description = ?, Address = ? "
            + "where OrganizationID = ?";

    private final String SQL_SELECT_ORGANIZATION
            = "select * from Organization where OrganizationID = ?";

    private final String SQL_SELECT_ALL_ORGANIZATIONS
            = "select * from Organization";

    //SuperOrganization
    private final String SQL_INSERT_SUPERORGANIZATION
            = "insert into SuperOrganization ( SuperPersonID, OrganizationID)"
            + " values (?, ?)";

    private final String SQL_DELETE_SUPERORGANIZATION_FROM_ORGANIZATION
            = "delete from SuperOrganization where SuperOrganization.OrganizationID = ?";

    private final String SQL_DELETE_SUPERORGANIZATION_FROM_SUPERPERSON
            = "delete from SuperOrganization where SuperOrganization.SuperPersonID = ?";

    //Sighting
    private final String SQL_INSERT_SIGHTING
            = "insert into Sighting ( Date, LocationID)"
            + " values (?, ?)";

    private final String SQL_DELETE_SIGHTING
            = "delete from Sighting where SightingID = ?";

    private final String SQL_UPDATE_SIGHTING
            = "update Sighting set Date = ?, LocationID = ? "
            + "where SightingID = ?";

    private final String SQL_SELECT_SIGHTING
            = "select * from Sighting where SightingID = ?";

    private final String SQL_SELECT_ALL_SIGHTINGS
            = "select * from Sighting";

    private final String SQL_DELETE_SIGHTING_FROM_LOCATION
            = "delete from Sighting where Sighting.LocationID = ?";
    
    private final String SQL_SELECT_SIGHTING_FROM_LOCATION
            = "select Sighting.* "
            + "from Location "
            + "inner join Sighting on Location.LocationID = Sighting.LocationID "
            + "where Location.LocationID = ?";
    
    private final String SQL_SELECT_SIGHTING_FROM_DATE
            = "select Sighting.* "
            + "from Sighting "
            + "where Sighting.Date = ?";

    //SuperSighting
    private final String SQL_INSERT_SUPERSIGHTING
            = "insert into SuperSighting ( SuperPersonID, SightingID)"
            + " values (?, ?)";

    private final String SQL_DELETE_SUPERSIGHTING_FROM_SIGHTING
            = "delete from SuperSighting where SuperSighting.SightingID = ?";

    private final String SQL_DELETE_SUPERSIGHTING_FROM_SUPERPERSON
            = "delete from SuperSighting where SuperSighting.SuperPersonID = ?";

    //Location
    private final String SQL_INSERT_LOCATION
            = "insert into Location ( LocationName, Description, Date, Address, Latitude, Longitude)"
            + " values (?, ?, ?, ?, ?, ?)";

    private final String SQL_DELETE_LOCATION
            = "delete from Location where LocationID = ?";

    private final String SQL_UPDATE_LOCATION
            = "update Location set LocationName = ?, Description = ?, Date = ?, Address = ?, Latitude = ?, Longitude = ? "
            + "where LocationID = ?";

    private final String SQL_SELECT_LOCATION
            = "select * from Location where LocationID = ?";

    private final String SQL_SELECT_ALL_LOCATIONS
            = "select * from Location";

    //Power
    private final String SQL_INSERT_POWER
            = "insert into Power ( Description)"
            + " values (?)";

    private final String SQL_DELETE_POWER
            = "delete from Power where PowerID = ?";

    private final String SQL_UPDATE_POWER
            = "update Power set Description = ? "
            + "where PowerID = ?";

    private final String SQL_SELECT_POWER
            = "select * from Power where PowerID = ?";
    
    private final String SQL_SELECT_POWER_BY_NAME
            = "select Power.* "
            + "from Power where Power.Description = ?";
    
    private final String SQL_SELECT_POWER_FROM_SUPERPERSON
            = "select Power.*\n "
            + "from SuperPerson\n "
            + "inner join SuperPowers on SuperPerson.SuperPersonID = SuperPowers.SuperPersonID\n "
            + "inner join Power on SuperPowers.PowerID = Power.PowerID"
            + " where SuperPerson.SuperPersonID = ?";
    
    private final String SQL_SELECT_ALL_POWERS
            = "select * from Power";

    //SuperPowers
    private final String SQL_INSERT_SUPERPOWER
            = "insert into SuperPowers ( SuperPersonID, PowerID)"
            + " values (?, ?)";

    private final String SQL_DELETE_SUPERPOWER_FROM_POWER
            = "delete from SuperPowers where PowerID = ?";

    private final String SQL_DELETE_SUPERPOWER_FROM_SUPERPERSON
            = "delete from SuperPowers where SuperPersonID = ?";

    //SuperPeople and Organizations
    private final String SQL_SELECT_MEMBERS_OF_ORGANIZATION
            = "select SuperPerson.*\n "
            + "from Organization\n "
            + "inner join SuperOrganization on Organization.OrganizationID = SuperOrganization.OrganizationID\n "
            + "inner join SuperPerson on SuperOrganization.SuperPersonID = SuperPerson.SuperPersonID\n"
            + " where Organization.OrganizationID = ?";

    private final String SQL_SELECT_ORGANIZATIONS_FROM_SUPERPERSON
            = "select Organization.*\n "
            + "from SuperPerson\n "
            + "inner join SuperOrganization on SuperPerson.SuperPersonID = SuperOrganization.SuperPersonID\n "
            + "inner join Organization on SuperOrganization.OrganizationID = Organization.OrganizationID\n"
            + " where SuperPerson.SuperPersonID = ?";

    //SuperPeople and Locations
    private final String SQL_SELECT_SUPERPEOPLE_FROM_LOCATION
            = "select distinct SuperPerson.*\n "
            + "from Location\n "
            + "inner join Sighting on Location.LocationID = Sighting.LocationID\n "
            + "inner join SuperSighting on Sighting.SightingID = SuperSighting.SightingID\n "
            + "inner join SuperPerson on SuperSighting.SuperPersonID = SuperPerson.SuperPersonID\n"
            + " where Location.LocationID = ?";

    private final String SQL_SELECT_LOCATIONS_FROM_SUPERPERSON
            = "select distinct Location.*\n "
            + "from SuperPerson\n "
            + "inner join SuperSighting on SuperPerson.SuperPersonID = SuperSighting.SuperPersonID\n "
            + "inner join Sighting on SuperSighting.SightingID = Sighting.SightingID\n "
            + "inner join Location on Sighting.LocationID = Location.LocationID\n"
            + " where SuperPerson.SuperPersonID = ?";

    //SuperPeople, Locations and Sightings
    private final String SQL_SELECT_SUPERPEOPLE_AND_LOCATIONS_FROM_DATE
            = "select distinct SuperPerson.SuperName, Location.*, Sighting.Date, Sighting.SightingID\n "
            + "from Sighting\n "
            + "inner join Location on Sighting.LocationID = Location.LocationID\n "
            + "inner join SuperSighting on Sighting.SightingID = SuperSighting.SightingID\n "
            + "inner join SuperPerson on SuperSighting.SuperPersonID = SuperPerson.SuperPersonID\n"
            + " where Sighting.Date = ?";
    
    private final String SQL_SELECT_SUPERPEOPLE_FROM_SIGHTING
            = "select distinct SuperPerson.* "
            + "from Sighting "
            + "inner join SuperSighting on Sighting.Sightingid = SuperSighting.Sightingid "
            + "inner join SuperPerson on SuperSighting.SuperPersonid = SuperPerson.SuperPersonid "
            + "where Sighting.Sightingid = ?";
    
    private final String SQL_SELECT_LOCATION_FROM_SIGHTING
            = "select distinct Location.* "
            + "from Sighting "
            + "inner join Location on Sighting.LocationID = Location.LocationID "
            + "where Sighting.SightingID = ?";
    
    private final String SQL_SELECT_SIGHTINGS_FROM_LOCATION
            = "select distinct Sighting.* "
            + "from Location "
            + "inner join Sighting on Location.LocationID = Sighting.LocationID "
            + "where Location.LocationID = ?";

    //SuperPeople and Powers
    private final String SQL_SELECT_SUPERPEOPLE_FROM_POWERS
            = "select SuperPerson.*\n "
            + "from Power\n "
            + "inner join SuperPowers on Power.PowerID = SuperPowers.PowerID\n "
            + "inner join SuperPerson on SuperPowers.SuperPersonID = SuperPerson.SuperPersonID\n"
            + " where Power.PowerID = ?";

    private final String SQL_SELECT_POWERS_FROM_SUPERPERSON
            = "select Power.*\n "
            + "from SuperPerson\n "
            + "inner join SuperPowers on SuperPerson.SuperPersonID = SuperPowers.SuperPersonID\n "
            + "inner join Power on SuperPowers.PowerID = Power.PowerID\n"
            + " where SuperPerson.SuperPersonID = ?";

    //SuperPerson methods:
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSuperPerson(SuperPerson superPerson) {
        jdbcTemplate.update(SQL_INSERT_SUPERPERSON, superPerson.getName(), superPerson.getDescription(), superPerson.isHero());
        int superid = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        superPerson.setSuperPersonid(superid);
        List<Power> powers = superPerson.getSuperPower();
        for (Power currentPow : powers) {
            Power power = new Power();
            try {
                power = jdbcTemplate.queryForObject (SQL_SELECT_POWER_BY_NAME, new PowerMap(), currentPow.getDescription());
            } catch (EmptyResultDataAccessException ex) {
                power = null;
            }
            if (power == null) {
            addPower(currentPow);
            }
        }
        insertSuperPower(superPerson);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSuperPerson(int id) {
        jdbcTemplate.update(SQL_DELETE_SUPERSIGHTING_FROM_SUPERPERSON, id);
        jdbcTemplate.update(SQL_DELETE_SUPERPOWER_FROM_SUPERPERSON, id);
        jdbcTemplate.update(SQL_DELETE_SUPERORGANIZATION_FROM_SUPERPERSON, id);
        jdbcTemplate.update(SQL_DELETE_SUPERPERSON, id);
        List<Power> powList = getAllSuperPowersBySuperPersonid(id);
        for (Power pow : powList) {
            deletePower(pow.getPowerid());
        }
    }

    @Override
    public void updateSuperPerson(SuperPerson superPerson) {
        jdbcTemplate.update(SQL_UPDATE_SUPERPERSON, superPerson.getName(), superPerson.getDescription(), superPerson.isHero(), superPerson.getSuperPersonid());
        List<Power> powers = superPerson.getSuperPower();
        for (Power currentPow : powers) {
            Power power = new Power();
            try {
                power = jdbcTemplate.queryForObject (SQL_SELECT_POWER_BY_NAME, new PowerMap(), currentPow.getDescription());
            } catch (EmptyResultDataAccessException ex) {
                power = null;
            }
            if (power == null) {
            addPower(currentPow);
            }
        }
    }

    @Override
    public SuperPerson getSuperPersonByid(int id) {
        SuperPerson sp = new SuperPerson();
        try {
            List<Power> powers = jdbcTemplate.query(SQL_SELECT_POWER_FROM_SUPERPERSON, new PowerMap(), id);
            sp = jdbcTemplate.queryForObject(SQL_SELECT_SUPERPERSON, new SuperPersonMap(), id);
            sp.setSuperPower(powers);
            return sp;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<SuperPerson> getAllSuperPeople() {
        List<SuperPerson> spList = jdbcTemplate.query(SQL_SELECT_ALL_SUPERPEOPLE, new SuperPersonMap());
        for (SuperPerson currentSp : spList) {
            List<Power> powers = getAllSuperPowersBySuperPersonid(currentSp.getSuperPersonid());
            currentSp.setSuperPower(powers);
        }
        return spList;
    }

    @Override
    public List<Organization> getAllOrganizationsBySuperPersonid(int id) {
        List<Organization> orgList = jdbcTemplate.query(SQL_SELECT_ORGANIZATIONS_FROM_SUPERPERSON, new OrganizationMap(), id);
        for (Organization currentOrg : orgList) {
            List<SuperPerson> spList = getAllMembers(currentOrg.getOrganizationid());
            currentOrg.setMembers(spList);
        }
        return orgList;
    }

    @Override
    public List<Power> getAllSuperPowersBySuperPersonid(int id) {
        return jdbcTemplate.query(SQL_SELECT_POWERS_FROM_SUPERPERSON, new PowerMap(), id);
    }

    //Organization methods:
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrganization(Organization organization) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION, organization.getName(), organization.getEmail(), organization.getDescription(), organization.getAddress());
        int organizationid = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        organization.setOrganizationid(organizationid);

        insertSuperOrganization(organization);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteOrganization(int id) {
        jdbcTemplate.update(SQL_DELETE_SUPERORGANIZATION_FROM_ORGANIZATION, id);
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, id);
    }

    @Override
    public void updateOrganization(Organization organization) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION, organization.getName(), organization.getEmail(), organization.getDescription(), organization.getOrganizationid());
    }

    @Override
    public Organization getOrganizationByid(int id) {
        try {
        List<SuperPerson> sp = getAllMembers(id);
        Organization org = jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION, new OrganizationMap(), id);
        org.setMembers(sp);
        return org;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        List<Organization> orgList =  jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS, new OrganizationMap());
        for (Organization currentOrg : orgList) {
            List<SuperPerson> spList = getAllMembers(currentOrg.getOrganizationid());
            currentOrg.setMembers(spList);
        }
        return orgList;
    }

    @Override
    public List<SuperPerson> getAllMembers(int id) {
        List<SuperPerson> spList = jdbcTemplate.query(SQL_SELECT_MEMBERS_OF_ORGANIZATION, new SuperPersonMap(), id);
        for (SuperPerson currentSp : spList) {
            List<Power> power = getAllSuperPowersBySuperPersonid(currentSp.getSuperPersonid());
            currentSp.setSuperPower(power);
        }
        return spList;
    }

    //Sighting methods:
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING, sighting.getDate().toString(), sighting.getLocation().getLocationid());
        int sightingid = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        sighting.setSightingid(sightingid);
        
        insertSuperSighting(sighting);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSighting(int id) {
        jdbcTemplate.update(SQL_DELETE_SUPERSIGHTING_FROM_SIGHTING, id);
        jdbcTemplate.update(SQL_DELETE_SIGHTING, id);
    }

    @Override
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING, sighting.getDate().toString(), sighting.getLocation().getLocationid(), sighting.getSightingid());
        jdbcTemplate.update(SQL_DELETE_SUPERSIGHTING_FROM_SIGHTING, sighting.getSightingid());
        
        insertSuperSighting(sighting);
    }

    @Override
    public Sighting getSightingByid(int id) {
        try {
        List<SuperPerson> spList = jdbcTemplate.query(SQL_SELECT_SUPERPEOPLE_FROM_SIGHTING, new SuperPersonMap(), id);
        for (SuperPerson currentSp : spList) {
            List<Power> powers = getAllSuperPowersBySuperPersonid(currentSp.getSuperPersonid());
            currentSp.setSuperPower(powers);
        }   
        Location loc = jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_FROM_SIGHTING, new LocationMap(), id);
        Sighting sight = jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING, new SightingMap(), id);
        sight.setLocation(loc);
        sight.setSuperPeople(spList);
        return sight;
        } catch (EmptyResultDataAccessException ex) {
                return null;
                }
    }

    @Override
    public List<Sighting> getAllSightings() {
        List<Sighting> sightList = jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS, new SightingMap());
        for (Sighting currentSight : sightList) {
            List<SuperPerson> spList = jdbcTemplate.query(SQL_SELECT_SUPERPEOPLE_FROM_SIGHTING, new SuperPersonMap(), currentSight.getSightingid());
            for (SuperPerson currentSp : spList) {
                List<Power> powerList = jdbcTemplate.query(SQL_SELECT_POWERS_FROM_SUPERPERSON, new PowerMap(), currentSp.getSuperPersonid());
                currentSp.setSuperPower(powerList);
            }
            currentSight.setSuperPeople(spList);
            Location loc = jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_FROM_SIGHTING, new LocationMap(), currentSight.getSightingid());
            currentSight.setLocation(loc);
        }
        
        return sightList;
    }
            

    @Override
    public List<Sighting> getAllSightingsByDate(LocalDate date) {
        List<Sighting> sightList = jdbcTemplate.query(SQL_SELECT_SIGHTING_FROM_DATE, new SightingMap(), date.toString());
        for (Sighting currentSight : sightList) {
            List<SuperPerson> spList = jdbcTemplate.query(SQL_SELECT_SUPERPEOPLE_FROM_SIGHTING, new SuperPersonMap(), currentSight.getSightingid());
            for (SuperPerson currentSp : spList) {
                List<Power> powerList = jdbcTemplate.query(SQL_SELECT_POWERS_FROM_SUPERPERSON, new PowerMap(), currentSp.getSuperPersonid());
                currentSp.setSuperPower(powerList);
            }
            currentSight.setSuperPeople(spList);
            Location loc = jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_FROM_SIGHTING, new LocationMap(), currentSight.getSightingid());
            currentSight.setLocation(loc);
        }
        
        return sightList;
    }
    

    //Location methods:
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION, location.getName(), location.getDescription(), location.getDate().toString(), location.getAddress(), location.getLatitude(), location.getLongitude());
        int locationid = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        location.setLocationid(locationid);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteLocation(int id) {
        List<Sighting> sightList = jdbcTemplate.query(SQL_SELECT_SIGHTINGS_FROM_LOCATION, new SightingMap(), id);
        for (Sighting currentSight : sightList) {
            jdbcTemplate.update(SQL_DELETE_SUPERSIGHTING_FROM_SIGHTING, currentSight.getSightingid());
            deleteSighting(currentSight.getSightingid());
        }
        jdbcTemplate.update(SQL_DELETE_LOCATION, id);
    }

    @Override
    public void updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION, location.getName(), location.getDescription(), location.getDate().toString(), location.getAddress(), location.getLatitude(), location.getLongitude(), location.getLocationid());
    }
    
    @Override
    public Location getLocationByid(int id) {
        try {
        return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION, new LocationMap(), id);
        }catch (EmptyResultDataAccessException ex) {
                return null;
                }
    }
    
    @Override
    public List<Location> getAllLocations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS, new LocationMap());
    }

    @Override
    public List<Location> getAllSightedLocationsBySuperPersonid(int id) {
        return jdbcTemplate.query(SQL_SELECT_LOCATIONS_FROM_SUPERPERSON, new LocationMap(), id);
    }

    @Override
    public List<SuperPerson> getAllSuperPeopleBySightedLocationid(int id) {
        List<SuperPerson> spList = jdbcTemplate.query(SQL_SELECT_SUPERPEOPLE_FROM_LOCATION, new SuperPersonMap(), id);
        for (SuperPerson currentSp : spList) {
            List<Power> power = getAllSuperPowersBySuperPersonid(id);
            currentSp.setSuperPower(power);
        }
        return spList;
    }

    //Power methods:
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPower(Power power) {
        jdbcTemplate.update(SQL_INSERT_POWER, power.getDescription());
        int powerid = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        power.setPowerid(powerid);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deletePower(int id) {
        jdbcTemplate.update(SQL_DELETE_SUPERPOWER_FROM_POWER, id);
        jdbcTemplate.update(SQL_DELETE_POWER, id);
    }

    @Override
    public void updatePower(Power power) {
        jdbcTemplate.update(SQL_UPDATE_POWER, power.getDescription(), power.getPowerid());
    }

    @Override
    public Power getPowerByid(int id) {
        try {
        return jdbcTemplate.queryForObject(SQL_SELECT_POWER, new PowerMap(), id);
        } catch (EmptyResultDataAccessException ex) {
                return null;
                }
    }
    
    @Override
    public List<Power> getAllPowers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_POWERS, new PowerMap());
    }

    @Override
    public List<SuperPerson> getAllSuperPeopleByPowerid(int id) {
        return jdbcTemplate.query(SQL_SELECT_SUPERPEOPLE_FROM_POWERS, new SuperPersonMap(), id);
    }

    //Helper methods:
    private void insertSuperOrganization(Organization organization) {
        final int organizationid = organization.getOrganizationid();
        final List<SuperPerson> members = organization.getMembers();
        
        if (members != null) {
        for (SuperPerson currentMember : members) {
            jdbcTemplate.update(SQL_INSERT_SUPERORGANIZATION,
                    currentMember.getSuperPersonid(),
                    organizationid);
        }
        }
    }
    
    private void insertSuperPower(SuperPerson sp) {
        final int spid = sp.getSuperPersonid();
        final List<Power> powers = sp.getSuperPower();
        
        if (powers != null) {
        for (Power currentPower : powers) {
            jdbcTemplate.update(SQL_INSERT_SUPERPOWER,
                    spid,
                    currentPower.getPowerid());
        }
        }
    }
    
    private void insertSuperSighting(Sighting sight) {
        final int sightid = sight.getSightingid();
        final List<SuperPerson> supers = sight.getSuperPeople();
        
        if (supers != null) {
        for (SuperPerson currentSuper : supers) {
            jdbcTemplate.update(SQL_INSERT_SUPERSIGHTING,
                    currentSuper.getSuperPersonid(),
                    sightid);
        }
        }
    }

    //Mappers
    private static final class SuperPersonMap implements RowMapper<SuperPerson> {

        @Override
        public SuperPerson mapRow(ResultSet rs, int i) throws SQLException {
            SuperPerson sp = new SuperPerson();
            sp.setSuperPersonid(rs.getInt("SuperPersonID"));
            sp.setName(rs.getString("SuperName"));
            sp.setDescription(rs.getString("Description"));
            sp.setHero(rs.getBoolean("Hero"));
            return sp;
        }
    }

    private static final class OrganizationMap implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization or = new Organization();
            or.setOrganizationid(rs.getInt("OrganizationID"));
            or.setName(rs.getString("OrganizationName"));
            or.setEmail(rs.getString("Email"));
            or.setDescription(rs.getString("Description"));
            or.setAddress(rs.getString("Address"));
            return or;
        }
    }

    private static final class SightingMap implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting si = new Sighting();
            si.setSightingid(rs.getInt("SightingID"));
            si.setDate(rs.getTimestamp("Date").toLocalDateTime().toLocalDate());
            return si;
        }
    }

    private static final class PowerMap implements RowMapper<Power> {

        @Override
        public Power mapRow(ResultSet rs, int i) throws SQLException {
            Power po = new Power();
            po.setPowerid(rs.getInt("PowerID"));
            po.setDescription(rs.getString("Description"));
            return po;
        }
    }

    private static final class LocationMap implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location lo = new Location();
            lo.setLocationid(rs.getInt("LocationID"));
            lo.setName(rs.getString("LocationName"));
            lo.setDescription(rs.getString("Description"));
            lo.setDate(rs.getTimestamp("Date").toLocalDateTime().toLocalDate());
            lo.setAddress(rs.getString("Address"));
            lo.setLatitude(rs.getDouble("Latitude"));
            lo.setLongitude(rs.getDouble("Longitude"));
            return lo;
        }
    }

}

