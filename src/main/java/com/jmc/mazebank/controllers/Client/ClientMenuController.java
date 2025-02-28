package com.jmc.mazebank.controllers.Client;

import com.jmc.mazebank.Models.Model;
import com.jmc.mazebank.Views.clientMenu;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {

    public Button dashboard_btn;
    public Button transaction_btn;
    public Button accounts_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button report_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }
    private void addListeners() {
        dashboard_btn.setOnAction(event ->  onDashboard());
        transaction_btn.setOnAction(event -> onTransaction());
        accounts_btn.setOnAction(event -> onAccounts());
        logout_btn.setOnAction(event -> onLogout());
    }

    private void onTransaction() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(clientMenu.TRANSACTIONS);
    }

    private void onDashboard() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(clientMenu.DASHBOARD);
    }
    private void onAccounts() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(clientMenu.ACCOUNTS);
    }

    private void onLogout() {
        Stage stage = (Stage) dashboard_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
        Model.getInstance().setClientLoginSuccess(false);
    }

}
