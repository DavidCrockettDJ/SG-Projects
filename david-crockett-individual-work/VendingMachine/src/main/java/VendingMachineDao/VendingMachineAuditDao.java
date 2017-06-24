/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineDao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author apprentice
 */
public class VendingMachineAuditDao implements AuditInterface {
    
    
    private final String AUDIT_FILE = "AuditFile.txt";
    
    
    @Override
    public void writeAudit(String entry) throws VendingMachineException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
            
        } catch (IOException x) {
            throw new VendingMachineException("Could not persist audit info.", x);
        }
        
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }
    
}
