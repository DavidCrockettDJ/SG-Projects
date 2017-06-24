/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineUI;

import VendingMachineDto.VendingMachineDto;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 *
 * @author apprentice
 */
public class VendingMachineView {
    UserIO io;
    VendingMachineDto item = new VendingMachineDto();
    private BigDecimal userMoney = new BigDecimal("0").setScale(2, RoundingMode.HALF_UP);
    private String selectedItem;
    
    public VendingMachineView(UserIO io) {
        this.io = io;
    }
    
    public String displayWelcomeMenu(ArrayList<VendingMachineDto> item) {
        io.print("xxxxxxxWelcome to Vending Machine 3.0xxxxxxxx");
        io.print("");
        io.print("Here's what we have to offer today: ");
        io.print("xxxxxxxInventoryxxxxxxx");
        io.print("");
        for (VendingMachineDto currentItem : item) {
            io.print(currentItem.getName() + " [$" + currentItem.getPrice() + "]  [" + currentItem.getNumOfItems() + " available.]");
        }
        String userChoice = io.readString("Would you like to add money to buy an item? (yes/no)");
        return userChoice;
    }
    
    public BigDecimal collectMoney() {
        String input = "";
        while (input.isEmpty()) {
            input = io.readString("Please enter the amount of money you'd like to add: ");
        }
        userMoney = new BigDecimal(input);
        io.print("Perfect! you've entered $" + userMoney);
        return userMoney;
    }
    
    public String selectItem(ArrayList<VendingMachineDto> item) {
        io.print("You currently have $" + userMoney + " Please choose from the following selection: ");
        io.print("xxxxxxxInventoryxxxxxxx");
        io.print("");
        for (VendingMachineDto currentItem: item) {
            io.print(currentItem.getName() + " [$" + currentItem.getPrice() + "] [" + currentItem.getNumOfItems() + " available.]");
        }
        selectedItem = io.readString("Please enter the name of the item you'd like to purchase, or type Exit to leave: ");
        return selectedItem;
    }
    
    public String getSelectedItem() {
        return selectedItem;
    }

    public BigDecimal displayInsufficientfunds() {
        String userChoice;
        userChoice = io.readString("Would you like to add more money? (yes/no)");
        if (userChoice.equalsIgnoreCase("yes")) {
            userMoney = userMoney.add(new BigDecimal(io.readString("How much money would you like to add?: ")));
        } 
        return userMoney;
    }
    
    public BigDecimal getUserMoney() {
        return userMoney;
    }
    
    public void printItem(VendingMachineDto item) {
        io.print("Thank you for your purchase! Here is your item: " + item.getName());
        userMoney = userMoney.subtract((item.getPrice()));
        io.print("Your change is: $" + userMoney);
    }  
    
    public String printChange(int quarters, int dimes, int nickels, int pennies) {
        io.print("Payment will be in the form of " + quarters + " quarters, " + dimes + " dimes, " + nickels + " nickels, and " + pennies + " pennies.");
        String input = io.readString("Would you like to make another purchase?");
        return input;
    }
    
    public String displayErrorMessage(Exception e) {
        String errorMessage = e.getMessage();
        io.print(errorMessage);
        return errorMessage;
    }
    
    public void displayExit() {
        io.print("Thanks for stopping by! Have a great day!");
    }
    
    public String tryExcalibur() {
        String answer = io.readString("What is your name?");
        return answer;
    }
    
    public void denyExcalibur() {
        io.print("You are not the chosen King! You shall be denied and hung for such treason!");
    }
    
    public void giveExcalibur() {
        io.print("Welcome your highness! Here is your legendary weapon. LONG LIVE THE KING!");
    }

}
