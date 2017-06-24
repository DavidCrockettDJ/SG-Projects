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
public class InvalidResponseException extends Exception{
    
    public InvalidResponseException (String message) {
        super(message);
    }
    
    public InvalidResponseException (String message, Throwable cause) {
        super(message, cause);
    }
}
