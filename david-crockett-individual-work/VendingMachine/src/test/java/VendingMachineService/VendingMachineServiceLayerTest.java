/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineService;

import VendingMachineDao.DaoInterface;
import VendingMachineDao.InsufficientFundsException;
import VendingMachineDao.VendingMachineDao;
import VendingMachineDao.VendingMachineDaoMock;
import VendingMachineDto.VendingMachineDto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class VendingMachineServiceLayerTest {
    VendingMachineServiceLayer service;
    public VendingMachineServiceLayerTest() {
//        DaoInterface dao = new VendingMachineDaoMock();
//        service = new VendingMachineServiceLayer(dao);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        DaoInterface dao = new VendingMachineDaoMock();
        service = new VendingMachineServiceLayer(dao);
        }
    
    
    @After
    public void tearDown() {
    }

    /**
     * Test of displayList method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testDisplayList() throws Exception {
        ArrayList<VendingMachineDto> itemList = service.displayList();
        for (VendingMachineDto currentItem : itemList) {
            if (currentItem.getNumOfItems() <= 0) {
                itemList.remove(currentItem);
            }
        }
        assertEquals(1, itemList.size());
    }

    /**
     * Test of dispenseItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testValidateMoney() {
       BigDecimal money = new BigDecimal("300");
       VendingMachineDto item = new VendingMachineDto();
       item.setPrice(new BigDecimal("400"));
       try {
       service.validateMoney(money, item);
       fail("Exception not thrown.");
       } catch (InsufficientFundsException e) {
           
       }
    }

    /**
     * Test of updateItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testUpdateItem() throws Exception {
        VendingMachineDto item = new VendingMachineDto();
        item.setNumOfItems(5);
        service.updateItem(item);
        assertEquals(4, item.getNumOfItems());
    }
    
}
