/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineService;

import java.math.BigDecimal;
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
public class ChangeTest {
    Change change = new Change();
    public ChangeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getChange method, of class Change.
     */
    @Test
    public void testGetChange() {
        BigDecimal money = new BigDecimal("150");
        money = change.getChange(money);
        assertEquals(new BigDecimal("0.00"), money);
    }

    /**
     * Test of getQuarters method, of class Change.
     */
    @Test
    public void testGetQuarters() {
        BigDecimal money = new BigDecimal("0.67");
        change.getChange(money);
        assertEquals(2, change.getQuarters());
        assertEquals(1, change.getDimes());
        assertEquals(1, change.getNickels());
        assertEquals(2, change.getPennies());
    }
    
}
