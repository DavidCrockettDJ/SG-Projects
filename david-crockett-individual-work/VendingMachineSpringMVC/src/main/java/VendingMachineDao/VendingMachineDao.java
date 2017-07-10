/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineDao;

import VendingMachineModel.Item;
import com.sg.vendingmachinespringmvc.DataPersistanceException;
import java.io.File;
import java.io.FileNotFoundException;
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
public class VendingMachineDao implements DaoInterface {
    Item item = new Item();
    Map<Integer, Item> itemMap = new HashMap<>();
    private final String DELIMITER = "::";
    private final String INVENTORY = "Inventory.txt";
    List<Item> itemList = new ArrayList<>();
    
    private void loadInventory() throws DataPersistanceException{
        Scanner sc = null;
        try{
        sc = new Scanner(new File(getClass().getClassLoader().getResource(INVENTORY).getFile()));
        }catch (FileNotFoundException e) {
            throw new DataPersistanceException("ERROR: Could not load data from file.", e);
        }
        String currentLine;
        String[] currentToken = null;
        
        while(sc.hasNextLine()) {
            Item currentItem = new Item();
            currentLine = sc.nextLine();
            currentToken = currentLine.split(DELIMITER);
            int currentId = Integer.parseInt(currentToken[0]);
            int currentQuantity = Integer.parseInt(currentToken[3]);
            currentItem.setItemId(currentId);
            currentItem.setName(currentToken[1]);
            currentItem.setPrice(new BigDecimal(currentToken[2]));
            currentItem.setQuantity(currentQuantity);
            
            itemMap.put(currentItem.getItemId(), currentItem);
        }
        sc.close();
    }
    
    private void writeInventory() throws DataPersistanceException{
        PrintWriter write = null;
        try{
            write = new PrintWriter(new FileWriter(getClass().getClassLoader().getResource(INVENTORY).getFile()));
        }catch (IOException e) {
            throw new DataPersistanceException ("ERROR: Could not write data to file.", e);
        }
        List<Item> Inventory = new ArrayList<>(itemMap.values());
        for (Item currentItem : Inventory) {
            write.println(currentItem.getItemId() + DELIMITER +
                    currentItem.getName() + DELIMITER +
                    currentItem.getPrice() + DELIMITER +
                    currentItem.getQuantity());
            write.flush();
        }
        write.close();
    }

    @Override
    public List<Item> displayItems() throws DataPersistanceException{
        loadInventory();
        itemList = new ArrayList<>(itemMap.values());
        return itemList;
    }

    @Override
    public Item getItem(int id) throws DataPersistanceException{
        loadInventory();
        return itemMap.get(id);
    }

    @Override
    public Item updateItem(Item item) throws DataPersistanceException{
        item.setQuantity(item.getQuantity() - 1);
        itemMap.put(item.getItemId(), item);
        writeInventory();
        return item;
    }
    
}
