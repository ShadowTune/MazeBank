package com.jmc.mazebank.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class SavingsAccount extends Account {
    private final DoubleProperty withdrawLimit;
    public SavingsAccount(String owner, String accountNumber, double balance, double withdrawLimit) {
        super(owner, accountNumber, balance);
        this.withdrawLimit = new SimpleDoubleProperty(this, "Withdraw Limit", withdrawLimit);
    }
    public DoubleProperty withdrawLimitProp() {
        return withdrawLimit;
    }

    @Override
    public String toString() {
        return accountNumberProperty().get();
    }
}
