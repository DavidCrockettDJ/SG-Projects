/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineService;

import VendingMachineDao.DaoInterface;
import VendingMachineModel.Change;
import VendingMachineModel.Item;
import com.sg.vendingmachinespringmvc.DataPersistanceException;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author apprentice
 */
public class VendingMachineService implements ServiceInterface{
    DaoInterface dao;
    Change change = new Change();
    
    @Inject
    public VendingMachineService(DaoInterface vendingMachineDao) {
        this.dao = vendingMachineDao;
    }

    @Override
    public List<Item> displayItems() throws DataPersistanceException {
        return dao.displayItems();
    }

    @Override
    public Item getItem(int id) throws DataPersistanceException {
        Item item = dao.getItem(id);
        return item;
    }

    @Override
    public Item updateItem(Item item) throws DataPersistanceException {
        return dao.updateItem(item);
    }

    @Override
    public boolean validateFunds(BigDecimal money, Item item) {
        if (money.compareTo(item.getPrice()) >= 0) {
            return true;
        }
        return false;
    }
    
    @Override
    public BigDecimal getChange(BigDecimal money) {
        return change.getChange(money);
    }

    @Override
    public int getQuarter() {
        return change.getQuarter();
    }

    @Override
    public int getDime() {
        return change.getDime();
    }

    @Override
    public int getNickel() {
        return change.getNickel();
    }

    @Override
    public int getPenny() {
        return change.getPenny();
    }
    
}
