package com.jmc.mazebank.controllers.Client;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountController implements Initializable {
    public Label chk_acc_num;
    public Label transaction_limit;
    public Label acc_date;
    public Label acc_balance;
    public Label save_Acc_num;
    public Label withdraw_limit;
    public Label sv_acc_date;
    public Label sv_acc_balance;
    public TextField amount_to_sv;
    public Button trans_to_sv_btn;
    public TextField amount_to_chk;
    public Button trans_to_chk_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
