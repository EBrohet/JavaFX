package cda.bibliotheque.controller;

import java.io.IOException;

import cda.bibliotheque.App;
import cda.bibliotheque.model.Genre;
import cda.bibliotheque.dao.GenreDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateGenreController {

    @FXML
    private TextField inputLabel;

    private final GenreDAO genreDAO = new GenreDAO();

    @FXML
    void submit(ActionEvent event) throws IOException {
        Genre genre = new Genre();
        genre.setLabel(inputLabel.getText());
        genreDAO.addGenre(genre);
        App.setRoot("genres");
    }

    @FXML
    private void switchToGenres() throws IOException{
        App.setRoot("genres");
    }
}