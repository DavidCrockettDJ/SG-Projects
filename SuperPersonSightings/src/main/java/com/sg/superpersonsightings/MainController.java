package com.sg.superpersonsightings;

import com.sg.SuperPersonDao.DaoInterface;
import com.sg.SuperPersonModel.Location;
import com.sg.SuperPersonModel.Organization;
import com.sg.SuperPersonModel.Power;
import com.sg.SuperPersonModel.Sighting;
import com.sg.SuperPersonModel.SuperPerson;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    DaoInterface dao;
    List<SuperPerson> spList;
    List<Organization> orgList;
    List<Location> locList;
    List<Sighting> sightList;
    List<Power> powList = new ArrayList<>();
    List<SuperPerson> tableList;
    
    @Inject
    public MainController(DaoInterface SuperDao) {
        this.dao = SuperDao;
    }
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String displayHome(HttpServletRequest request, Model model) {
        sightList = dao.getAllSightings();
        Collections.reverse(sightList);
        
        model.addAttribute("sightList", sightList);
        return "index";
    }
    
    @RequestMapping(value="/home", method=RequestMethod.GET)
    public String goHome() {
        
        return "redirect:/";
    }
    
    //Organizations
    @RequestMapping(value="/organizations", method=RequestMethod.GET)
    public String displayOrganizations(HttpServletRequest request, Model model) {
        orgList = dao.getAllOrganizations();
        
        model.addAttribute("orgList", orgList);
        return "organizations";
    }
    
    @RequestMapping(value="/addOrganization", method=RequestMethod.GET)
    public String addOrganization() {
        
        return "addOrganization";
    }
    
    @RequestMapping(value="/submitOrganization", method=RequestMethod.POST)
    public String submitOrganization(HttpServletRequest request, Model model) {
        Organization org = new Organization();
        org.setName(request.getParameter("organizationName"));
        org.setEmail(request.getParameter("organizationEmail"));
        org.setDescription(request.getParameter("organizationDescription"));
        org.setAddress(request.getParameter("organizationAddress"));
        dao.addOrganization(org);
        
        return "redirect:/organizations";
    }
    
    @RequestMapping(value="/viewOrganization", method=RequestMethod.GET)
    public String viewOrganization(HttpServletRequest request, Model model) {
        
        return "viewOrganization";
    }
    
    //Locations
    @RequestMapping(value="/locations", method=RequestMethod.GET)
    public String displayLocations(Model model) {
        locList = dao.getAllLocations();
        
        model.addAttribute("locList", locList);
        return "locations";
    }
    
    @RequestMapping(value="/addLocation", method=RequestMethod.GET)
    public String addLocation() {
        
        return "addLocation";
    }
    
    @RequestMapping(value="/submitLocation", method=RequestMethod.POST)
    public String submitLocation(HttpServletRequest request, Model model) {
        Location loc = new Location();
        loc.setName(request.getParameter("locationName"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        loc.setDate(LocalDate.parse(request.getParameter("locationDate"), formatter));
        loc.setDescription(request.getParameter("locationDescription"));
        loc.setAddress(request.getParameter("locationAddress"));
        loc.setLatitude(Double.parseDouble(request.getParameter("locationLatitude")));
        loc.setLongitude(Double.parseDouble(request.getParameter("locationLongitude")));
        dao.addLocation(loc);
        
        return "redirect:/locations";
    }
    
    @RequestMapping(value="/viewLocation", method=RequestMethod.GET)
    public String viewLocation(HttpServletRequest request, Model model) {
        Location location = dao.getLocationByid(Integer.parseInt(request.getParameter("Locationid")));
        
        model.addAttribute("location", location);
        return "viewLocation";
    }
    
    @RequestMapping(value="displayEditLocation", method = RequestMethod.GET)
    public String displayEditLocation(HttpServletRequest request, Model model) {
        Location location = dao.getLocationByid(Integer.parseInt(request.getParameter("Locationid")));
        
        model.addAttribute("location", location);
        return "editLocation";
    }
    
    @RequestMapping(value="editLocation", method = RequestMethod.POST)
    public String editLocation(HttpServletRequest request, Model model) {
        Location location = dao.getLocationByid(Integer.parseInt(request.getParameter("editButton")));
        location.setName(request.getParameter("locationName"));
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        //location.setDate(LocalDate.parse(request.getParameter("locationDate"), formatter));
        location.setDescription(request.getParameter("locationDescription"));
        location.setAddress(request.getParameter("locationAddress"));
        location.setLatitude(Double.parseDouble(request.getParameter("locationLatitude")));
        location.setLongitude(Double.parseDouble(request.getParameter("locationLongitude")));
        dao.updateLocation(location);
        
        return "locations";
    }
    
    @RequestMapping(value="deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request, Model model) {
        Location location = dao.getLocationByid(Integer.parseInt(request.getParameter("Locationid")));
        dao.deleteLocation(location.getLocationid());
        
        return "locations";
    }
    
    //SuperPeople
    @RequestMapping(value="/superPeople", method=RequestMethod.GET)
    public String displaySuperPeople(Model model) {
        spList = dao.getAllSuperPeople();
        
        model.addAttribute("spList", spList);
        return "superPeople";
    }
    
    @RequestMapping(value="/addSuperPerson", method=RequestMethod.GET)
    public String addSuperPerson() {
        
        return "addSuperPerson";
    }
    
    @RequestMapping(value="submitSuperPerson", method=RequestMethod.POST)
    public String submitSuperPerson(HttpServletRequest request, Model model) {
        Power pow = new Power();
        SuperPerson sp = new SuperPerson();
        pow.setDescription(request.getParameter("power"));
        dao.addPower(pow);
        powList.add(pow);
        sp.setName(request.getParameter("superName"));
        sp.setDescription(request.getParameter("superDescription"));
        sp.setHero(Boolean.parseBoolean(request.getParameter("heroChoice")));
        sp.setSuperPower(powList);
        
        dao.addSuperPerson(sp);
        powList.remove(pow);
        
        return "redirect:/superPeople";
    }
    
    @RequestMapping(value="viewSuperPerson", method = RequestMethod.GET)
    public String viewSuperPerson(HttpServletRequest request, Model model) {
        SuperPerson sp = dao.getSuperPersonByid(Integer.parseInt(request.getParameter("SuperPersonid")));
        String hero;
        if (sp.isHero() == true) {
            hero = "Hero";
        } else {
            hero = "villain";
        }
        
        model.addAttribute("hero", hero);
        model.addAttribute("sp", sp);
        return "viewSuperPerson";
    }
    
    @RequestMapping(value="displayEditSuperPerson", method = RequestMethod.GET)
    public String displayEditSuperPerson(HttpServletRequest request, Model model) {
        SuperPerson sp = dao.getSuperPersonByid(Integer.parseInt(request.getParameter("SuperPersonid")));
        
        model.addAttribute("sp", sp);
        return "editSuperPerson";
    }
    
    @RequestMapping(value="editSuperPerson", method = RequestMethod.POST)
    public String editSuperPerson(HttpServletRequest request, Model model) {
        List<Power> powers = dao.getAllSuperPowersBySuperPersonid(Integer.parseInt(request.getParameter("editButton")));
        SuperPerson sp = dao.getSuperPersonByid(Integer.parseInt(request.getParameter("editButton")));
        sp.setName(request.getParameter("superName"));
        sp.setDescription(request.getParameter("superDescription"));
        sp.setHero(Boolean.parseBoolean(request.getParameter("heroChoice")));
        sp.setSuperPower(powers);
        dao.updateSuperPerson(sp);
        
        
        return "superPeople";
    }
    
    @RequestMapping(value="deleteSuperPerson", method = RequestMethod.GET)
    public String deleteSuperPerson(HttpServletRequest request, Model model) {
        SuperPerson sp = dao.getSuperPersonByid(Integer.parseInt(request.getParameter("SuperPersonid")));
        dao.deleteSuperPerson(sp.getSuperPersonid());
        
        return "superPeople";
    }
    
    //Sightings
    @RequestMapping(value="sightings", method = RequestMethod.GET)
    public String displaySightings(HttpServletRequest request, Model model) {
        sightList = dao.getAllSightings();
        
        model.addAttribute("sightList", sightList);
        return "sightings";
    }
    
    @RequestMapping(value="addSighting", method = RequestMethod.GET)
    public String displayAddSighting(HttpServletRequest request, Model model) {
        locList = dao.getAllLocations();
        spList = dao.getAllSuperPeople();
        
        
        model.addAttribute("locList", locList);
        model.addAttribute("spList", spList);
        model.addAttribute("tList", tableList);
        return "addSighting";
    }
    
    @RequestMapping(value="submitSighting", method = RequestMethod.POST)
    public String submitSighting(HttpServletRequest request, Model model) {
        List<SuperPerson> sList = new ArrayList<>();
        Sighting sight = new Sighting();
        SuperPerson sp = dao.getSuperPersonByid(Integer.parseInt(request.getParameter("superPersonChoice")));
        Location loc = dao.getLocationByid(Integer.parseInt(request.getParameter("locationChoice")));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        sight.setDate(LocalDate.parse(request.getParameter("sightingDate"), formatter));
        sList.add(sp);
        sight.setSuperPeople(spList);
        sight.setLocation(loc);
        dao.addSighting(sight);
        
        return "sightings";
    }
    
    @RequestMapping(value="viewSighting", method = RequestMethod.GET)
    public String viewSighting(HttpServletRequest request, Model model) {
        Sighting sight = dao.getSightingByid(Integer.parseInt(request.getParameter("Sightingid")));
        Location loc = sight.getLocation();
        spList = sight.getSuperPeople();
        
        model.addAttribute("sight", sight);
        model.addAttribute("loc", loc);
        model.addAttribute("spList", spList);
        return "viewSighting";
    }
    
    @RequestMapping(value="deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request) {
    Sighting sight = dao.getSightingByid(Integer.parseInt(request.getParameter("Sightingid")));
    dao.deleteSighting(sight.getSightingid());
    
    return "sightings";
    }
    
    @RequestMapping(value="displayEditSighting", method = RequestMethod.GET)
    public String displayEditSighting(HttpServletRequest request, Model model) {
        Sighting sight = dao.getSightingByid(Integer.parseInt(request.getParameter("Sightingid")));
        Location loc = sight.getLocation();
        spList = sight.getSuperPeople();
        
        model.addAttribute("sight", sight);
        model.addAttribute("loc", loc);
        model.addAttribute("spList", spList);
        return "editSighting";
    }
    
    @RequestMapping(value="editSighting", method = RequestMethod.POST)
    public String editSighting(HttpServletRequest request, Model model) {
        Sighting sight = dao.getSightingByid(Integer.parseInt(request.getParameter("editButton")));
        Location loc = dao.getLocationByid(Integer.parseInt(request.getParameter("locationChoice")));
        sight.setLocation(loc);
        List<SuperPerson> sList = new ArrayList<>();
        SuperPerson s = dao.getSuperPersonByid(Integer.parseInt(request.getParameter("superPersonChoice")));
        sList.add(s);
        sight.setSuperPeople(sList);
        dao.updateSighting(sight);
        
        return "sightings";
    }
    
}
