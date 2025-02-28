package com.jmc.mazebank.controllers.Admin;

import com.jmc.mazebank.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    public Button create_client_btn;
    public Button client_btn;
    public Button deposit_btn;
    public Button logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }
    private void addListeners() {
        create_client_btn.setOnAction(event -> onCreateClient());
        client_btn.setOnAction(event -> onClients());
        deposit_btn.setOnAction(event -> onDeposit());
        logout_btn.setOnAction(event -> onLogout());
    }
    private void onCreateClient() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(com.jmc.mazebank.Views.adminMenu.CREATE_CLIENT);
    }
    private void onClients() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(com.jmc.mazebank.Views.adminMenu.CLIENTS);
    }
    private void onDeposit() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(com.jmc.mazebank.Views.adminMenu.DEPOSIT);
    }
    private void onLogout() {
        Stage stage = (Stage) client_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
        Model.getInstance().setAdminLoginSuccess(false);
    }
}
