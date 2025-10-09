package cda.bibliotheque.controller;

import cda.bibliotheque.App;
import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        // App.setRoot("authors");
        App.setRoot("books");
    }
}
