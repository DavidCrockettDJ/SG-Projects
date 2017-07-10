package com.sg.addressbook;

import AddressDao.DaoInterface;
import Model.Address;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AddressController {
    DaoInterface dao;
    
    @Inject
    public AddressController(DaoInterface addressDaoMemImpl) {
        this.dao = addressDaoMemImpl;
    }
        
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String displayContacts(HttpServletRequest request, Model model) {
        List<Address> addressList = dao.getAllAddresses();
        
        
        model.addAttribute("Addresses", addressList);
        return "index";
    }
    
    @RequestMapping(value="/addAddress", method=RequestMethod.POST)
    public String addAddress(HttpServletRequest request, Model model) {
        Address newAddress = new Address();
        newAddress.setFirstName(request.getParameter("firstName"));
        newAddress.setLastName(request.getParameter("lastName"));
        newAddress.setStreetAddress(request.getParameter("streetAddress"));
        newAddress.setCity(request.getParameter("city"));
        newAddress.setZipcode(Integer.parseInt(request.getParameter("zipcode")));
        
        dao.addAddress(newAddress);
        return "redirect:/";
    }
    
    @RequestMapping(value="/deleteAddress", method=RequestMethod.GET)
    public String deleteAddress(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("deleteButton"));
        dao.deleteAddress(id);
        return "redirect:/";
    }
    
    @RequestMapping(value="/displayEditAddress", method=RequestMethod.GET)
    public String editAddress(HttpServletRequest request, Model model) {
        
    }
    
}
