/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorMasteryDao;

import FloorMasteryDto.Order;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class MockDaoTest {
    
    DaoInterface dao = new MockDao();
    Map<String, Map<Integer, Order>> orderFiles = new HashMap<>();
    Map<Integer, Order> orders = new HashMap<>();
    static LocalDateTime now = LocalDateTime.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");

    static String formatDateTime = now.format(formatter);
    
    public MockDaoTest() {
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
     * Test of addOrder method, of class MockDao.
     */
    @Test
    public void testAddOrder() throws Exception {
       Order order = new Order();
       order.setOrderNum(2);
       orderFiles.put(formatDateTime, new HashMap<>()); 
        Map<Integer, Order> orders = orderFiles.get(formatDateTime);
        orders.put(order.getOrderNum(), order);
        assertEquals(1, orderFiles.size());
        assertEquals(1, orders.size());
    }

    /**
     * Test of findRemoveOrder method, of class MockDao.
     */
    @Test
    public void testFindRemoveOrder() {
        Order order = new Order();
        orderFiles.put(formatDateTime, new HashMap<>()); 
        order.setOrderNum(3);
        Map<Integer, Order> orders = orderFiles.get(formatDateTime);
        orders.put(order.getOrderNum(), order);
        assertEquals(3, orders.get(order.getOrderNum()).getOrderNum());
    }

    /**
     * Test of removeOrder method, of class MockDao.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        Order order = new Order();
        orderFiles.put(formatDateTime, new HashMap<>()); 
        order.setOrderNum(3);
        Map<Integer, Order> orders = orderFiles.get(formatDateTime);
        orders.put(order.getOrderNum(), order);
        orders.remove(order.getOrderNum());
        assertEquals(0, orders.size());
    }
    
}
