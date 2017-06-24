/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingAdvice;

import VendingMachineDao.AuditInterface;
import VendingMachineDao.VendingMachineException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author apprentice
 */
public class LoggingAdvice {
    AuditInterface auditDao;
    
    public LoggingAdvice(AuditInterface auditDao) {
        this.auditDao = auditDao;
    }
    
    public void createAuditEntry(JoinPoint jp, Exception ex) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": " + ex.getClass().getName() + " ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAudit(auditEntry);
        } catch (VendingMachineException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
    
}
