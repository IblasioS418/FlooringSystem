/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.swgflooring.service;

/**
 *
 * @author ibby4
 */
public class InvalidProductNameException extends Exception {

    public InvalidProductNameException(String message) {
        super(message);
    }

    public InvalidProductNameException(String message, Throwable cause) {
        super(message, cause);
    }
    }
    
