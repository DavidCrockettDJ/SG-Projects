/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineDao;

/**
 *
 * @author apprentice
 */
public class VendingMachineException extends Exception {
    
    public VendingMachineException(String message) {
        super(message);
    }
    
    public VendingMachineException(String message, Throwable cause) {
        super(message, cause);
    }
}
