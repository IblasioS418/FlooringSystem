/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.swgflooring.controller;

import com.is.swgflooring.dao.OrderDAO;
import com.is.swgflooring.dao.PersistenceException;
import com.is.swgflooring.dto.Order;
import com.is.swgflooring.service.InvalidAreaException;
import com.is.swgflooring.service.InvalidCustomerNameException;
import com.is.swgflooring.service.InvalidProductNameException;
import com.is.swgflooring.service.InvalidStateNameException;
import com.is.swgflooring.service.OrderNotFoundException;
import com.is.swgflooring.service.SwgService;
import com.is.swgflooring.view.SwgView;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ibby4
 */
public class SwgController {

    LocalDate orderDate;
    Integer orderNumber;

    private SwgService service;
    OrderDAO dao;
    SwgView view;

    public SwgController(SwgService service, SwgView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;

        while (keepGoing) {
            try {
                menuSelection = displayMenuAndGetSelection();

                switch (menuSelection) {
                    case 1:
                        displayOrder();
                        break;
                    case 2:
                        addOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        save();
                        break;
                    case 6:
                        keepGoing = false;
                        exitMessage();
                        break;
                    default:
                        unknownCmd();
                }

            } catch (PersistenceException ex) {
                System.out.println("Error Invalid Try Again");
            } catch (OrderNotFoundException ex) {
                System.out.println("Order Not Found");
            } catch (InvalidStateNameException ex) {
                System.out.println("Invalid State Entered. Please Enter a Valid State");
            } catch (InvalidAreaException ex) {
                System.out.println("Invalid Area Entered. Please Enter a Valid Area");
            } catch (InvalidProductNameException ex) {
                System.out.println("Invalid Product Type Entered. Please Enter a Valid Product");
            } catch (InvalidCustomerNameException ex) {
                System.out.println("Invalid Customer Name, Please Enter a Valid Customer Name");
            }

        }

    }

    private int displayMenuAndGetSelection() {
        return view.displayMenuAndGetSelection();
    }

    private void addOrder() throws PersistenceException, InvalidStateNameException, InvalidAreaException, InvalidProductNameException, OrderNotFoundException, InvalidCustomerNameException {
        view.displayAddNewOrderBanner();

        Order newOrder = view.getOrderInfo();
        orderDate = view.getOrderDate();
        if (orderDate == null) {
            throw new PersistenceException("");
        }
        service.addOrder(orderDate, newOrder);

        view.displayOrderCreatedBanner();

    }

    private void displayOrder() throws OrderNotFoundException, PersistenceException {
        orderDate = view.getOrderDate();
        if (orderDate == null) {
            throw new PersistenceException("");
        }
        List<Order> orderList = service.getallOrders(orderDate);
        view.displayOrders(orderList);
    }

    private void removeOrder() throws OrderNotFoundException, PersistenceException {
        view.displayRemoveOrderBanner();
        orderDate = view.getOrderDate();
        if (orderDate == null) {
            throw new PersistenceException("");
        }
        orderNumber = view.getOrderId();
        service.removeOrder(orderDate, orderNumber);
        view.displayOrderRemoved();
    }

    private void editOrder() throws OrderNotFoundException, PersistenceException, InvalidStateNameException, InvalidAreaException, InvalidProductNameException {
        view.displayEditOrder();
        orderDate = view.getOrderDate();
        if (orderDate == null) {
            throw new PersistenceException("");
        }
        orderNumber = view.getOrderId();
        Order newOrder = new Order();
        Order currentOrder = service.viewOrder(orderDate, orderNumber);
        view.getNewOrderInfo(orderNumber, currentOrder);
        String confirmation = view.displayConfirmation();
        if (confirmation.equals("Y")) {
            service.update(orderDate, newOrder);
            System.out.println("Order Updated");
        } else {
            displayMenuAndGetSelection();
        }

    }

    private void unknownCmd() {
        view.displayUnknownCmdInfo();
    }

    private void exitMessage() {
        view.displayExit();
    }

    private void save() throws OrderNotFoundException, PersistenceException, InvalidStateNameException, InvalidProductNameException {
//       List<Order> orderList = service.getallOrders(orderDate);
//       service.validateList(orderList);
        service.Save();
        view.displayProgressSaved();

    }

}
