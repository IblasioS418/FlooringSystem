/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.swgflooring;

import com.is.swgflooring.controller.SwgController;
import com.is.swgflooring.dao.PersistenceException;
import com.is.swgflooring.service.InvalidAreaException;
import com.is.swgflooring.service.InvalidCustomerNameException;
import com.is.swgflooring.service.InvalidStateNameException;
import com.is.swgflooring.service.OrderNotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author ibby4
 */
public class App {
    public static void main(String[] args) throws InvalidAreaException , InvalidCustomerNameException , InvalidStateNameException , OrderNotFoundException , PersistenceException {       
    
    ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        SwgController controller
                = ctx.getBean("controller", SwgController.class);
        controller.run();
}
}
