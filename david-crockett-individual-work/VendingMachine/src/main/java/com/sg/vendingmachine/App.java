/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine;

import VendingMachineController.VendingMachineController;
import VendingMachineDao.VendingMachineException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class App {
    
    public static void main(String[] args) throws VendingMachineException {
//        DaoInterface dao = new VendingMachineDao();
//        UserIO io = new UserIOImpl();
//        VendingMachineView view = new VendingMachineView(io);
//        VendingMachineServiceLayer service = new VendingMachineServiceLayer(dao);
//        VendingMachineController controller = new VendingMachineController(service, view);
        ApplicationContext ctx = new ClassPathXmlApplicationContext("vendingContext.xml");
        VendingMachineController controller = ctx.getBean("controller", VendingMachineController.class);
        controller.execute();
    }
}
