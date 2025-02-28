package com.jmc.mazebank.Views;

import com.jmc.mazebank.Models.Transaction;
import com.jmc.mazebank.controllers.Client.TransactionCell;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class transactionCell extends ListCell<Transaction> {
    @Override
    protected void updateItem(Transaction transaction, boolean empty) {
        super.updateItem(transaction, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Client/transactioncell.fxml"));
            TransactionCell controller = new TransactionCell(transaction);
            loader.setController(controller);
            setText(null);
            try {
                setGraphic(loader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
