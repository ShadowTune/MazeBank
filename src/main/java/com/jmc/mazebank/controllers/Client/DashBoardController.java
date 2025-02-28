package com.jmc.mazebank.controllers.Client;

import com.jmc.mazebank.Models.Model;
import com.jmc.mazebank.Models.Transaction;
import com.jmc.mazebank.Views.transactionCell;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import javax.naming.Binding;
import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {
    public Text user_name;
    public Label login_date;
    public Label balance;
    public Label create_acc_number;
    public Label save_balance;
    public Label save_acc_number;
    public Label income_lbl;
    public Label expense_lbl;
    public ListView transaction_listview;
    public TextField payee_fld;
    public TextField amount_fld;
    public TextArea message_fld;
    public Button send_money_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindData();
        initLatestTransactionsList();
        transaction_listview.setItems(Model.getInstance().getLatestTransactions());
        transaction_listview.setCellFactory(e -> new transactionCell());
        send_money_btn.setOnAction(event -> onSendMoney());
        accountSummary();
    }
    private void bindData() {
        user_name.textProperty().bind(Bindings.concat("Hi ").concat(Model.getInstance().getClient().firstNameProperty()));
        login_date.setText("Today, " + LocalDate.now());
        balance.textProperty().bind(Model.getInstance().getClient().checkingAccountProperty().get().balanceProperty().asString());
        create_acc_number.textProperty().bind(Model.getInstance().getClient().checkingAccountProperty().get().accountNumberProperty());
        save_balance.textProperty().bind(Model.getInstance().getClient().savingsAccountProperty().get().balanceProperty().asString());
        save_acc_number.textProperty().bind(Model.getInstance().getClient().savingsAccountProperty().get().accountNumberProperty());
    }
    private void initLatestTransactionsList() {
        if (Model.getInstance().getLatestTransactions().isEmpty()) {
            Model.getInstance().setLatestTransactions();
        }
    }
    private void onSendMoney() {
        String receiver = payee_fld.getText();
        double amount = Double.parseDouble(amount_fld.getText());
        String message = message_fld.getText();
        String sender = Model.getInstance().getClient().pAddressProperty().get();
        ResultSet resultSet = Model.getInstance().getDataBaseDriver().searchClient(receiver);
        try {
            if (resultSet.isBeforeFirst()) {
                Model.getInstance().getDataBaseDriver().updateBalance(receiver, amount, "ADD");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Model.getInstance().getDataBaseDriver().updateBalance(sender, amount, "SUB");
        Model.getInstance().getClient().savingsAccountProperty().get().setBalance(Model.getInstance().getDataBaseDriver().getSavingsBalance(sender));
        Model.getInstance().getDataBaseDriver().newTransaction(sender, receiver, amount, message);
        payee_fld.setText("");
        amount_fld.setText("");
        message_fld.setText("");
    }
    private void accountSummary() {
        double income = 0;
        double expenses = 0;
        if (Model.getInstance().getAllTransactions().isEmpty()) {
            Model.getInstance().setAllTransactions();
        }
        for (Transaction transaction: Model.getInstance().getAllTransactions()) {
            if (transaction.senderProperty().get().equals(Model.getInstance().getClient().pAddressProperty().get())) {
                expenses = expenses + transaction.amountProperty().get();
            } else {
                income = income + transaction.amountProperty().get();
            }
        }
        income_lbl.setText("+ $" + income);
        expense_lbl.setText("- $" + expenses);
    }
}
