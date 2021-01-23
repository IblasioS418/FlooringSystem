/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.swgflooring.dao;

import com.is.swgflooring.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author ibby4
 */
public interface OrderDAO {
    void Create(LocalDate orderDate, Order order) throws PersistenceException;
    List<Order> ReadAll(LocalDate orderDate)throws PersistenceException;
    Order ReadByName(LocalDate orderDate, String customerName) throws PersistenceException;
    Order ReadById(LocalDate orderDate, Integer orderId) throws PersistenceException;
    void Update(LocalDate orderDate, Order order) throws PersistenceException;
    void Delete(LocalDate orderDate, Integer orderId) throws PersistenceException;
    void Save();
}
