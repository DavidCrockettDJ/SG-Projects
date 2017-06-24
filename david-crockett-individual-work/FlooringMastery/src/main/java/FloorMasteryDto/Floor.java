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
public class Floor {
    
    private String floorType;
    private BigDecimal priceSquareFoot;
    private BigDecimal laborCostSquareFoot;

    public String getFloorType() {
        return floorType;
    }

    public void setFloorType(String floorType) {
        this.floorType = floorType;
    }

    public BigDecimal getPriceSquareFoot() {
        return priceSquareFoot;
    }

    public void setPriceSquareFoot(BigDecimal priceSquareFoot) {
        this.priceSquareFoot = priceSquareFoot;
    }
    
    public BigDecimal getLaborCostSquareFoot () {
        return laborCostSquareFoot;
    }
    
    public void setLaborCostSquareFoot(BigDecimal laborCostSquareFoot) {
        this.laborCostSquareFoot = laborCostSquareFoot;
    }
}
