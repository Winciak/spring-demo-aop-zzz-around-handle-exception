package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDAO {

    private String name;
    private String serviceCode;

    //NEW METHOD FINDACCOUNTS()

    public List<Account> findAccounts(boolean tripWire){

        if(tripWire){
            throw new RuntimeException("No soup for you xD niezła beka kursowa");
        }

        List<Account> myAccounts = new ArrayList<>();

        Account temp1 = new Account("John","Silver");
        Account temp2 = new Account("Madhu","Platinum");
        Account temp3 = new Account("Luca","Gold");

        myAccounts.add(temp1);
        myAccounts.add(temp2);
        myAccounts.add(temp3);

        return myAccounts;
    }

    public void addAccount(Account theAccount, boolean vipFlag){

        System.out.println(getClass() + ": DOING DB WORK :)");
    }

    public String getName() {
        System.out.println(getClass() + ": getName :)");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + ": setName :)");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + ": getCode :)");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + ": setCode :)");
        this.serviceCode = serviceCode;
    }
}
