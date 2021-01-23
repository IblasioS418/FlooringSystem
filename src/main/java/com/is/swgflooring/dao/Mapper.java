/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.swgflooring.dao;

import com.is.swgflooring.dto.Order;
import com.is.swgflooring.dto.Product;
import com.is.swgflooring.dto.Tax;
import java.math.BigDecimal;

/**
 *
 * @author ibby4
 */
public class Mapper {
    
    private final static String DELIMITER = ",";

    public static String MapToString(Order Order) {
        return Order.getOrderNumber() + DELIMITER
               + Order.getCustomerName() + DELIMITER
               + Order.getState() + DELIMITER
               + Order.getTaxRate() + DELIMITER
               + Order.getProductType() + DELIMITER
               + Order.getArea() + DELIMITER
               + Order.getCostPerSqFt() + DELIMITER
               + Order.getLaborCostPerSqFt() + DELIMITER
               + Order.getMaterialCost() + DELIMITER
               + Order.getLaborCost() + DELIMITER
               + Order.getTax() + DELIMITER
               + Order.getTotal();          
      
    }
    
    public static String MapToString(Product Product){
        return    Product.getProductType() + DELIMITER
                + Product.getCostPerSqFt() + DELIMITER
                + Product.getLaborCostPerSqFt();
        
    }
    
    public static String MapToString(Tax Tax){
        return Tax.getState() + DELIMITER
                + Tax.getTaxRate();
    }

    public static Order MapToOrder(String currentLine) {
        String[] orderData = currentLine.split(DELIMITER);
        Order Order = new Order();
        Order.setOrderNumber(Integer.parseInt(orderData[0]));
        Order.setCustomerName(orderData[1]);
        Order.setState(orderData[2]);
        Order.setTaxRate(new BigDecimal(orderData[3]));
        Order.setProductType(orderData[4]);
        Order.setArea(new BigDecimal(orderData[5]));
        Order.setCostPerSqFt(new BigDecimal(orderData[6]));
        Order.setLaborCostPerSqFt(new BigDecimal(orderData[7]));
        Order.setMaterialCost(new BigDecimal(orderData[8]));
        Order.setLaborCost(new BigDecimal(orderData[9]));
        Order.setTax(new BigDecimal(orderData[10]));
        Order.setTotal(new BigDecimal(orderData[11]));
        
        return Order;
    }
   public static Product MapToProduct(String currentLine){
        String[] productData = currentLine.split(DELIMITER);
        Product Product = new Product();
        Product.setProductType(productData[0]);
        Product.setCostPerSqFt(new BigDecimal(productData[1]));
        Product.setLaborCostPerSqFt(new BigDecimal(productData[2]));
        
        return Product;                       
    }
    public static Tax MapToTax (String currentLine) {
        String[] taxData = currentLine.split(DELIMITER);
        Tax Tax = new Tax();
        Tax.setState(taxData[0]);
        Tax.setTaxRate(new BigDecimal(taxData[1]));
        
        return Tax;
    }
    
}
