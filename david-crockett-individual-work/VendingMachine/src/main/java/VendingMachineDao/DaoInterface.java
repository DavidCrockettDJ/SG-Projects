/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineDao;

import VendingMachineDto.VendingMachineDto;
import java.util.ArrayList;

/**
 *
 * @author apprentice
 */
public interface DaoInterface {

    public ArrayList<VendingMachineDto> displayList() throws VendingMachineException;
    
    public VendingMachineDto dispenseItem(String key) throws NoItemInventoryException;
    
    public VendingMachineDto updateItem(VendingMachineDto item) throws VendingMachineException;
    
}
