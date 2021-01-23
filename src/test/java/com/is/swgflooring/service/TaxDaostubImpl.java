/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.swgflooring.service;

import com.is.swgflooring.dao.Mapper;
import com.is.swgflooring.dao.TaxDAO;
import com.is.swgflooring.dao.TaxDAOFileImpl;
import com.is.swgflooring.dto.Tax;
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
public class TaxDaostubImpl implements TaxDAO {
 private String FILENAME;
    private String DELIMITER;
    private Map<String, Tax> taxList = new HashMap<>();

    public TaxDaostubImpl(String filename) {
        this.FILENAME = filename;
        this.load();
    }

    @Override
    public List<Tax> ReadAll() {
        return new ArrayList<>(taxList.values());
    }

    @Override
    public Tax ReadByState(String State) {
        return taxList.get(State);
    }

    private void load() {
        try {
            Scanner sc = new Scanner(
                    new BufferedReader(new FileReader(this.FILENAME)));
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                Tax selectedTax = Mapper.MapToTax(currentLine);
                taxList.put(selectedTax.getState(), selectedTax);

            }
            sc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TaxDAOFileImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public TaxDaostubImpl() {
    }
    
}
