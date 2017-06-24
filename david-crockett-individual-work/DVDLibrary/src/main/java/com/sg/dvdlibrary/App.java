/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary;

import DVDcontroller.DVDController;
import DVDdao.DVDException;

/**
 *
 * @author apprentice
 */
public class App {
    
    public static void main(String[] args) throws DVDException {
        DVDController controller = new DVDController();
        controller.execute();
    }
    
}
