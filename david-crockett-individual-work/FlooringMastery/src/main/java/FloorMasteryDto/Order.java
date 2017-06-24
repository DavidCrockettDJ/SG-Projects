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
public class Order {
    
    private int orderNum = 0;
    private String customerName;
    private BigDecimal area;
    private String state;
    private BigDecimal taxRate;
    private String floorType;
    private BigDecimal priceSquareFoot;
    private BigDecimal materialTotalCost;
    private BigDecimal laborCostSquareFoot;
    private BigDecimal laborCostTotal;
    private BigDecimal taxTotal;
    private BigDecimal totalCost;

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

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

    public BigDecimal getMaterialTotalCost() {
        return materialTotalCost;
    }

    public void setMaterialTotalCost(BigDecimal materialTotalCost) {
        this.materialTotalCost = materialTotalCost;
    }

    public BigDecimal getLaborCostSquareFoot() {
        return laborCostSquareFoot;
    }

    public void setLaborCostSquareFoot(BigDecimal laborCostSquareFoot) {
        this.laborCostSquareFoot = laborCostSquareFoot;
    }

    public BigDecimal getLaborCostTotal() {
        return laborCostTotal;
    }

    public void setLaborCostTotal(BigDecimal laborCostTotal) {
        this.laborCostTotal = laborCostTotal;
    }

    public BigDecimal getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(BigDecimal taxTotal) {
        this.taxTotal = taxTotal;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
    
}