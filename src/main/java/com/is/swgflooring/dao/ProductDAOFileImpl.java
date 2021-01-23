/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.swgflooring.dao;


import com.is.swgflooring.dto.Order;
import com.is.swgflooring.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ibby4
 */
public class ProductDAOFileImpl implements ProductDAO {
    
    private String FILENAME;
    private String DELIMITER;
    private Map<String, Product> productList = new HashMap<>();

    public ProductDAOFileImpl(String filename) {
        this.FILENAME = filename;
        this.load();
    }

    @Override
    public List<Product> ReadAll() {        
        return new ArrayList<>(productList.values());
    }

    @Override
    public Product ReadByName(String productType) {
        return productList.get(productType);
    }
    
    private void load() {
        try {
            Scanner sc = new Scanner(
                    new BufferedReader(new FileReader(this.FILENAME)));
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                Product selectedProduct = Mapper.MapToProduct(currentLine);                
                productList.put(selectedProduct.getProductType(), selectedProduct);   
              
                
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TaxDAOFileImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
