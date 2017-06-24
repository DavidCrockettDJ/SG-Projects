/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorMasteryDao;

import static FloorMasteryDao.OrdersDao.formatDateTime;
import FloorMasteryDto.Floor;
import FloorMasteryDto.Order;
import FloorMasteryDto.Taxes;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class MockDao implements DaoInterface{
    
    Order order = new Order();
    int orderNumInc = 1;
    TaxesDao taxes = new TaxesDao();
    FloorDao floors = new FloorDao();
    static LocalDateTime now = LocalDateTime.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
    static String formatDateTime = now.format(formatter);
    
    Map<String, Map<Integer, Order>> orderFiles = new HashMap<>();
    
    @Override
    public void openFile() throws FloorMasteryException {
    }

    @Override
    public void existingOrderNum() throws FloorMasteryException {
    }

    @Override
    public void addOrder(Order order) throws FloorMasteryException {
        Taxes tax = taxes.getTax(order.getState());
        order.setOrderNum(orderNum());
        order.setTaxRate(tax.getTaxRate());
        BigDecimal taxRate = tax.getTaxRate();
        taxRate = taxRate.setScale(4, RoundingMode.HALF_UP);
        Floor floor = floors.getFloor(order.getFloorType());
        order.setPriceSquareFoot(floor.getPriceSquareFoot());
        order.setLaborCostSquareFoot(floor.getLaborCostSquareFoot());
        order.setMaterialTotalCost(floor.getPriceSquareFoot().multiply(order.getArea()).setScale(2, RoundingMode.HALF_UP));
        order.setLaborCostTotal(floor.getLaborCostSquareFoot().multiply(order.getArea()).setScale(2, RoundingMode.HALF_UP));
        order.setTaxTotal(taxRate.multiply(order.getMaterialTotalCost().add(order.getLaborCostTotal())).setScale(2, RoundingMode.HALF_UP));
        order.setTotalCost(order.getTaxTotal().add(order.getMaterialTotalCost().add(order.getLaborCostTotal())).setScale(2, RoundingMode.HALF_UP));
        orderFiles.put(formatDateTime, new HashMap<>()); 
        Map<Integer, Order> orders = orderFiles.get(formatDateTime);
        orders.put(order.getOrderNum(), order);
    }

    @Override
    public List<Order> displayOrders(String date) throws FloorMasteryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editOrder(String date, Order order) throws FloorMasteryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order findEditOrder(String date, int orderNum) throws NullPointerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order findRemoveOrder(String date, int orderNum) throws NullPointerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int orderNum() {
        return 5;
    }

    @Override
    public void removeOrder(int orderNum, String date) throws FloorMasteryException {
        Map<Integer, Order> orders = orderFiles.get(date);
        orders.remove(orderNum);
    }

    @Override
    public void saveWork() throws FloorMasteryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
