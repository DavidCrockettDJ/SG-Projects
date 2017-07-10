/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc;

/**
 *
 * @author apprentice
 */
public class DataPersistanceException extends Exception{
    
    public DataPersistanceException(String message) {
        super(message);
    }
    
    public DataPersistanceException(String message, Throwable cause) {
        super(message, cause);
    }
}
