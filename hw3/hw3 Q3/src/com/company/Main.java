package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ArrayList<BankAccount> listOfAccounts = new ArrayList<>();
        BankAccount account1 = new BankAccount(5000, "Fereshta");
        listOfAccounts.add(account1);
        BankAccount account2 = new BankAccount(4000, "Joe");
        listOfAccounts.add(account2);
        BankAccount account3 = new BankAccount(11000, "Jack");
        listOfAccounts.add(account3);
        BankAccount account4 = new BankAccount(9000, "Sam");
        listOfAccounts.add(account4);
        System.out.println("Here is the list of accounts before sorted");
        for (BankAccount account : listOfAccounts){
            System.out.println(account.getName() + " : " +account.getBalance());
        }
        Collections.sort(listOfAccounts);
        System.out.println("Here is the list of accounts after sorted");
        for (BankAccount account : listOfAccounts){
            System.out.println(account.getName() + " : " +account.getBalance());
        }
    }
}
