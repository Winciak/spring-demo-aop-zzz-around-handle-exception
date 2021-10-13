package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterFinallyDemoApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

       List<Account> theAccounts = null;

       try {
           boolean tripWire = false;
            theAccounts = accountDAO.findAccounts(tripWire);
       } catch (Exception exc) {
           System.out.println("\n\nMain program caught exception: " + exc);
       }


       System.out.println("\n\nMain Program: AfterThrowingDemoApp");
       System.out.println("-------");

       System.out.println(theAccounts + "\n");

       context.close();
    }
}
