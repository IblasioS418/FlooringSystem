/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.swgflooring.dao;

import com.is.swgflooring.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author ibby4
 */
public class OrderDaoFileImpl implements OrderDAO {

    private LocalDate orderDate;
    private String fileName;
    private String DELIMITER;
    private Map<LocalDate, Map<Integer, Order>> orderMap = new HashMap<>();

    @Override
    public void Create(LocalDate orderDate, Order order) throws PersistenceException {
        this.orderDate = orderDate;
        int counter = 0;
        Map<Integer, Order> orders = orderMap.get(orderDate);
        if (orders == null) {
            try {
                load();
                orders = orderMap.get(orderDate);
            } catch (PersistenceException e) {
                System.out.println("Order could not be loaded");
            }
        } else if (orders == null || orders.size() == 0) {
            orders = new HashMap<>();
        }

        counter = orders.size() + 1;
        order.setOrderNumber(counter);
        orders.put(order.getOrderNumber(), order);
        orderMap.put(orderDate, orders);
    }

    @Override
    public List<Order> ReadAll(LocalDate Date) throws PersistenceException {
        orderDate = Date;
        Map<Integer, Order> orders = orderMap.get(orderDate);

        if (orders == null) {
            try {
                load(); // if load every time it will overwrite what in the HashMap that does not save yet
                orders = orderMap.get(orderDate);
            } catch (PersistenceException e) {
                return null;
            }
        }
        return new ArrayList<>(orders.values());

    }

    @Override
    public Order ReadByName(LocalDate orderDate, String customerName) throws PersistenceException {
        this.orderDate = orderDate;
        List<Order> orderList = this.ReadAll(orderDate);
        Predicate<Order> checkName = (Order order) -> order.getCustomerName().equalsIgnoreCase(customerName);
        orderList.stream()
                .filter(checkName)
                .collect(Collectors.toList());
        return orderList.get(0);

    }

    @Override
    public Order ReadById(LocalDate orderDate, Integer orderId) throws PersistenceException {
        this.orderDate = orderDate;
        Map<Integer, Order> orders = orderMap.get(orderDate);
        if (orders == null) {
            try {
                load(); // if load every time it will overwrite what in the HashMap that does not save yet
                orders = orderMap.get(orderDate);
            } catch (PersistenceException e) {
                return null;
            }

        }
        Order order = orders.get(orderId);
        return order;

    }

    @Override
    public void Update(LocalDate orderDate, Order order) throws PersistenceException {
        int ordernum = order.getOrderNumber();
        Map<Integer, Order> orderList = orderMap.get(orderDate);
        if (orderList == null) {
            ReadAll(orderDate);
            order.setOrderNumber(ordernum);
            orderMap.put(orderDate, (Map<Integer, Order>) order);
        }
    }

    @Override
    public void Delete(LocalDate orderDate, Integer orderId) throws PersistenceException {
        Map<Integer, Order> orders = orderMap.get(orderDate);
        if (orders == null) {
            try {
                load(); // if load every time it will overwrite what in the HashMap that does not save yet
                orders = orderMap.get(orderDate);
            } catch (PersistenceException e) {
            }
        }
        orders.remove(orderId);
        orderMap.put(orderDate, (Map<Integer, Order>) orders);
    }

    @Override
    public void Save() {

        for (LocalDate date : orderMap.keySet()) {
            this.fileName = "orders/Orders_" + date.format(DateTimeFormatter.ofPattern("MMddyyyy")) + ".txt";
            try {
                PrintWriter out = new PrintWriter(new FileWriter(this.fileName));
                Map<Integer, Order> orders = orderMap.get(date);
                Collection<Order> orderList = orders.values();
                for (Order it : orderList) {
                    String orderAsText = Mapper.MapToString(it);
                    out.println(orderAsText);
                    out.flush();
                }
                out.close();
            } catch (IOException ex) {
                System.out.println("Order Could not be saved.");
            }
        }
    }

    private void load() throws PersistenceException {
        this.fileName = "orders/Orders_" + orderDate.format(DateTimeFormatter.ofPattern("MMddyyyy")) + ".txt";
        File dir = new File("orders");
        File[] FileList = dir.listFiles();

        for (File it : FileList) {
            //fileName = it.getName();
            String dateString = fileName.split("_")[1].split("[.]")[0];
            orderDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MMddyyyy"));

            Scanner sc;
            try {
                sc = new Scanner(
                        new BufferedReader(new FileReader(fileName)));

            } catch (FileNotFoundException ex) {
                throw new PersistenceException("File not Found");

            }
            Map<Integer, Order> orderList = new HashMap<>();

            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                Order selectedOrder = Mapper.MapToOrder(currentLine);
                orderList.put(selectedOrder.getOrderNumber(), selectedOrder);

            }
            sc.close();
            orderMap.put(orderDate, (Map<Integer, Order>) orderList);

        }

    }
}
