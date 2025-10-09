package cda.bibliotheque.controller;

import cda.bibliotheque.App;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;

public class PrimaryController {

    @FXML
    void switchToAuthors(ActionEvent event) throws Exception{
        App.setRoot("authors");
    }

    @FXML
    void switchToBooks(ActionEvent event) throws Exception{
        App.setRoot("books");
    }

    @FXML
    void switchToClients(ActionEvent event) throws Exception{
        App.setRoot("clients");
    }

    @FXML
    void switchToEditors(ActionEvent event) throws Exception{
        App.setRoot("Editors");
    }

}

