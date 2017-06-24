/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorMasteryDto;

import java.math.BigDecimal;

/**
 *
 * @author apprentice
 */
public class Taxes {
    
    private String state;
    private BigDecimal tax;
    private BigDecimal taxTotal;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return tax;
    }

    public void setTaxRate(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(BigDecimal taxTotal) {
        this.taxTotal = taxTotal;
    }
    
}
