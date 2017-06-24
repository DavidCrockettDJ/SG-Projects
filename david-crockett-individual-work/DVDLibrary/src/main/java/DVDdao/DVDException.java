/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVDdao;

/**
 *
 * @author apprentice
 */
public class DVDException extends Exception{
    
    
    public DVDException(String message) {
        super(message);
    }
    
    public DVDException(String message, Throwable cause) {
        super(message, cause);
    }
}

