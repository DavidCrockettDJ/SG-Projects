/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorMasteryController;

import FloorMasteryDao.FloorMasteryException;
import FloorMasteryDao.InvalidResponseException;
import FloorMasteryDto.Order;
import FloorMasteryService.Service;
import FloorMasteryUI.View;

/**
 *
 * @author apprentice
 */
public class Controller {
    View view;
    Service service;
    int input = 0;
    
    
    public Controller(View view, Service service) {
        this.view = view;
        this.service = service;
    }
    
    public void execute() throws Exception{
        openFile();
        boolean continueProgram = true;
    while (continueProgram == true) {
       displayMenu();
       
       switch(input) {
           case 1:
               displayOrders();
               break;
           case 2:
               addOrder();
               break;
           case 3:
               editOrder();
               break;
           case 4:
               removeOrder();
               break;
           case 5:
               saveWork();
               break;
           case 6:
               exitProgram();
               continueProgram = false;
               break;
           
       }
    }
    
    exitMessage();
    }
    
    private void openFile() throws FloorMasteryException{
        try{
        service.openFile();
        }catch (FloorMasteryException e) {
            view.displayErrorMessage(e);
        }
    }
    
    private void displayMenu() {
        try{
        input = view.displayMenu();
        }catch (InvalidResponseException e) {
            view.displayErrorMessage(e);
        }
    }
    
    
    private void displayOrders() throws FloorMasteryException{
        view.setDate();
        try{
        view.DisplayOrders(service.displayOrders(view.getDate()));
        }catch (FloorMasteryException e) {
            view.displayErrorMessage(e);
        }
    }
    
    private void addOrder() throws FloorMasteryException{
        try{
        service.addOrder(view.addOrder());
        }catch (FloorMasteryException e) {
          //Have to catch exception here. No need to do anything or send message.  
        }
    }
    
    private void removeOrder() throws FloorMasteryException{
        view.setDate();
        int orderNum = view.removeOrder();
        try{
        Order order = service.findRemoveOrder(view.getDate(), orderNum);
        orderNum = view.confirmRemoveOrder(order);
        }catch (NullPointerException e) {
            orderNum = 0;
        }
        if (orderNum == 0) {
            view.displayKeepOrder();
        } else {
            service.removeOrder(view.getDate(), orderNum);   
        }
    }
    
    private void editOrder() throws FloorMasteryException{
        view.setDate();
        int orderNum = view.findEditOrder();
        Order order = service.findEditOrder(view.getDate(), orderNum);
        if (order == null) {
            view.noOrder();
        } else{
        order = view.editOrder(order);
        service.editOrder(view.getDate(), order);
        }
            
        }
    
    
    private void saveWork() throws FloorMasteryException{
        service.saveWork();
    }
    
    private void exitProgram() throws FloorMasteryException{
        boolean choice;
        choice = view.exitProgram();
        if (choice == true) {
            service.saveWork();
        }
    }
    
    private void exitMessage() throws FloorMasteryException{
        view.displayExitMessage();
    }
}
