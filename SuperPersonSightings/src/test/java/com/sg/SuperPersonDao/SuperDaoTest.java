/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperPersonDao;

import com.sg.SuperPersonModel.Location;
import com.sg.SuperPersonModel.Organization;
import com.sg.SuperPersonModel.Power;
import com.sg.SuperPersonModel.Sighting;
import com.sg.SuperPersonModel.SuperPerson;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class SuperDaoTest {
    
    DaoInterface dao;
    
    public SuperDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx
      = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        dao = ctx.getBean("SuperDao", DaoInterface.class);
        
        // delete all Organizations
        List<Organization> orgs = dao.getAllOrganizations();
        for (Organization currentorg : orgs) {
            dao.deleteOrganization(currentorg.getOrganizationid());
        }
        
        // delete all SuperPeople
        List<SuperPerson> supers = dao.getAllSuperPeople();
        for (SuperPerson currentsp : supers) {
            dao.deleteSuperPerson(currentsp.getSuperPersonid());
        }
        
        // delete all Sightings
        List<Sighting> sights = dao.getAllSightings();
        for (Sighting currentsight : sights) {
            dao.deleteSighting(currentsight.getSightingid());
        }
        
        // delete all Locations
        List<Location> locations = dao.getAllLocations();
        for (Location currentLo : locations) {
            dao.deleteLocation(currentLo.getLocationid());
        }
        
        // delete all Powers
        List<Power> powers = dao.getAllPowers();
        for (Power currentPower : powers) {
            dao.deletePower(currentPower.getPowerid());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addSuperPerson method, of class SuperDao.
     */
    @Test
    public void testAddGetSuperPerson() {
        List<Power> powers = new ArrayList<>();
        Power pow = new Power();
        pow.setDescription("super intelligence");
        dao.addPower(pow);
        powers.add(pow);
        SuperPerson sp = new SuperPerson();
        sp.setName("IronMan");
        sp.setDescription("Frikkin awesome yo");
        sp.setHero(true);
        sp.setSuperPower(powers);
        
        dao.addSuperPerson(sp);
        
        SuperPerson hero = dao.getSuperPersonByid(sp.getSuperPersonid());
        assertEquals(hero, sp);
        
        
    }

    /**
     * Test of deleteSuperPerson method, of class SuperDao.
     */
    @Test
    public void testDeleteSuperPerson() {
        List<Power> powers = new ArrayList<>();
        Power pow = new Power();
        pow.setDescription("super intelligence");
        dao.addPower(pow);
        powers.add(pow);
        SuperPerson sp = new SuperPerson();
        sp.setName("IronMan");
        sp.setDescription("Frikkin awesome yo");
        sp.setHero(true);
        sp.setSuperPower(powers);
        
        dao.addSuperPerson(sp);
        
        SuperPerson hero = dao.getSuperPersonByid(sp.getSuperPersonid());
        assertEquals(hero, sp);
        dao.deleteSuperPerson(hero.getSuperPersonid());
        assertNull(dao.getSuperPersonByid(hero.getSuperPersonid()));
    }

    /**
     * Test of updateSuperPerson method, of class SuperDao.
     */
    @Test
    public void testUpdateSuperPerson() {
        List<Power> powers = new ArrayList<>();
        Power pow = new Power();
        pow.setDescription("super intelligence");
        dao.addPower(pow);
        powers.add(pow);
        SuperPerson sp = new SuperPerson();
        sp.setName("IronMan");
        sp.setDescription("Frikkin awesome yo");
        sp.setHero(true);
        sp.setSuperPower(powers);
        
        dao.addSuperPerson(sp);
        
        SuperPerson hero = dao.getSuperPersonByid(sp.getSuperPersonid());
        assertEquals(hero, sp);
    }

    /**
     * Test of getAllSuperPeople method, of class SuperDao.
     */
    @Test
    public void testGetAllSuperPeople() {
        List<Power> powers = new ArrayList<>();
        Power pow = new Power();
        pow.setDescription("super intelligence");
        dao.addPower(pow);
        powers.add(pow);
        SuperPerson sp = new SuperPerson();
        sp.setName("IronMan");
        sp.setDescription("Frikkin awesome yo");
        sp.setHero(true);
        sp.setSuperPower(powers);
        SuperPerson sp2 = new SuperPerson();
        sp2.setName("george");
        sp2.setDescription("hey");
        sp2.setHero(false);
        sp2.setSuperPower(powers);
        
        dao.addSuperPerson(sp);
        dao.addSuperPerson(sp2);
        
        List<SuperPerson> spList = new ArrayList<>();
        spList = dao.getAllSuperPeople();
        assertEquals(2, spList.size());
    }

    /**
     * Test of getAllOrganizationsBySuperPersonid method, of class SuperDao.
     */
    @Test
    public void testGetAllOrganizationsBySuperPersonid() {
        List<Power> powers = new ArrayList<>();
        Power pow = new Power();
        pow.setDescription("super intelligence");
        dao.addPower(pow);
        powers.add(pow);
        SuperPerson sp = new SuperPerson();
        sp.setName("IronMan");
        sp.setDescription("Frikkin awesome yo");
        sp.setHero(true);
        sp.setSuperPower(powers);
        
        dao.addSuperPerson(sp);
        
        SuperPerson hero = dao.getSuperPersonByid(sp.getSuperPersonid());
        assertEquals(hero, sp);
        
        List<SuperPerson> spList = new ArrayList<>();
        spList.add(sp);
        Organization org = new Organization();
        org.setName("Avengers");
        org.setEmail("blah@Gmail.com");
        org.setDescription("Superhero league");
        org.setAddress("444 someStreet, CA, 95677");
        org.setMembers(spList);
        
        Organization org2 = new Organization();
        org2.setName("Avengers2");
        org2.setEmail("blah");
        org2.setDescription("hey");
        org2.setAddress("somewhere");
        org2.setMembers(spList);
        
        dao.addOrganization(org);
        dao.addOrganization(org2);
        Organization avengers = dao.getOrganizationByid(org.getOrganizationid());
        assertEquals(avengers, org);
        Organization ave = dao.getOrganizationByid(org2.getOrganizationid());
        assertEquals (ave, org2);
        List<Organization> orgList = dao.getAllOrganizationsBySuperPersonid(sp.getSuperPersonid());
        assertEquals(2, orgList.size());
    }

    /**
     * Test of getAllSuperPowersBySuperPersonid method, of class SuperDao.
     */
    @Test
    public void testGetAllSuperPowersBySuperPersonid() {
        List<Power> powers = new ArrayList<>();
        Power pow = new Power();
        pow.setDescription("super intelligence");
        dao.addPower(pow);
        Power power = dao.getPowerByid(pow.getPowerid());
        assertEquals(power, pow);
        Power pow2 = new Power();
        pow2.setDescription("hey");
        dao.addPower(pow2);
        Power power2 = dao.getPowerByid(pow2.getPowerid());
        assertEquals(power2, pow2);
        powers.add(pow);
        powers.add(pow2);
        SuperPerson sp = new SuperPerson();
        sp.setName("IronMan");
        sp.setDescription("Frikkin awesome yo");
        sp.setHero(true);
        sp.setSuperPower(powers);
        
        dao.addSuperPerson(sp);
        
        SuperPerson hero = dao.getSuperPersonByid(sp.getSuperPersonid());
        assertEquals(hero, sp);
        List<Power> spPowers = dao.getAllSuperPowersBySuperPersonid(sp.getSuperPersonid());
        assertEquals(2, spPowers.size());
    }

    /**
     * Test of deleteOrganization method, of class SuperDao.
     */
    @Test
    public void testDeleteOrganization() {
        List<Power> powers = new ArrayList<>();
        Power pow = new Power();
        pow.setDescription("super intelligence");
        dao.addPower(pow);
        powers.add(pow);
        SuperPerson sp = new SuperPerson();
        sp.setName("IronMan");
        sp.setDescription("Frikkin awesome yo");
        sp.setHero(true);
        sp.setSuperPower(powers);
        
        dao.addSuperPerson(sp);
        
        SuperPerson hero = dao.getSuperPersonByid(sp.getSuperPersonid());
        assertEquals(hero, sp);
        
        List<SuperPerson> spList = new ArrayList<>();
        spList.add(sp);
        Organization org = new Organization();
        org.setName("Avengers");
        org.setEmail("blah@Gmail.com");
        org.setDescription("Superhero league");
        org.setAddress("444 someStreet, CA, 95677");
        org.setMembers(spList);
        
        Organization org2 = new Organization();
        org2.setName("Avengers2");
        org2.setEmail("blah");
        org2.setDescription("hey");
        org2.setAddress("somewhere");
        org2.setMembers(spList);
        
        dao.addOrganization(org);
        dao.addOrganization(org2);
        Organization avengers = dao.getOrganizationByid(org.getOrganizationid());
        assertEquals(avengers, org);
        Organization ave = dao.getOrganizationByid(org2.getOrganizationid());
        assertEquals (ave, org2);
        List<Organization> orgList = dao.getAllOrganizations();
        assertEquals(2, orgList.size());
        dao.deleteOrganization(org2.getOrganizationid());
        assertNull(dao.getOrganizationByid(org2.getOrganizationid()));
        orgList = dao.getAllOrganizations();
        assertEquals(1, orgList.size());
    }

    /**
     * Test of updateOrganization method, of class SuperDao.
     */
    @Test
    public void testUpdateOrganization() {
        
    }
    
    @Test
    public void testAddSighting() {
      LocalDate date = LocalDate.parse("2017-02-23", DateTimeFormatter.ISO_DATE);
      Location loc = new Location();
      loc.setName("hi");
      loc.setDescription("Aussie");
      loc.setDate(date);
      loc.setAddress("lameaf");
      loc.setLatitude(34.56);
      loc.setLongitude(43.67);
      
      dao.addLocation(loc);
      List<Power> powers = new ArrayList<>();
        Power pow = new Power();
        pow.setDescription("super intelligence");
        dao.addPower(pow);
        powers.add(pow);
        SuperPerson sp = new SuperPerson();
        sp.setName("IronMan");
        sp.setDescription("Frikkin awesome yo");
        sp.setHero(true);
        sp.setSuperPower(powers);
        
        dao.addSuperPerson(sp);
        
        SuperPerson hero = dao.getSuperPersonByid(sp.getSuperPersonid());
        assertEquals(hero, sp);
        
        List<SuperPerson> spList = new ArrayList<>();
        spList.add(sp);
      Sighting sight = new Sighting();
      sight.setDate(date);
      sight.setLocation(loc);
      sight.setSuperPeople(spList);
      
      dao.addSighting(sight);
      Sighting sight2 = dao.getSightingByid(sight.getSightingid());
      assertEquals(sight2, sight);
      dao.deleteSighting(sight.getSightingid());
      assertNull(dao.getSightingByid(sight.getSightingid()));
      
    }

    /**
     * Test of updateSighting method, of class SuperDao.
     */
    @Test
    public void testUpdateSighting() {
        LocalDate date2 = LocalDate.parse("2013-04-22", DateTimeFormatter.ISO_DATE);
       LocalDate date = LocalDate.parse("2017-02-23", DateTimeFormatter.ISO_DATE);
      Location loc = new Location();
      loc.setName("hi");
      loc.setDescription("Aussie");
      loc.setDate(date);
      loc.setAddress("lameaf");
      loc.setLatitude(34.56);
      loc.setLongitude(43.67);
      
      dao.addLocation(loc);
      List<Power> powers = new ArrayList<>();
        Power pow = new Power();
        pow.setDescription("super intelligence");
        dao.addPower(pow);
        powers.add(pow);
        SuperPerson sp = new SuperPerson();
        sp.setName("IronMan");
        sp.setDescription("Frikkin awesome yo");
        sp.setHero(true);
        sp.setSuperPower(powers);
        
        dao.addSuperPerson(sp);
        
        SuperPerson hero = dao.getSuperPersonByid(sp.getSuperPersonid());
        assertEquals(hero, sp);
        
        List<SuperPerson> spList = new ArrayList<>();
        spList.add(sp);
      Sighting sight = new Sighting();
      sight.setDate(date);
      sight.setLocation(loc);
      sight.setSuperPeople(spList);
      
      Sighting sight3 = new Sighting();
      sight3.setDate(date2);
      sight3.setLocation(loc);
      sight3.setSuperPeople(spList);
      
      dao.addSighting(sight);
      sight3.setSightingid(sight.getSightingid());
      dao.updateSighting(sight3);
      Sighting sightTest = dao.getSightingByid(sight.getSightingid());
      assertEquals(sightTest, sight3);
    }

    /**
     * Test of getAllSightings method, of class SuperDao.
     */
    @Test
    public void testGetAllSightings() {
       LocalDate date2 = LocalDate.parse("2013-04-22", DateTimeFormatter.ISO_DATE);
       LocalDate date = LocalDate.parse("2017-02-23", DateTimeFormatter.ISO_DATE);
      Location loc = new Location();
      loc.setName("hi");
      loc.setDescription("Aussie");
      loc.setDate(date);
      loc.setAddress("lameaf");
      loc.setLatitude(34.56);
      loc.setLongitude(43.67);
      
      dao.addLocation(loc);
      List<Power> powers = new ArrayList<>();
        Power pow = new Power();
        pow.setDescription("super intelligence");
        dao.addPower(pow);
        powers.add(pow);
        SuperPerson sp = new SuperPerson();
        sp.setName("IronMan");
        sp.setDescription("Frikkin awesome yo");
        sp.setHero(true);
        sp.setSuperPower(powers);
        
        dao.addSuperPerson(sp);
        
        SuperPerson hero = dao.getSuperPersonByid(sp.getSuperPersonid());
        assertEquals(hero, sp);
        
        List<SuperPerson> spList = new ArrayList<>();
        spList.add(sp);
      Sighting sight = new Sighting();
      sight.setDate(date);
      sight.setLocation(loc);
      sight.setSuperPeople(spList);
      
      Sighting sight3 = new Sighting();
      sight3.setDate(date2);
      sight3.setLocation(loc);
      sight3.setSuperPeople(spList);
      
      dao.addSighting(sight);
      dao.addSighting(sight3);
      Sighting sight2 = dao.getSightingByid(sight.getSightingid());
      assertEquals(sight2, sight);
      List<Sighting> sightList = dao.getAllSightings();
      assertEquals(2, sightList.size());
      
    }

    /**
     * Test of getAllSightingsByDate method, of class SuperDao.
     */
    @Test
    public void testGetAllSightingsByDate() {
        LocalDate date2 = LocalDate.parse("2013-04-22", DateTimeFormatter.ISO_DATE);
       LocalDate date = LocalDate.parse("2017-02-23", DateTimeFormatter.ISO_DATE);
      Location loc = new Location();
      loc.setName("hi");
      loc.setDescription("Aussie");
      loc.setDate(date);
      loc.setAddress("lameaf");
      loc.setLatitude(34.56);
      loc.setLongitude(43.67);
      
      dao.addLocation(loc);
      List<Power> powers = new ArrayList<>();
        Power pow = new Power();
        pow.setDescription("super intelligence");
        dao.addPower(pow);
        powers.add(pow);
        SuperPerson sp = new SuperPerson();
        sp.setName("IronMan");
        sp.setDescription("Frikkin awesome yo");
        sp.setHero(true);
        sp.setSuperPower(powers);
        
        dao.addSuperPerson(sp);
        
        SuperPerson hero = dao.getSuperPersonByid(sp.getSuperPersonid());
        assertEquals(hero, sp);
        
        List<SuperPerson> spList = new ArrayList<>();
        spList.add(sp);
      Sighting sight = new Sighting();
      sight.setDate(date);
      sight.setLocation(loc);
      sight.setSuperPeople(spList);
      
      Sighting sight3 = new Sighting();
      sight3.setDate(date);
      sight3.setLocation(loc);
      sight3.setSuperPeople(spList);
      
      dao.addSighting(sight);
      List<Sighting> sightList = dao.getAllSightingsByDate(date);
      assertEquals(1, sightList.size());
    }

    /**
     * Test of addLocation method, of class SuperDao.
     */
    @Test
    public void testAddLocation() {
       LocalDate date = LocalDate.parse("2017-02-23", DateTimeFormatter.ISO_DATE);
      Location loc = new Location();
      loc.setName("hi");
      loc.setDescription("Aussie");
      loc.setDate(date);
      loc.setAddress("lameaf");
      loc.setLatitude(34.56);
      loc.setLongitude(43.67);
      
      dao.addLocation(loc);
    }

    /**
     * Test of deleteLocation method, of class SuperDao.
     */
    @Test
    public void testDeleteLocation() {
        LocalDate date = LocalDate.parse("2017-02-23", DateTimeFormatter.ISO_DATE);
      Location loc = new Location();
      loc.setName("hi");
      loc.setDescription("Aussie");
      loc.setDate(date);
      loc.setAddress("lameaf");
      loc.setLatitude(34.56);
      loc.setLongitude(43.67);
      
      dao.addLocation(loc);
      List<Power> powers = new ArrayList<>();
        Power pow = new Power();
        pow.setDescription("super intelligence");
        dao.addPower(pow);
        powers.add(pow);
        SuperPerson sp = new SuperPerson();
        sp.setName("IronMan");
        sp.setDescription("Frikkin awesome yo");
        sp.setHero(true);
        sp.setSuperPower(powers);
        
        dao.addSuperPerson(sp);
        
        SuperPerson hero = dao.getSuperPersonByid(sp.getSuperPersonid());
        assertEquals(hero, sp);
        
        List<SuperPerson> spList = new ArrayList<>();
        spList.add(sp);
      Sighting sight = new Sighting();
      sight.setDate(date);
      sight.setLocation(loc);
      sight.setSuperPeople(spList);
      
      dao.addSighting(sight);
      Sighting sight2 = dao.getSightingByid(sight.getSightingid());
      assertEquals(sight2, sight);
      dao.deleteLocation(loc.getLocationid());
      assertNull(dao.getSightingByid(sight.getSightingid()));
    }

    /**
     * Test of updateLocation method, of class SuperDao.
     */
    @Test
    public void testUpdateLocation() {
        LocalDate date = LocalDate.parse("2017-02-23", DateTimeFormatter.ISO_DATE);
      Location loc = new Location();
      loc.setName("hi");
      loc.setDescription("Aussie");
      loc.setDate(date);
      loc.setAddress("lameaf");
      loc.setLatitude(34.56);
      loc.setLongitude(43.67);
      
      Location loc2 = new Location();
      loc2.setName("hi");
      loc2.setDescription("Aussie2");
      loc2.setDate(date);
      loc2.setAddress("lameaf");
      loc2.setLatitude(34.56);
      loc2.setLongitude(43.67);
      
      dao.addLocation(loc);
      loc2.setLocationid(loc.getLocationid());
      dao.updateLocation(loc2);
      Location locTest = dao.getLocationByid(loc.getLocationid());
      assertEquals(locTest, loc2);
      
    }

    /**
     * Test of getAllLocations method, of class SuperDao.
     */
    @Test
    public void testGetAllLocations() {
       LocalDate date = LocalDate.parse("2017-02-23", DateTimeFormatter.ISO_DATE);
      Location loc = new Location();
      loc.setName("hi");
      loc.setDescription("Aussie");
      loc.setDate(date);
      loc.setAddress("lameaf");
      loc.setLatitude(34.56);
      loc.setLongitude(43.67);
      
      dao.addLocation(loc);
      List<Location> locList = dao.getAllLocations();
      assertEquals(1, locList.size());
    }

    /**
     * Test of getAllSightedLocationsBySuperPersonid method, of class SuperDao.
     */
    @Test
    public void testGetAllSightedLocationsBySuperPersonid() {
        LocalDate date = LocalDate.parse("2017-02-23", DateTimeFormatter.ISO_DATE);
      Location loc = new Location();
      loc.setName("hi");
      loc.setDescription("Aussie");
      loc.setDate(date);
      loc.setAddress("lameaf");
      loc.setLatitude(34.56);
      loc.setLongitude(43.67);
      
      Location loc2 = new Location();
      loc2.setName("hi");
      loc2.setDescription("Aussie2");
      loc2.setDate(date);
      loc2.setAddress("lameaf");
      loc2.setLatitude(34.56);
      loc2.setLongitude(43.67);
      
      dao.addLocation(loc);
      dao.addLocation(loc2);
      List<Power> powers = new ArrayList<>();
        Power pow = new Power();
        pow.setDescription("super intelligence");
        dao.addPower(pow);
        powers.add(pow);
        SuperPerson sp = new SuperPerson();
        sp.setName("IronMan");
        sp.setDescription("Frikkin awesome yo");
        sp.setHero(true);
        sp.setSuperPower(powers);
        
        dao.addSuperPerson(sp);
        
        SuperPerson hero = dao.getSuperPersonByid(sp.getSuperPersonid());
        assertEquals(hero, sp);
        
        List<SuperPerson> spList = new ArrayList<>();
        spList.add(sp);
      Sighting sight = new Sighting();
      sight.setDate(date);
      sight.setLocation(loc);
      sight.setSuperPeople(spList);
      
      Sighting sight2 = new Sighting();
      sight2.setDate(date);
      sight2.setLocation(loc2);
      sight2.setSuperPeople(spList);
      
      dao.addSighting(sight);
      dao.addSighting(sight2);
      
      List<Location> locList = dao.getAllSightedLocationsBySuperPersonid(sp.getSuperPersonid());
      assertEquals(2, locList.size());
    }

    /**
     * Test of getAllSuperPeopleBySightedLocationid method, of class SuperDao.
     */
    @Test
    public void testGetAllSuperPeopleBySightedLocationid() {
        LocalDate date = LocalDate.parse("2017-02-23", DateTimeFormatter.ISO_DATE);
      Location loc = new Location();
      loc.setName("hi");
      loc.setDescription("Aussie");
      loc.setDate(date);
      loc.setAddress("lameaf");
      loc.setLatitude(34.56);
      loc.setLongitude(43.67);
      
      dao.addLocation(loc);
      List<Power> powers = new ArrayList<>();
        Power pow = new Power();
        pow.setDescription("super intelligence");
        dao.addPower(pow);
        powers.add(pow);
        SuperPerson sp = new SuperPerson();
        sp.setName("IronMan");
        sp.setDescription("Frikkin awesome yo");
        sp.setHero(true);
        sp.setSuperPower(powers);
        
        dao.addSuperPerson(sp);
        
        SuperPerson hero = dao.getSuperPersonByid(sp.getSuperPersonid());
        assertEquals(hero, sp);
        
        List<SuperPerson> spList = new ArrayList<>();
        spList.add(sp);
      Sighting sight = new Sighting();
      sight.setDate(date);
      sight.setLocation(loc);
      sight.setSuperPeople(spList);
      
      dao.addSighting(sight);
      spList = dao.getAllSuperPeopleBySightedLocationid(loc.getLocationid());
      assertEquals(1, spList.size());
    }

    /**
     * Test of deletePower method, of class SuperDao.
     */
    @Test
    public void testDeletePower() {
       List<Power> powers = new ArrayList<>();
        Power pow = new Power();
        pow.setDescription("super intelligence");
        dao.addPower(pow);
        powers.add(pow);
        Power power = dao.getPowerByid(pow.getPowerid());
        assertEquals(power, pow);
        dao.deletePower(pow.getPowerid());
        assertNull(dao.getPowerByid(pow.getPowerid()));
    }

    /**
     * Test of updatePower method, of class SuperDao.
     */
    @Test
    public void testUpdatePower() {
        Power pow = new Power();
        pow.setDescription("super intelligence");
        dao.addPower(pow);
        Power pow2 = new Power();
        pow2.setDescription("heh");
        pow2.setPowerid(pow.getPowerid());
        dao.updatePower(pow2);
        Power finalPow = dao.getPowerByid(pow.getPowerid());
        assertEquals(finalPow, pow2);
    }

    /**
     * Test of getAllSuperPeopleByPowerid method, of class SuperDao.
     */
    @Test
    public void testGetAllSuperPeopleByPowerid() {
        List<Power> powers = new ArrayList<>();
        Power pow = new Power();
        pow.setDescription("super intelligence");
        dao.addPower(pow);
        powers.add(pow);
        SuperPerson sp = new SuperPerson();
        sp.setName("IronMan");
        sp.setDescription("Frikkin awesome yo");
        sp.setHero(true);
        sp.setSuperPower(powers);
        
        dao.addSuperPerson(sp);
        
        SuperPerson hero = dao.getSuperPersonByid(sp.getSuperPersonid());
        assertEquals(hero, sp);
        List<SuperPerson> spList = dao.getAllSuperPeopleByPowerid(pow.getPowerid());
        assertEquals(1, spList.size());
    }
    
}
