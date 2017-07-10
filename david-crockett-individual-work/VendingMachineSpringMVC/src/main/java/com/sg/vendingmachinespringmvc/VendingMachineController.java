package com.sg.vendingmachinespringmvc;

import VendingMachineModel.Item;
import VendingMachineService.ServiceInterface;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VendingMachineController {
    ServiceInterface service;
    BigDecimal total = new BigDecimal("0.00");
    int itemId;
    String message = "";
    int quarter;
    int dime;
    int nickel;
    int penny;
    
    @Inject
    public VendingMachineController(ServiceInterface vendingMachineService) {
        this.service = vendingMachineService;
    }
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String displayItems(Model model) {
        List<Item> itemList = new ArrayList<>();
        try{
        itemList = service.displayItems();
        }catch (DataPersistanceException e) {
            message = "Could not load Inventory";
        }
        
        model.addAttribute("items", itemList);
        model.addAttribute("total", total);
        model.addAttribute("id", itemId);
        model.addAttribute("message", message);
        model.addAttribute("q", quarter);
        model.addAttribute("d", dime);
        model.addAttribute("n", nickel);
        model.addAttribute("p", penny);
        return "index";
    }
    
    @RequestMapping(value="/itemSelection", method=RequestMethod.GET)
    public String getItem(HttpServletRequest request, Model model) {
        itemId = Integer.parseInt(request.getParameter("itemId"));
        return "redirect:/";
    }
    
    @RequestMapping(value="/addMoney", method=RequestMethod.POST)
    public String addMoney(HttpServletRequest request, Model model) {
        if(request.getParameter("addDollar") != null){
        BigDecimal addDollar = new BigDecimal(request.getParameter("addDollar"));
        total = total.add(addDollar).setScale(2, RoundingMode.HALF_UP);
        }
        if(request.getParameter("addQuarter") != null){
        BigDecimal addQuarter = new BigDecimal(request.getParameter("addQuarter"));
        total = total.add(addQuarter).setScale(2, RoundingMode.HALF_UP);
        }
        if(request.getParameter("addDime") != null){
        BigDecimal addDime = new BigDecimal(request.getParameter("addDime"));
        total = total.add(addDime).setScale(2, RoundingMode.HALF_UP);
        }
        if(request.getParameter("addNickel") != null){
        BigDecimal addNickel = new BigDecimal(request.getParameter("addNickel"));
        total = total.add(addNickel).setScale(2, RoundingMode.HALF_UP);
        }
        
        
        return "redirect:/";
    }
    
    @RequestMapping(value="/makePurchase", method=RequestMethod.GET)
    public String makePurchase(Model model) {
        BigDecimal amountShort = new BigDecimal("0.00");
        Item item = new Item();
        if (itemId == 0) {
            message = "Please select an item.";
            return "redirect:/";
        }
        try{
        item = service.getItem(itemId);
        }catch (DataPersistanceException e) {
            message = "Could not load Inventory";
        }
        if (item.getQuantity() <= 0) {
            message = "SOLD OUT!!!";
            return "redirect:/";
        }
        if (service.validateFunds(total, item) == true) {
            message = "Thank You!!!";
            try{
            service.updateItem(item);
        }catch (DataPersistanceException e) {
            message = "Could not dispense item.";
        }
            total = total.subtract(item.getPrice());
            total = service.getChange(total);
            quarter = service.getQuarter();
            dime = service.getDime();
            nickel = service.getNickel();
            penny = service.getPenny();
        }else {
            amountShort = item.getPrice().subtract(total);
            message = "Please deposit: $" + amountShort;
        }
        
        return "redirect:/";
    }
    
    @RequestMapping(value="/getChange", method=RequestMethod.GET)
    public String getChange(Model model) {
        
        total = service.getChange(total);
        quarter = service.getQuarter();
        dime = service.getDime();
        nickel = service.getNickel();
        penny = service.getPenny();
        message = "";
        itemId = 0;
        return "redirect:/";
    }
}
