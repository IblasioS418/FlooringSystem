/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.swgflooring.service;

import com.is.swgflooring.dao.PersistenceException;
import com.is.swgflooring.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author ibby4
 */
public interface SwgService {

    void addOrder(LocalDate orderdate, Order order) throws PersistenceException, InvalidStateNameException, InvalidAreaException, InvalidProductNameException ,InvalidCustomerNameException;

    List<Order> getallOrders(LocalDate orderDate) throws OrderNotFoundException, PersistenceException; //InvalidDateException;

    Order calculate(Order order) throws PersistenceException, InvalidStateNameException, InvalidAreaException, InvalidProductNameException;

    Order viewOrder(LocalDate orderDate, Integer orderId) throws OrderNotFoundException, PersistenceException;

//    1void validateList(List<Order> orderList) throws InvalidStateNameException, InvalidProductNameException;

    public void removeOrder(LocalDate orderDate, Integer orderId) throws OrderNotFoundException, PersistenceException;
    
    public void Save();

    public void update(LocalDate orderDate, Order order) throws PersistenceException;
}
