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
public class InvalidStateNameException extends Exception{
    public InvalidStateNameException(String message) {
        super(message);
    }

    public InvalidStateNameException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
