package com.jmc.mazebank.Views;

import com.jmc.mazebank.controllers.Admin.AdminController;
import com.jmc.mazebank.controllers.Client.ClientController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class viewFactory {
    private accountType loginAccountType;
    private final ObjectProperty<clientMenu> clientSelectedMenuItem;
    private final ObjectProperty<adminMenu> adminSelectedMenuItem;
    private AnchorPane dashboardView;
    private AnchorPane accountsView;
    private AnchorPane transactionView;
    private AnchorPane createClientView;
    private AnchorPane depositView;
    private AnchorPane clientsView;
    public viewFactory() {
        this.loginAccountType = accountType.CLIENT;
        this.clientSelectedMenuItem = new SimpleObjectProperty<>();
        this.adminSelectedMenuItem = new SimpleObjectProperty<>();
    }

    public accountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(accountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    public ObjectProperty<clientMenu> getClientSelectedMenuItem() {
        return clientSelectedMenuItem;
    }

    public ObjectProperty<adminMenu> getAdminSelectedMenuItem() {
        return adminSelectedMenuItem;
    }
    public AnchorPane getDashBoardView() {
        if (dashboardView == null) {
            try {
                dashboardView = new FXMLLoader(getClass().getResource("/FXML/Client/dashboard.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dashboardView;
    }

    public AnchorPane getTransactionView() {
        if (transactionView == null) {
            try {
                transactionView = new FXMLLoader((getClass().getResource("/FXML/Client/transaction.fxml"))).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return transactionView;
    }

    public AnchorPane getAccountsView() {
        if (accountsView == null) {
            try {
                accountsView = new FXMLLoader(getClass().getResource("/FXML/Client/account.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return accountsView;
    }
    public AnchorPane getDepositView() {
        if (depositView == null) {
            try {
                depositView = new FXMLLoader(getClass().getResource("/FXML/Admin/deposit.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return depositView;
    }
    public void showLoginWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/login.fxml"));
        createStage(loader);
    }
    public void showMessageWindow(String pAddress, String messageText) {
        StackPane pane = new StackPane();
        HBox hBox = new HBox(5);
        hBox.setAlignment(Pos.CENTER);
        Label sender = new Label(pAddress);
        Label message = new Label(messageText);
        hBox.getChildren().addAll(sender, message);
        pane.getChildren().add(hBox);
        Scene scene = new Scene(pane,300, 100);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/bank.png"))));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Message");
        stage.setScene(scene);
        stage.show();
    }
    public AnchorPane getCreateClientView() {
        if (createClientView == null) {
            try {
                createClientView = new FXMLLoader(getClass().getResource("/FXML/Admin/createClient.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return createClientView;
    }
    public AnchorPane getClientsView() {
        if (clientsView == null) {
            try {
                clientsView = new FXMLLoader(getClass().getResource("/FXML/Admin/client.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return clientsView;
    }

    public void showClientWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Client/client.fxml"));
        ClientController clientcontrol = new ClientController();
        loader.setController(clientcontrol);
        createStage(loader);
    }
    public void showAdminWindow() {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Admin/admin.fxml"));
            AdminController control = new AdminController();
            loader.setController(control);
            createStage(loader);
    }
    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/bank.png"))));
        stage.setResizable(false);
        stage.setTitle("Maze Bank");
        stage.show();
    }
    public void closeStage(Stage stage) {
        stage.close();
    }
}
