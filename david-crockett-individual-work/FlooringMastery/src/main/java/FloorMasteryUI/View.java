/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorMasteryUI;

import FloorMasteryDao.FloorMasteryException;
import FloorMasteryDao.InvalidResponseException;
import FloorMasteryDto.Order;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class View {

    UserIoInterface io;

    public View(UserIoInterface io) {
        this.io = io;
    }

    private String date;

    public int displayMenu() throws InvalidResponseException {
        io.print("===Flooring Arena 3.0===\n"
                + "1. Display Orders\n"
                + "2. Add an Order\n"
                + "3. Edit an Order\n"
                + "4. Remove an Order\n"
                + "5. Save Current Work\n"
                + "6. Exit");
        try {
            int userChoice = io.readInt("Enter the number for the option you'd like to choose (1-6):", 0, 8);
            return userChoice;
        } catch (NumberFormatException e) {
            throw new InvalidResponseException("ERROR: Invalid entry.");
        }
    }

    public void DisplayOrders(List<Order> orders) {
        for (Order currentOrder : orders) {
            io.print("***Order Number: " + currentOrder.getOrderNum() + "\n Customer name: " + currentOrder.getCustomerName() + "\n State: " + currentOrder.getState() + "\n Floor material: " + currentOrder.getFloorType()
                    + "\n Area: " + currentOrder.getArea() + "/ Tax rate: " + currentOrder.getTaxRate() + "%" + "\n Price per square foot: $" + currentOrder.getPriceSquareFoot()
                    + "\n Labor cost per square foot: $" + currentOrder.getLaborCostSquareFoot() + "\n Tax total: $" + currentOrder.getTaxTotal() + "\n Material cost: $"
                    + currentOrder.getMaterialTotalCost() + "\n Labor cost: $" + currentOrder.getLaborCostTotal() + "\n Total cost: $" + currentOrder.getTotalCost());
        }
    }

    public Order addOrder() throws FloorMasteryException{

        Order newOrder = new Order();
        boolean validResponse = true;
        io.print("You've selected to add an order! Please input the following info.");
        newOrder.setCustomerName(io.readString("Customer Name:"));
        do {
        newOrder.setState(io.readString("State (Ohio, Pennsylvania, Michigan, Indiana):"));
        validResponse = true;
        if (!newOrder.getState().equalsIgnoreCase("Ohio") && !newOrder.getState().equalsIgnoreCase("Pennsylvania") && !newOrder.getState().equalsIgnoreCase("Michigan") && !newOrder.getState().equalsIgnoreCase("Indiana")) {
            validResponse = false;
        }
        }while (validResponse == false);
        do {
        newOrder.setFloorType(io.readString("Floor Material:"));
        validResponse = true;
        if (!newOrder.getFloorType().equalsIgnoreCase("Wood") && !newOrder.getFloorType().equalsIgnoreCase("Lava") && !newOrder.getFloorType().equalsIgnoreCase("Spikes") && !newOrder.getFloorType().equalsIgnoreCase("Carpet")
                && !newOrder.getFloorType().equalsIgnoreCase("Tile") && !newOrder.getFloorType().equalsIgnoreCase("Laminate") && !newOrder.getFloorType().equalsIgnoreCase("Lunarsoil")) {
            validResponse = false;
        }
        }while (validResponse == false);
        do {
            try {
                newOrder.setArea(new BigDecimal(io.readString("Area:")));
                validResponse = true;
            } catch (NumberFormatException e) {
                validResponse = false;
                io.print("Invalid entry.");
            }
        } while (validResponse == false);
        io.print("Customer Name: " + newOrder.getCustomerName() + " State: " + newOrder.getState() + " Floor Material: " + newOrder.getFloorType() + " Area: " + newOrder.getArea());
        String userConfirm = io.readString("Would you like to confirm this order? (yes/no)");
        if (userConfirm.equalsIgnoreCase("yes") || userConfirm.equalsIgnoreCase("y")) {
            return newOrder;
        }
        return null;
    }

    public String setDate() {
        date = io.readString("Please enter a date (MMDDYYYY): ");
        return date;
    }

    public String getDate() {
        return date;
    }

    public int removeOrder() throws NumberFormatException{
        boolean validEntry = true;
        do{
            try{
        int userOrderNum = io.readInt("Please enter the order number of the order you'd like to remove: ");
        validEntry = true;
        return userOrderNum;
            }catch (NumberFormatException e) {
                validEntry = false;
            }
        }while (validEntry == false);
        return 0;
    }

    public int confirmRemoveOrder(Order order) {
        io.print(order.getOrderNum() + "/ " + order.getCustomerName() + "/ " + order.getState() + "/ " + order.getFloorType() + "/ " + order.getArea() + "/ "
                + order.getTaxRate() + "/ " + order.getPriceSquareFoot() + "/ " + order.getLaborCostSquareFoot() + "/ " + order.getTaxTotal() + "/ " + order.getMaterialTotalCost() + "/ "
                + order.getLaborCostTotal() + "/ " + order.getTotalCost());
        String userChoice = io.readString("Are you sure you'd like to remove this order? (yes/no)");
        while (!userChoice.equalsIgnoreCase("yes") || !userChoice.equalsIgnoreCase("no")) {
            if (userChoice.equalsIgnoreCase("yes")) {
                return order.getOrderNum();
            }
            if (userChoice.equalsIgnoreCase("no")) {
                return 0;
            }
        }
        return 0;
    }

    public int findEditOrder() {
        boolean validEntry = true;
        do{
            try{
        int userOrderNum = io.readInt("Please enter the order number of the order you'd like to edit: ");
        validEntry = true;
        return userOrderNum;
            }catch (NumberFormatException e) {
                validEntry = false;
            }
        }while (validEntry == false);
        return 0;
    }

    public Order editOrder(Order order) {
        boolean validResponse = true;
        String userInput = io.readString("Customer name: " + order.getCustomerName());
        if (!userInput.isEmpty()) {
            order.setCustomerName(userInput);
        }
        userInput = io.readString("State (Ohio, Pennsylvania, Michigan, Indiana): " + order.getState());
        if (!userInput.isEmpty()) {
            order.setState(userInput);
        }
        userInput = io.readString("Floor Material: " + order.getFloorType());
        if (!userInput.isEmpty()) {
            order.setFloorType(userInput);
        }
        userInput = io.readString("Area: " + order.getArea());
        if (!userInput.isEmpty()) {
            do {
            try {
                order.setArea(new BigDecimal(io.readString("Area:")));
                validResponse = true;
            } catch (NumberFormatException e) {
                validResponse = false;
                io.print("Invalid entry.");
            }
        } while (validResponse == false);
            order.setArea(new BigDecimal(userInput));
        }
        return order;
    }

    public boolean exitProgram() {
        String userChoice;
        do {
            userChoice = io.readString("Make sure to save your work! Would you like to save now? (yes/no)");
            if (userChoice.equalsIgnoreCase("yes")) {
                return true;
            }
            if (userChoice.equalsIgnoreCase("no")) {
                return false;
            }
        } while (!userChoice.equalsIgnoreCase("yes") || !userChoice.equalsIgnoreCase("no"));

        return false;

    }

    public void displayKeepOrder() {
        io.print("Order has not been removed.");
    }
    
    public void noOrder() {
        io.print("Order does not exist.");
    }

    public String displayErrorMessage(Exception e) {
        String errorMessage = e.getMessage();
        io.print(errorMessage);
        return errorMessage;
    }

    public void displayExitMessage() {
        io.print("Goodbye! Thanks for dropping by!");
    }
}
