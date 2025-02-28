package com.jmc.mazebank.controllers.Client;

import com.jmc.mazebank.Models.Model;
import com.jmc.mazebank.Models.Transaction;
import com.jmc.mazebank.Views.transactionCell;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionController implements Initializable {
    public ListView<Transaction> transaction_listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initAllTransactions();
        transaction_listview.setItems(Model.getInstance().getAllTransactions());
        transaction_listview.setCellFactory(e -> new transactionCell());
    }
    private void initAllTransactions() {
        if (Model.getInstance().getAllTransactions().isEmpty()) {
            Model.getInstance().setAllTransactions();
        }
    }
}
