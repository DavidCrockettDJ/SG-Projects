/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineModel;

import java.math.BigDecimal;

/**
 *
 * @author apprentice
 */
public class Change {
    
    private final BigDecimal QUARTER = new BigDecimal("0.25");
    private final BigDecimal DIME = new BigDecimal("0.10");
    private final BigDecimal NICKEL = new BigDecimal("0.05");
    private final BigDecimal PENNY = new BigDecimal("0.01");
    private int quarter = 0;
    private int dime = 0;
    private int nickel = 0;
    private int penny = 0;
    
    public BigDecimal getChange(BigDecimal money) {
        quarter = 0;
        dime = 0;
        nickel = 0;
        penny = 0;
        
        while (money.compareTo(QUARTER) >=0) {
            money = money.subtract(QUARTER);
            quarter++;
        }
        while (money.compareTo(DIME) >= 0) {
            money = money.subtract(DIME);
            dime++;
        }
        while (money.compareTo(NICKEL) >= 0) {
            money = money.subtract(NICKEL);
            nickel++;
        }
        while (money.compareTo(PENNY) >= 0) {
            money = money.subtract(PENNY);
            penny++;
        }
        return money;
    }
    
    public int getQuarter() {
        return quarter;
    }
    
    public int getDime() {
        return dime;
    }
    
    public int getNickel() {
        return nickel;
    }
    
    public int getPenny() {
        return penny;
    }
}
