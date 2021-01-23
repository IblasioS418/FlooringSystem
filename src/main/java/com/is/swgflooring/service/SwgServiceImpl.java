/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.swgflooring.service;

import com.is.swgflooring.dao.OrderDAO;
import com.is.swgflooring.dao.PersistenceException;
import com.is.swgflooring.dao.ProductDAO;
import com.is.swgflooring.dao.TaxDAO;
import com.is.swgflooring.dto.Order;
import com.is.swgflooring.dto.Product;
import com.is.swgflooring.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author ibby4
 */
public class SwgServiceImpl implements SwgService {

    OrderDAO orderDao;
    TaxDAO taxDao;
    ProductDAO productDao;

    public SwgServiceImpl(OrderDAO orderDao, TaxDAO taxDao, ProductDAO productDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.taxDao = taxDao;
    }

    @Override
    public void addOrder(LocalDate orderDate, Order order) throws PersistenceException, InvalidStateNameException, InvalidAreaException, InvalidProductNameException, InvalidCustomerNameException {
        String nameEntered = order.getCustomerName();
        char[] chars = nameEntered.toCharArray();
        for (char c : chars) {
            if (Character.isDigit(c)) {
             throw new InvalidCustomerNameException("Please enter a valid customer name");
            }
        } 
        if (order.getCustomerName() == null || order.getCustomerName().isBlank()) {
            throw new InvalidCustomerNameException("Please enter a valid customer name");
        }
        if ("Wood".equalsIgnoreCase(order.getProductType())
                || "Tile".equalsIgnoreCase(order.getProductType())
                || "Laminate".equalsIgnoreCase(order.getProductType())
                || "Carpet".equalsIgnoreCase(order.getProductType())) {
            order = calculate(order);
            orderDao.Create(orderDate, order);
        } else if (order.getProductType() == null) {
            throw new InvalidProductNameException("Invalid product Name");
        } else {
            throw new InvalidProductNameException("Invalid product Name");
        }

        if (order.getState().length() != 2) {
            throw new InvalidStateNameException("Invalid State");
        }
        

    }

    @Override
    public List<Order> getallOrders(LocalDate orderDate) throws PersistenceException, OrderNotFoundException {
        List<Order> orderList = orderDao.ReadAll(orderDate);
        if (orderList == null || orderList.isEmpty()) {
            throw new OrderNotFoundException("");
        }
        return orderList;
    }

    @Override
    public Order calculate(Order order) throws InvalidStateNameException, InvalidAreaException, InvalidProductNameException {
        Tax tax = taxDao.ReadByState(order.getState());
        order.setTaxRate(tax.getTaxRate());
        Product product = productDao.ReadByName(order.getProductType());
        order.setCostPerSqFt(product.getCostPerSqFt());
        order.setLaborCostPerSqFt(product.getLaborCostPerSqFt());
        order.setLaborCost(order.getLaborCostPerSqFt().multiply(order.getArea()));
        order.setMaterialCost(order.getCostPerSqFt().multiply(order.getArea()));
        order.setTax(order.getTaxRate().multiply(order.getArea()));
        order.setTotal(order.getLaborCost().add(order.getMaterialCost().add(order.getTax())));

        return order;
    }

    @Override
    public Order viewOrder(LocalDate orderDate, Integer orderId) throws OrderNotFoundException, PersistenceException {
        Order selectedOrder = orderDao.ReadById(orderDate, orderId);
        if (selectedOrder == null) {
            throw new OrderNotFoundException("Order Not Found");
        } else {
            return selectedOrder;
        }
    }

    @Override
    public void removeOrder(LocalDate orderDate, Integer orderId) throws OrderNotFoundException, PersistenceException {
        Order selectedOrder = orderDao.ReadById(orderDate, orderId);
        if (selectedOrder == null) {
            throw new OrderNotFoundException("Order Not Found");
        } else {
            orderDao.Delete(orderDate, selectedOrder.getOrderNumber());
        }
    }

    @Override
    public void Save() {
        orderDao.Save();
    }

    @Override
    public void update(LocalDate orderDate, Order newOrder) throws PersistenceException {
        int orderNum = newOrder.getOrderNumber();
        Order currentOrder;
        try {
            currentOrder = this.viewOrder(orderDate, orderNum);
            currentOrder.setCustomerName(newOrder.getCustomerName());
            if (newOrder.getCustomerName() == null || newOrder.getCustomerName().isBlank()) {
                throw new InvalidCustomerNameException("Please enter a valid customer name");
            }
            currentOrder.setProductType(newOrder.getProductType());
            if (newOrder.getProductType() == null || newOrder.getProductType() != "Wood"
                    || newOrder.getProductType() != "Tile" || newOrder.getProductType() != "Laminate"
                    || newOrder.getProductType() != "Carpet") {
                throw new InvalidProductNameException("Invalid product Name");

            }
            currentOrder.setArea(newOrder.getArea());
            currentOrder.setState(newOrder.getState());
            this.calculate(currentOrder);
        } catch (OrderNotFoundException ex) {
            System.out.println("Order not Found");
        } catch (InvalidStateNameException ex) {
            System.out.println("Invalid State Name");
        } catch (InvalidAreaException ex) {
            System.out.println("Invalid Area");
        } catch (InvalidProductNameException ex) {
            System.out.println("Invalid Product Name");
        } catch (InvalidCustomerNameException ex) {
            System.out.println("Please enter a valid customer name");
        }

    }
}
