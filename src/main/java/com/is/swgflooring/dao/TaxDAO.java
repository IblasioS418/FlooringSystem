/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.swgflooring.dao;

import com.is.swgflooring.dto.Tax;
import java.util.List;

/**
 *
 * @author ibby4
 */
public interface TaxDAO {
    List<Tax> ReadAll();
    Tax ReadByState(String State);
}
