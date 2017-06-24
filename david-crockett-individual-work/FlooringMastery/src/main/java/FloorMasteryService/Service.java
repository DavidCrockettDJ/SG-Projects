/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorMasteryService;

import FloorMasteryDao.DaoInterface;
import FloorMasteryDao.FloorDao;
import FloorMasteryDao.FloorMasteryException;
import FloorMasteryDao.TaxesDao;
import FloorMasteryDto.Floor;
import FloorMasteryDto.Order;
import FloorMasteryDto.Taxes;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class Service {
    private DaoInterface dao;
    private String training;
    TaxesDao taxes = new TaxesDao();
    FloorDao floors = new FloorDao();
    private static final BigDecimal ONEHUNDRED = new BigDecimal("100");
    
    public Service(DaoInterface dao, String training) {
        this.dao = dao;
        this.training = training;
    }
    
    public void openFile() throws FloorMasteryException{
        try{
        dao.openFile();
        }catch (FloorMasteryException e) {
            
        }
    }
    
    public void existingOrderNum() throws FloorMasteryException{
        dao.existingOrderNum();
    }
    
    public List<Order> displayOrders(String date) throws FloorMasteryException{
        return dao.displayOrders(date);
    }
    
    public void addOrder(Order order) throws FloorMasteryException{
        if (order != null) {
        Taxes tax = taxes.getTax(order.getState());
        order.setTaxRate(tax.getTaxRate());
        BigDecimal taxRate = tax.getTaxRate();
        taxRate = taxRate.divide(ONEHUNDRED).setScale(4, RoundingMode.HALF_UP);
        Floor floor = floors.getFloor(order.getFloorType());
        order.setPriceSquareFoot(floor.getPriceSquareFoot());
        order.setLaborCostSquareFoot(floor.getLaborCostSquareFoot());
        order.setMaterialTotalCost(floor.getPriceSquareFoot().multiply(order.getArea()).setScale(2, RoundingMode.HALF_UP));
        order.setLaborCostTotal(floor.getLaborCostSquareFoot().multiply(order.getArea()).setScale(2, RoundingMode.HALF_UP));
        order.setTaxTotal(taxRate.multiply(order.getMaterialTotalCost().add(order.getLaborCostTotal())).setScale(2, RoundingMode.HALF_UP));
        order.setTotalCost(order.getTaxTotal().add(order.getMaterialTotalCost().add(order.getLaborCostTotal())).setScale(2, RoundingMode.HALF_UP));
        dao.addOrder(order);
        }
    }
    
    public Order findRemoveOrder(String date, int orderNum) throws NullPointerException{
        return dao.findRemoveOrder(date, orderNum);
    }
    
    public void removeOrder(String date, int orderNum) throws FloorMasteryException{
        dao.removeOrder(orderNum, date);
    }
    
    public Order findEditOrder(String date, int orderNum) throws NullPointerException{
       return dao.findEditOrder(date, orderNum);
    }
    
    public void editOrder(String date, Order order) throws FloorMasteryException {
        Taxes tax = taxes.getTax(order.getState());
        order.setTaxRate(tax.getTaxRate());
        BigDecimal taxRate = tax.getTaxRate();
        taxRate = taxRate.divide(ONEHUNDRED).setScale(4, RoundingMode.HALF_UP);
        Floor floor = floors.getFloor(order.getFloorType());
        order.setPriceSquareFoot(floor.getPriceSquareFoot());
        order.setLaborCostSquareFoot(floor.getLaborCostSquareFoot());
        order.setMaterialTotalCost(floor.getPriceSquareFoot().multiply(order.getArea()).setScale(2, RoundingMode.HALF_UP));
        order.setLaborCostTotal(floor.getLaborCostSquareFoot().multiply(order.getArea()).setScale(2, RoundingMode.HALF_UP));
        order.setTaxTotal(taxRate.multiply(order.getMaterialTotalCost().add(order.getLaborCostTotal())).setScale(2, RoundingMode.HALF_UP));
        order.setTotalCost(order.getTaxTotal().add(order.getMaterialTotalCost().add(order.getLaborCostTotal())).setScale(2, RoundingMode.HALF_UP));
        dao.editOrder(date, order);
    }
    
    public void saveWork() throws FloorMasteryException{
        if (training.equals("Production")){
        dao.saveWork();
        }
    }
}
