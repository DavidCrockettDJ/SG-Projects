/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorMasteryDao;

/**
 *
 * @author apprentice
 */
public class FloorMasteryException extends Exception{
    
    public FloorMasteryException (String message) {
        super(message);
    }
    
    public FloorMasteryException (String message, Throwable cause) {
        super(message, cause);
    }
}
