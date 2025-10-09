package cda.bibliotheque.controller;

import java.io.IOException;

import cda.bibliotheque.App;
import cda.bibliotheque.dao.ClientDAO;
import cda.bibliotheque.model.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class EditClientController {
    private final ObjectProperty<Client> client = new SimpleObjectProperty<>();
    private final ClientDAO clientDAO = new ClientDAO();

    @FXML
    private TextField inputEmail;

    @FXML
    private TextField inputFirstName;

    @FXML
    private TextField inputLastName;

    @FXML
    void submit(ActionEvent event) throws IOException {
        Client newClient = client.get();
        newClient.setEmail(inputEmail.getText());
        newClient.setFirstName(inputFirstName.getText());
        newClient.setLastName(inputLastName.getText());
        clientDAO.updateClient(newClient);
        App.setRoot("clients");
    }

    @FXML
    public void initialize(){
        client.addListener((obs, oldClient, newClient) -> {
            if (newClient != null) {
                inputEmail.setText(newClient.getEmail());
                inputFirstName.setText(newClient.getFirstName());
                inputLastName.setText(newClient.getLastName());
            }
        });
        
    }

    public EditClientController(){

    }

    public void setClient(Client client) {
        this.client.set(client);
    }

    @FXML
    private void switchToClients() throws IOException{
        App.setRoot("clients");
    }
}
