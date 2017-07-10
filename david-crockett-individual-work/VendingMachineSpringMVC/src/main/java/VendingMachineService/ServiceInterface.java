/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineService;

import VendingMachineModel.Item;
import com.sg.vendingmachinespringmvc.DataPersistanceException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface ServiceInterface {
    
    public List<Item> displayItems() throws DataPersistanceException;
    
    public Item getItem(int id) throws DataPersistanceException;
    
    public Item updateItem(Item item) throws DataPersistanceException;
    
    public boolean validateFunds(BigDecimal money, Item item);
    
    public int getQuarter();
    
    public int getDime();
    
    public int getNickel();
    
    public int getPenny();
    
    public BigDecimal getChange(BigDecimal money);
    
}
