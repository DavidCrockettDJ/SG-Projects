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
public interface AuditInterface {

    void writeAudit(String entry) throws VendingMachineException;
    
}
