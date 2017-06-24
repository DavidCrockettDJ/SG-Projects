/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery;

import FloorMasteryController.Controller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class App {
    
    public static void main(String[] args) throws Exception{
        //UserIoInterface io = new UserIo();
        //View view = new View(io);
//        DaoInterface dao = new OrdersDao();
//        Service service = new Service(dao);
//        Controller controller = new Controller(view, service);
//        controller.execute();
        ApplicationContext ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        Controller controller = ctx.getBean("controller", Controller.class);
        controller.execute();
    }
}
