package com.jmc.mazebank.controllers.Admin;

import com.jmc.mazebank.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;

public class CreateClientController implements Initializable {
    public TextField fname_fld;
    public TextField lname_fld;
    public TextField password_fld;
    public CheckBox pAddress_box;
    public Label pAddress_lbl;
    public CheckBox ch_acc_box;
    public TextField ch_amount_fld;
    public CheckBox sv_acc_box;
    public TextField sv_account_fld;
    public Button create_client_btn;
    public Label error_lbl;
    private String payeeAddress;
    private boolean createCheckingAccount = false;
    private boolean createSavingAccount = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        create_client_btn.setOnAction(event -> createClient());
        pAddress_box.selectedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) {
                payeeAddress = createPayeeAddress();
                onCreatePayeeAddress();
            }
        });
        ch_acc_box.selectedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) {
                createCheckingAccount = true;
            }
        });
        sv_acc_box.selectedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) {
                createSavingAccount = true;
            }
        });
    }
    private void createClient() {
        if (createCheckingAccount) {
            createAccount("Checking");
        } else if (createSavingAccount) {
            createAccount("Savings");
        }
        String fName = fname_fld.getText();
        String lName = lname_fld.getText();
        String password = password_fld.getText();
        Model.getInstance().getDataBaseDriver().createClient(fName, lName, payeeAddress, password, LocalDate.now());
        error_lbl.setStyle("-fx-text-fill: blue; -fx-font-size: 1.3em; -fx-font-weight: bold");
        error_lbl.setText("Client Created Successfully!"); emptyFields();
    }
    public void createAccount(String accountType) {
        double balance = Double.parseDouble(ch_amount_fld.getText());
        String firstSection = "3201";
        String lastSection = Integer.toString((new Random()).nextInt(9999) + 1000);
        String accountNumber = firstSection + lastSection;
        if (accountType.equals("Checking")) {
            Model.getInstance().getDataBaseDriver().createCheckingAccount(payeeAddress, accountNumber, 10, balance);
        } else {
            Model.getInstance().getDataBaseDriver().createSavingsAccount(payeeAddress, accountNumber, 2000, balance);
        }
    }
    private void onCreatePayeeAddress() {
        if (fname_fld.getText() != null && lname_fld.getText() != null) {
            pAddress_lbl.setText(payeeAddress);
        }
    }
    private String createPayeeAddress() {
        int id = Model.getInstance().getDataBaseDriver().getLastClientsID() + 1;
        char fChar = Character.toLowerCase(fname_fld.getText().charAt(0));
        return "@" + fChar + lname_fld.getText() + id;
    }
    private void emptyFields() {
        fname_fld.setText("");
        lname_fld.setText("");
        password_fld.setText("");
        pAddress_box.setSelected(false);
        pAddress_lbl.setText("");
        ch_acc_box.setSelected(false);
        ch_amount_fld.setText("");
        sv_acc_box.setSelected(false);
    }
 }
