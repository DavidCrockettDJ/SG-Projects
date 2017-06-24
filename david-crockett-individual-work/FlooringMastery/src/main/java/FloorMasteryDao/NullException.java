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
public class NullException extends Exception{
    
    public NullException (String message) {
        super(message);
    }
    
    public NullException (String message, Throwable cause) {
        super(message, cause);
    }
}
