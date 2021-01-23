/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.swgflooring.dao;

import com.is.swgflooring.dto.Order;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class OrderDaoFileImplTest {

    LocalDate orderDate = LocalDate.parse("01212020", DateTimeFormatter.ofPattern("MMddyyyy"));
    private OrderDAO dao = new OrderDaoFileImpl();

    public OrderDaoFileImplTest() {
    }
    File testFile = new File("orders/Orders_01212020.txt");
   

    @BeforeAll
    public static void setUpClass() {
          
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws PersistenceException {
//        testFile.delete();
      
       
//        
        
        try {
            if (testFile.createNewFile()) {
                System.out.println("File Created");
            } else {
                System.out.println("A File already Exists");
            }
        } catch (IOException ex) {
            Logger.getLogger(OrderDaoFileImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
//        List<Order> orderList = dao.ReadAll(orderDate);
//        for (Order order : orderList) {
//            dao.Delete(orderDate, order.getOrderNumber());
//        }
    }

    @AfterEach
    public void tearDown() {
        testFile.delete();

    }

    @Test
    public void testCreate() throws Exception {
        //ARRANGE        
        System.out.println("Create");
        this.setUp();
//        LocalDate orderDate = LocalDate.parse("01212020", DateTimeFormatter.ofPattern("MMddyyyy"));
        Order order1 = Mapper.MapToOrder("0,Will,OH,0,Wood,100.00,0,0,0,0,0,0");
        Order expResult = Mapper.MapToOrder("1,Will,OH,0,Wood,100.00,0,0,0,0,0,0");
        //ACT
//        OrderDaoFileImpl instance = new OrderDaoFileImpl(); 
        dao.Create(orderDate, order1);
        Order Result = dao.ReadById(orderDate, order1.getOrderNumber());
        //ASSERT
        assertEquals(expResult, Result);

        this.tearDown();

    }

    /**
     * Test of ReadAll method, of class OrderDaoFileImpl.
     */
    @Test
    public void testReadAll() throws Exception {
        //ARRANGE
        System.out.println("ReadAll");
        this.setUp();

//        LocalDate orderDate = LocalDate.parse("01112020", DateTimeFormatter.ofPattern("MMddyyyy"));
        Order order1 = Mapper.MapToOrder("0,Mark,OH,0,Wood,100.00,0,0,0,0,0,0");
        Order order2 = Mapper.MapToOrder("0,Mark,OH,0,Wood,100.00,0,0,0,0,0,0");
//        OrderDaoFileImpl instance = new OrderDaoFileImpl();
        dao.Create(orderDate, order1);
        dao.Create(orderDate, order2);
        //ACT
        List<Order> orders = dao.ReadAll(orderDate);
        //ASSERT
        assertEquals(2, orders.size());

        this.tearDown();
    }

    /**
     * Test of ReadByName method, of class OrderDaoFileImpl.
     */
    @Test
    public void testReadByName() throws Exception {
        //ARRANGE
        System.out.println("ReadByName");
        this.setUp();
//        LocalDate orderDate = LocalDate.parse("01112020", DateTimeFormatter.ofPattern("MMddyyyy"));
        Order order1 = Mapper.MapToOrder("0,Wise,OH,0,Wood,100.00,0,0,0,0,0,0");
        String customerName = "Wise";
//        OrderDaoFileImpl instance = new OrderDaoFileImpl();
        dao.Create(orderDate, order1);
        //ACT
        Order expResult = order1;
        Order Result = dao.ReadByName(orderDate, customerName);
        //ASSERT
        assertEquals(expResult, Result);

        this.tearDown();

    }

    /**
     * Test of ReadById method, of class OrderDaoFileImpl.
     */
    @Test
    public void testReadById() throws Exception {
        //ARRANGE
        System.out.println("ReadById");
        this.setUp();
//        OrderDaoFileImpl instance = new OrderDaoFileImpl();
//        LocalDate orderDate = LocalDate.parse("01112020", DateTimeFormatter.ofPattern("MMddyyyy"));
        Order order1 = Mapper.MapToOrder("0,Mark,OH,0,Wood,100.00,0,0,0,0,0,0");
        Order expResult = Mapper.MapToOrder("1,Mark,OH,0,Wood,100.00,0,0,0,0,0,0");
        Integer orderId = 1;
        dao.Create(orderDate, order1);

        //ACT       
        Order Result = dao.ReadById(orderDate, orderId);
        //ASSERT
        assertEquals(expResult, Result);

        this.tearDown();
    }

    /**
     * Test of Update method, of class OrderDaoFileImpl.
     */
    @Test
    public void testUpdate() throws Exception {
        //ARRANGE
        System.out.println("Update");
        this.setUp();
//        LocalDate orderDate = LocalDate.parse("01112020", DateTimeFormatter.ofPattern("MMddyyyy"));
        Order newOrder = Mapper.MapToOrder("0,Wise,OH,0,Wood,100.00,0,0,0,0,0,0");
//        OrderDaoFileImpl instance = new OrderDaoFileImpl();
        dao.Create(orderDate, newOrder);
        Order expResult = Mapper.MapToOrder("1,Wiz,OH,0,Wood,150.00,0,0,0,0,0,0");
        //ACT
        Order Result = dao.ReadById(orderDate, 1);
        Result.setCustomerName("Wiz");
        Result.setArea(new BigDecimal("150.00"));
        //ACT
        dao.Update(orderDate, Result);
        //ASSERT
        assertEquals(expResult, Result);

        this.tearDown();
    }

    /**
     * Test of Delete method, of class OrderDaoFileImpl.
     */
    @Test
    public void testDelete() throws Exception {
        //ARRANGE
        System.out.println("Delete");
        this.setUp();
//        LocalDate orderDate = LocalDate.parse("01112020", DateTimeFormatter.ofPattern("MMddyyyy"));
        Order order1 = Mapper.MapToOrder("0,Wise,OH,0,Wood,100.00,0,0,0,0,0,0");
        Integer orderId = 1;
//        OrderDaoFileImpl instance = new OrderDaoFileImpl();
        dao.Create(orderDate, order1);
        //ACT
        dao.Delete(orderDate, orderId);
        Integer expResult = 0;
        List<Order> orderList = dao.ReadAll(orderDate);
        Integer result = orderList.size();
        //ASSERT
        assertEquals(expResult, result);

        this.tearDown();
    }

    /**
     * Test of Save method, of class OrderDaoFileImpl.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("Save");
        this.setUp();
//        LocalDate orderDate = LocalDate.parse("01112020", DateTimeFormatter.ofPattern("MMddyyyy"));
        Order expResult = Mapper.MapToOrder("0,Wise,OH,0,Wood,100.00,0,0,0,0,0,0");
//        OrderDaoFileImpl instance = 
        dao.Create(orderDate, expResult);
        dao.Save();
        List<Order> orderList = dao.ReadAll(orderDate);
        Order Result = orderList.get(0);

        assertEquals(expResult, Result);

        this.tearDown();
    }

}
