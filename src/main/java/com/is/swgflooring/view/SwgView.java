/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.swgflooring.view;

import com.is.swgflooring.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author ibby4
 */
public class SwgView {

    UserIO io;

    public SwgView(UserIO io) {
        this.io = io;
    }

    public int displayMenuAndGetSelection() {
        io.print("SWG Flooring ");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Save Current Work");
        io.print("6. Quit");

        return io.readInt("Please select from the"
                + " above choices.", 1, 6);

    }

    public void displayOrders(List<Order> orderList) {

        for (Order it : orderList) {
            io.print("Order Number: " + it.getOrderNumber() + " "
                    + ", " + "Customer Name: " + it.getCustomerName()
                    + ", " + "State: " + it.getState()
                    + ", " + "Area: " + it.getArea()
                    + ", " + "Cost Per Sq Ft: " + it.getCostPerSqFt()
                    + ", " + "Labor Cost: " + it.getLaborCost()
                    + ", " + "Labor Cost Per Sq Ft: " + it.getLaborCostPerSqFt()
                    + ", " + "Tax: " + it.getTax()
                    + ", " + "Tax Rate: " + it.getTaxRate()
                    + ", " + "Material Cost: " + it.getMaterialCost()
                    + ", " + "Total Cost: " + it.getTotal());

        }

    }

    public String displayConfirmation() {
        return io.readString("Confirm? Y/N").toUpperCase();

    }

    public Order getOrderInfo() {
        Order order = new Order();
        order.setCustomerName(io.readString("Please Enter Customer Name"));
        order.setProductType(io.readString("Please Enter Product Type to be used"));
        order.setArea(io.readBigDecimal("Enter designated Area size"));
        order.setState(io.readString("Enter State"));
        return order;
    }

    public void displayUnknownCmdInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void displayExit() {
        io.print("GOOD BYE");
    }

    public void displayOrderS(List<Order> orderList) {
        io.print("=====> Order List <=====");
        for (Order it : orderList) {
            if (it != null) {
                io.print(it.getOrderNumber()
                        + " " + it.getCustomerName()
                        + " " + it.getState());
            } else {
                io.print(" Order Not Found ");
            }

            io.readString("Hit Enter to Continue");

        }
    }

    public LocalDate getOrderDate() {
        return io.readLocalDate("Please Enter Order Date to Proceed.");
    }

    public void displayAddNewOrderBanner() {
        io.print("===> Add New Order <===");
    }

    public void displayOrderCreatedBanner() {
        io.print("Order Successfully Created.");
    }

    public Integer getOrderId() {
        return io.readInt("Please Enter Order Number to Proceed.");
    }

    public void displayRemoveOrderBanner() {
        io.print("===> Remove an Order <===");
    }

    public void displayOrderRemoved() {
        io.print("Order Sucessfully Removed");
    }

    public void displayEditOrder() {
        io.print("===> Edit an Existing Order <===");
    }

    public void displayProgressSaved() {
        io.print("Progess has been successfully saved.");
    }

    public Order getNewOrderInfo(Integer orderNumber, Order order) {
        order.setOrderNumber(orderNumber);
        System.out.println(order.getOrderNumber());
        order.setCustomerName(io.readString("Enter Customer Name: Current Name (" + order.getCustomerName() + ")."));
        order.setProductType(io.readString("Enter Product Type: Current Product (" + order.getProductType() + ")." ));
        order.setArea(io.readBigDecimal("Enter designated Area size: Current Size (" + order.getArea() + ")."));
        order.setState(io.readString("Enter State: Current State (" + order.getState() + ")."));
        return order;
    }

}
