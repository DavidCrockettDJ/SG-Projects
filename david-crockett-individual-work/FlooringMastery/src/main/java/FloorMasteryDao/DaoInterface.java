/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorMasteryDao;

import FloorMasteryDto.Order;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface DaoInterface {
    
    public void openFile() throws FloorMasteryException;
    
    public void existingOrderNum() throws FloorMasteryException;

    void addOrder(Order order) throws FloorMasteryException;

    List<Order> displayOrders(String date) throws FloorMasteryException;

    void editOrder(String date, Order order) throws FloorMasteryException;

    Order findEditOrder(String date, int orderNum) throws NullPointerException;

    Order findRemoveOrder(String date, int orderNum) throws NullPointerException;

    int orderNum();

    void removeOrder(int orderNum, String date) throws FloorMasteryException;

    void saveWork() throws FloorMasteryException;
    
}
