/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineDao;

import VendingMachineDto.VendingMachineDto;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class VendingMachineDao implements DaoInterface{
    private Map<String, VendingMachineDto> items = new HashMap<>();
    
    public static final String INVENTORY = "inventory.txt";
    
    public static final String DELIMITER = "::";
    
    List<VendingMachineDto> itemList = new ArrayList<>();
    
    private void loadInventory() throws VendingMachineException {
	    Scanner scanner;
            try {
	    scanner = new Scanner( new BufferedReader(new FileReader(INVENTORY)));
            } catch (FileNotFoundException e) {
                throw new VendingMachineException(
                        "Could not load file contents into memory.", e);
            }
            String currentLine;
            String[] currentTokens;
            
            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                currentTokens = currentLine.split(DELIMITER);
                int availableToken = Integer.parseInt(currentTokens[2]);
                VendingMachineDto currentItem = new VendingMachineDto();
                currentItem.setName(currentTokens[0]);
                currentItem.setPrice(new BigDecimal(currentTokens[1]));
                currentItem.setNumOfItems(availableToken);
                
                items.put(currentItem.getName(), currentItem);
                }
            
            scanner.close();
            
            }
    
    private void writeInventory() throws VendingMachineException {
        PrintWriter out;
        try {
	        out = new PrintWriter(new FileWriter(INVENTORY));
        } catch (IOException e) {
            throw new VendingMachineException(
            "Could not save inventory data.", e);
        }  
            List<VendingMachineDto> Inventory = new ArrayList<VendingMachineDto>(items.values());
	    for (VendingMachineDto currentItem : Inventory) {
                out.println(currentItem.getName() + DELIMITER 
	                + currentItem.getPrice() + DELIMITER
	                + currentItem.getNumOfItems());
                out.flush();
            }
            out.close();
    }
    @Override
    public ArrayList<VendingMachineDto> displayList() throws VendingMachineException {
        loadInventory();
        ArrayList<VendingMachineDto> itemList = new ArrayList<>(items.values());
        for (VendingMachineDto currentItem : itemList) {
            if (currentItem.getNumOfItems() <= 0) {
                itemList.remove(currentItem);
            }
        }
        return itemList;
    }
    
    @Override
    public VendingMachineDto dispenseItem(String key) throws NoItemInventoryException {
        if (!items.containsKey(key) || items.get(key).getNumOfItems() <= 0) {
            throw new NoItemInventoryException("ERROR: There is no such item in inventory.");
        } else {
        return items.get(key);
        }
    }
    @Override
    public VendingMachineDto updateItem(VendingMachineDto item) throws VendingMachineException {
        item.setNumOfItems(item.getNumOfItems() - 1);
        writeInventory();
        return item;
    }
    
}
