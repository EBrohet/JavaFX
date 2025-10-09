package cda.bibliotheque.controller;

import java.io.IOException;

import cda.bibliotheque.App;
import cda.bibliotheque.model.Client;
import cda.bibliotheque.dao.ClientDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateClientController {

    @FXML
    private TextField inputEmail;

    @FXML
    private TextField inputFirstName;

    @FXML
    private TextField inputLastName;

    private final ClientDAO clientDAO = new ClientDAO();

    @FXML
    void submit(ActionEvent event) throws IOException {
        Client client = new Client();
        client.setEmail(inputEmail.getText());
        client.setFirstName(inputFirstName.getText());
        client.setLastName(inputLastName.getText());
        clientDAO.addClient(client);
        App.setRoot("clients");
    }

    @FXML
    private void switchToClients() throws IOException{
        App.setRoot("clients");
    }
}