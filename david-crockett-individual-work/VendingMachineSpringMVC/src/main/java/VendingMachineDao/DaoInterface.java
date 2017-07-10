/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineDao;

import VendingMachineModel.Item;
import com.sg.vendingmachinespringmvc.DataPersistanceException;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface DaoInterface {
    
    public List<Item> displayItems() throws DataPersistanceException;
    
    public Item getItem(int id) throws DataPersistanceException;
    
    public Item updateItem(Item item) throws DataPersistanceException;
    
}
