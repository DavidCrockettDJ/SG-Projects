/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineService;

import VendingMachineDao.DaoInterface;
import VendingMachineDao.InsufficientFundsException;
import VendingMachineDao.NoItemInventoryException;
import VendingMachineDao.VendingMachineException;
import VendingMachineDto.VendingMachineDto;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author apprentice
 */
public class VendingMachineServiceLayer {

    private DaoInterface dao;
    private Change change = new Change();

    public VendingMachineServiceLayer(DaoInterface dao) {
        this.dao = dao;
    }

    public ArrayList<VendingMachineDto> displayList() throws VendingMachineException {
        return dao.displayList();
    }

    public VendingMachineDto dispenseItem(String key) throws NoItemInventoryException {
        return dao.dispenseItem(key);
    }

    public void validateMoney(BigDecimal money, VendingMachineDto item) throws InsufficientFundsException {
        BigDecimal userMoney = money;
        BigDecimal itemPrice = item.getPrice();
        if (userMoney.compareTo(itemPrice) < 0) {
            throw new InsufficientFundsException("ERROR: not enough money to complete transaction.");
        }
    }

    public VendingMachineDto updateItem(VendingMachineDto item) throws VendingMachineException {
        return dao.updateItem(item);
    }

}
