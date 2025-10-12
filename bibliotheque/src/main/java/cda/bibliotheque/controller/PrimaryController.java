package cda.bibliotheque.controller;

import cda.bibliotheque.App;
import java.io.IOException;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;

public class PrimaryController {

    @FXML
    void switchToAuthors(ActionEvent event) throws IOException{
        App.setRoot("authors");
    }

    @FXML
    void switchToBooks(ActionEvent event) throws IOException{
        App.setRoot("books");
    }

    @FXML
    void switchToClients(ActionEvent event) throws IOException{
        App.setRoot("clients");
    }

    @FXML
    void switchToEditors(ActionEvent event) throws IOException{
        App.setRoot("editors");
    }

    @FXML
    void switchToGenres(ActionEvent event) throws IOException{
        App.setRoot("genres");
    }

    @FXML
    void switchToBorrows(ActionEvent event) throws IOException{
        App.setRoot("borrows");
    }

}

