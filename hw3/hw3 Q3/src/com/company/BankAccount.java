package com.company;

public class BankAccount implements Comparable<BankAccount> {
    public BankAccount(int balance, String name){
        this.balance = balance;
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(BankAccount account) {
        if (balance < account.balance)
            return -1;
        if (balance > account.balance)
            return 1;
        return 0;
    }

    private int balance;
    private String name;
}
