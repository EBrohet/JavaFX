package cda.bibliotheque.controller;

import java.io.IOException;
import java.util.List;

import cda.bibliotheque.dao.GenreDAO;
import cda.bibliotheque.model.Genre;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import cda.bibliotheque.App;

public class GenreController {
    
    @FXML
    private TableColumn<Genre, Void> colActions;

    @FXML
    private TableColumn<Genre, String> colLabel;

    @FXML
    private TableView<Genre> genresTable;

    private final ObservableList<Genre> genreList = FXCollections.observableArrayList();
    private final GenreDAO genreDAO = new GenreDAO();

    @FXML
    public void initialize(){
        colLabel.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getLabel()));
        colActions.setCellFactory(cell -> new TableCell<>(){
            private final Button buttonEdit = new Button("Modifier");
            private final Button buttonDelete = new Button("Supprimer");
            private final HBox box = new HBox(10, buttonEdit, buttonDelete);
            {
                buttonEdit.setOnAction(event -> {
                    int index = getIndex();
                    Genre genreToEdit = genresTable.getItems().get(index);
                    try {
                        FXMLLoader loader = new FXMLLoader(App.class.getResource("edit-genre.fxml"));
                        Parent parent = loader.load();

                        EditGenreController editGenreController = loader.getController();
                        editGenreController.setGenre(genreToEdit);

                        App.getScene().setRoot(parent);
                    } catch (IOException e) {
                        System.err.println("Erreur lors de la création du bouton edit -> " + e.getMessage());
                    }
                });
                buttonDelete.setOnAction(event -> {
                    int index = getIndex();
                    Genre genreToDelete = genresTable.getItems().get(index);
                    genreDAO.deleteGenre(genreToDelete.getId());
                    loadGenres();
                });
            } 

            @Override
            protected void updateItem(Void item, boolean empty){
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                }else{
                    setGraphic(box);
                }
            }
        });
        loadGenres();
        genresTable.setItems(genreList);
    }

    @FXML
    void switchToCreate(ActionEvent event) throws IOException{
        App.setRoot("create-genre");
    }

    @FXML
    void switchToMain(ActionEvent event) throws IOException{
        App.setRoot("primary");
    }

    private void loadGenres(){
        List<Genre> genres = genreDAO.getAllGenre();
        // this.
        genreList.setAll(genres);
        genresTable.setItems(genreList);
    }
}
