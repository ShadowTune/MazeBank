package com.jmc.mazebank.controllers.Admin;

import com.jmc.mazebank.Models.Client;
import com.jmc.mazebank.Models.Model;
import com.jmc.mazebank.Views.clientCell;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    public ListView<Client> clients_listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initClientsList();
        clients_listview.setItems(Model.getInstance().getClients());
        clients_listview.setCellFactory(e -> new clientCell());
    }
    private void initClientsList() {
        if (Model.getInstance().getClients().isEmpty()) {
            Model.getInstance().setClients();
        }
    }
}
