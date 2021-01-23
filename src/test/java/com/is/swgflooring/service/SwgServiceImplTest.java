/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.swgflooring.service;

import com.is.swgflooring.dao.OrderDAO;
import com.is.swgflooring.dao.ProductDAO;
import com.is.swgflooring.dao.TaxDAO;
import com.is.swgflooring.dto.Order;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ibby4
 */
public class SwgServiceImplTest {
    
    private OrderDAO dao = new OrderDaoFileStubImpl();
    private TaxDAO taxDao = new TaxDaostubImpl();
    private ProductDAO productDao = new ProductDaostubImpl();
    
    public SwgServiceImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addOrder method, of class SwgServiceImpl.
     */
    @Test
    public void testAddOrder() throws Exception {
//        System.out.println("addOrder");
//        LocalDate orderDate =;
//        Order order = null;
//        SwgServiceImpl instance = null;
//        instance.addOrder(orderDate, order);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getallOrders method, of class SwgServiceImpl.
     */
    @Test
    public void testGetallOrders() throws Exception {
        System.out.println("getallOrders");
        LocalDate orderDate = null;
        SwgServiceImpl instance = null;
        List<Order> expResult = null;
        List<Order> result = instance.getallOrders(orderDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculate method, of class SwgServiceImpl.
     */
    @Test
    public void testCalculate() throws Exception {
        System.out.println("calculate");
        Order order = null;
        SwgServiceImpl instance = null;
        instance.calculate(order);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validate method, of class SwgServiceImpl.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        SwgServiceImpl instance = null;
//        instance.validate();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of viewOrder method, of class SwgServiceImpl.
     */
    @Test
    public void testViewOrder() throws Exception {
        System.out.println("viewOrder");
        LocalDate orderDate = null;
        Integer orderId = null;
        SwgServiceImpl instance = null;
        Order expResult = null;
        Order result = instance.viewOrder(orderDate, orderId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeOrder method, of class SwgServiceImpl.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        System.out.println("removeOrder");
        LocalDate orderDate = null;
        Integer orderId = null;
        SwgServiceImpl instance = null;
        instance.removeOrder(orderDate, orderId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
