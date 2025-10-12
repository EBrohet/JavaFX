package cda.bibliotheque.controller;

import java.io.IOException;

import cda.bibliotheque.App;
import cda.bibliotheque.dao.GenreDAO;
import cda.bibliotheque.model.Genre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class EditGenreController {
    private final ObjectProperty<Genre> genre = new SimpleObjectProperty<>();
    private final GenreDAO genreDAO = new GenreDAO();

    @FXML
    private TextField inputLabel;

    @FXML
    void submit(ActionEvent event) throws IOException {
        Genre newGenre = genre.get();
        newGenre.setLabel(inputLabel.getText());
        genreDAO.updateGenre(newGenre);
        App.setRoot("genres");
    }

    @FXML
    public void initialize(){
        genre.addListener((obs, oldGenre, newGenre) -> {
            if (newGenre != null) {
                inputLabel.setText(newGenre.getLabel());
            }
        });
        
    }

    public EditGenreController(){

    }

    public void setGenre(Genre genre) {
        this.genre.set(genre);
    }

    @FXML
    private void switchToGenres() throws IOException{
        App.setRoot("genres");
    }
}
