package com.jmc.mazebank.Models;

import javafx.beans.binding.ObjectExpression;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Client {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty payeeAddress;
    private final ObjectProperty<Account> checkingAccount;
    private final ObjectProperty<Account> savingsAccount;
    private final ObjectProperty<LocalDate> dateCreate;

    public Client(String fName, String lName, String pAddress, Account cAccount, Account sAccount, LocalDate date) {
        this.firstName = new SimpleStringProperty(this, "First Name", fName);
        this.lastName = new SimpleStringProperty(this, "Last Name", lName);
        this.payeeAddress = new SimpleStringProperty(this, "Payee Address", pAddress);
        this.checkingAccount = new SimpleObjectProperty<>(this, "Checking Account", cAccount);
        this.savingsAccount = new SimpleObjectProperty<>(this, "Savings Account", sAccount);
        this.dateCreate = new SimpleObjectProperty<>(this, "Date Created", date);
    }
    public StringProperty firstNameProperty() {
        return firstName;
    }
    public StringProperty lastNameProperty() {
        return lastName;
    }
    public StringProperty pAddressProperty() {
        return payeeAddress;
    }
    public ObjectProperty<Account> checkingAccountProperty() {
        return checkingAccount;
    }
    public ObjectProperty<Account> savingsAccountProperty() {
        return savingsAccount;
    }
    public ObjectProperty<LocalDate> dateProperty() {
        return dateCreate;
    }
}
