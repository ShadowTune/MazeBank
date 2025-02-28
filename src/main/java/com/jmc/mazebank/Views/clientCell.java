package com.jmc.mazebank.Views;

import com.jmc.mazebank.Models.Client;
import com.jmc.mazebank.controllers.Admin.ClientCellController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class clientCell extends ListCell<Client> {
    @Override
    protected void updateItem(Client client, boolean empty) {
        super.updateItem(client, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Admin/clientCell.fxml"));
            ClientCellController controller = new ClientCellController(client);
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
