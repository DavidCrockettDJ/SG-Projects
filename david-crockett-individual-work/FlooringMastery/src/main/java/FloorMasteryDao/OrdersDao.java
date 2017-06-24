/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorMasteryDao;

import FloorMasteryDto.Floor;
import FloorMasteryDto.Order;
import FloorMasteryDto.Taxes;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class OrdersDao implements DaoInterface {

    Order order = new Order();
    int orderNumInc = 1;
    TaxesDao taxes = new TaxesDao();
    FloorDao floors = new FloorDao();
    private static final BigDecimal ONEHUNDRED = new BigDecimal("100");
    static LocalDateTime now = LocalDateTime.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");

    static String formatDateTime = now.format(formatter);
    Map<String, Map<Integer, Order>> orderFiles = new HashMap<>();
    private String orderFile = "Order_" + formatDateTime + ".txt";
    private static final String DELIMITER = "::";

    private void loadOrders(String date) throws FloorMasteryException {
        Scanner sc = null;
        try {
            sc = new Scanner(new BufferedReader(new FileReader(orderFile)));
        } catch (FileNotFoundException e) {
            throw new FloorMasteryException(
                    "Orders for entered date do not exist.", e);
        }
        String currentLine;
        String[] currentTokens = null;
        //int orderNum = Integer.parseInt(currentTokens[0]);
        if (!orderFiles.containsKey(date)) {
        Map<Integer, Order> orders = new HashMap<>();
        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Order currentOrder = new Order();
            int orderNum = Integer.parseInt(currentTokens[0]);
            currentOrder.setOrderNum(orderNum);
            currentOrder.setCustomerName(currentTokens[1]);
            currentOrder.setArea(new BigDecimal(currentTokens[2]));
            currentOrder.setState(currentTokens[3]);
            currentOrder.setTaxRate(new BigDecimal(currentTokens[4]));
            currentOrder.setFloorType(currentTokens[5]);
            currentOrder.setPriceSquareFoot(new BigDecimal(currentTokens[6]));
            currentOrder.setMaterialTotalCost(new BigDecimal(currentTokens[7]));
            currentOrder.setLaborCostSquareFoot(new BigDecimal(currentTokens[8]));
            currentOrder.setLaborCostTotal(new BigDecimal(currentTokens[9]));
            currentOrder.setTaxTotal(new BigDecimal(currentTokens[10]));
            currentOrder.setTotalCost(new BigDecimal(currentTokens[11]));

            orders.put(currentOrder.getOrderNum(), currentOrder);

        }
        orderFiles.put(date, orders);
        }
        sc.close();
    }

    private void writeOrder() throws FloorMasteryException {
        PrintWriter out = null;
        for (String key : orderFiles.keySet()) {
            Map<Integer, Order> orders = orderFiles.get(key);
            orderFile = "Order_" + key + ".txt";
            try{
            out = new PrintWriter(new FileWriter(orderFile));
            } catch (IOException e) {
                throw new FloorMasteryException(
                        "Could not save data to file.", e);
                    }
            List<Order> orderList = new ArrayList<>(orders.values());
            for (Order currentOrder : orderList) {
                out.println(currentOrder.getOrderNum() + DELIMITER
                        + currentOrder.getCustomerName() + DELIMITER
                        + currentOrder.getArea() + DELIMITER
                        + currentOrder.getState() + DELIMITER
                        + currentOrder.getTaxRate() + DELIMITER
                        + currentOrder.getFloorType() + DELIMITER
                        + currentOrder.getPriceSquareFoot() + DELIMITER
                        + currentOrder.getMaterialTotalCost() + DELIMITER
                        + currentOrder.getLaborCostSquareFoot() + DELIMITER
                        + currentOrder.getLaborCostTotal() + DELIMITER
                        + currentOrder.getTaxTotal() + DELIMITER
                        + currentOrder.getTotalCost());
            }
            out.flush();
        }
        out.close();
    }

    @Override
    public void openFile() throws FloorMasteryException {
        try {
            loadOrders(formatDateTime);
        } catch (FloorMasteryException e) {
            Map<Integer, Order> orders = orderFiles.get(formatDateTime);
        }
    }

    @Override
    public void existingOrderNum() throws FloorMasteryException {
        try {
            loadOrders(formatDateTime);
            int max = 0;
            Map<Integer, Order> orders = orderFiles.get(formatDateTime);
            for (int key : orders.keySet()) {
                if (key > max) {
                    max = key;
                }
            }
            orderNumInc = ++max;
        } catch (FloorMasteryException e) {
            orderNumInc = 1;
        }
    }

    @Override
    public List<Order> displayOrders(String date) throws FloorMasteryException {
        orderFile = "Order_" + date + ".txt";
        loadOrders(date);
        Map<Integer, Order> orders = orderFiles.get(date);
        List<Order> displayOrderList = new ArrayList<>(orders.values());
        return displayOrderList;
    }

    @Override
    public void addOrder(Order order) throws FloorMasteryException{
        if (order != null) {
        existingOrderNum();
        order.setOrderNum(orderNum());
        if (!orderFiles.containsKey(formatDateTime)) {
        orderFiles.put(formatDateTime, new HashMap<>());
        }
        Map<Integer, Order> orders = orderFiles.get(formatDateTime);
        orders.put(order.getOrderNum(), order);
        }
    }

    @Override
    public Order findRemoveOrder(String date, int orderNum) throws NullPointerException{
        try{
        loadOrders(date);
        }catch (FloorMasteryException e) {
            return null;
        }
        try{
        Map<Integer, Order> orders = orderFiles.get(date);
        return orders.get(orderNum);
        }catch (NullPointerException e) {
            return null;
        }       
    }

    @Override
    public void removeOrder(int orderNum, String date) throws FloorMasteryException{
        loadOrders(date);
        Map<Integer, Order> orders = orderFiles.get(date);
        orders.remove(orderNum);
    }

    @Override
    public Order findEditOrder(String date, int orderNum) throws NullPointerException {
        try{
        loadOrders(date);
        }catch (FloorMasteryException e) {
            return null;
        }
        try{
        Map<Integer, Order> orders = orderFiles.get(date);
        return orders.get(orderNum);
        }catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public void editOrder(String date, Order order) throws FloorMasteryException {
        orderFile = "Order_" + date + ".txt";
        Map<Integer, Order> orders = orderFiles.get(date);
        orders.put(order.getOrderNum(), order);
    }

    @Override
    public void saveWork() throws FloorMasteryException {
        writeOrder();
    }

    @Override
    public int orderNum() {
        return orderNumInc;
    }

}
