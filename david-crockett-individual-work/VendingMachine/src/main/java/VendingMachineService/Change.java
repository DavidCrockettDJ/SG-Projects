/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineService;

import VendingMachineDto.VendingMachineDto;
import VendingMachineUI.VendingMachineView;
import java.math.BigDecimal;

/**
 *
 * @author apprentice
 */
public class Change {

    public final BigDecimal QUARTER = new BigDecimal("0.25");
    public int quarter = 0;
    public final BigDecimal DIME = new BigDecimal("0.10");
    public int dime = 0;
    public final BigDecimal NICKEL = new BigDecimal("0.05");
    public int nickel = 0;
    public final BigDecimal PENNY = new BigDecimal("0.01");
    public int penny = 0;

    public BigDecimal getChange(BigDecimal money) {
        while(money.compareTo(QUARTER) >= 0) {
            money = money.subtract(QUARTER);
            quarter++;
        }
        while(money.compareTo(DIME) >= 0) {
            money = money.subtract(DIME);
            dime++;
        }
        while(money.compareTo(NICKEL) >= 0) {
            money = money.subtract(NICKEL);
            nickel++;
        }
        while(money.compareTo(PENNY) >= 0) {
            money = money.subtract(PENNY);
            penny++;
        }
        return money;
    }
    
    public int getQuarters() {
        return quarter;
    }
    
    public int getDimes() {
        return dime;
    }
    
    public int getNickels() {
        return nickel;
    }
    
    public int getPennies() {
        return penny;
    }
    
    
    

}
