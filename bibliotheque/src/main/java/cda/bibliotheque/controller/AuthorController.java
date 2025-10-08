package cda.bibliotheque.controller;

import java.io.IOException;

import cda.bibliotheque.model.Author;
import cda.bibliotheque.dao.AuthorDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import cda.bibliotheque.App;

import java.time.LocalDate;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
// import javafx.stage.Stage;
// import javafx.scene.Node;
// import javafx.scene.Scene;

public class AuthorController {
    @FXML
    private TableView<Author> authorsTable;

    @FXML
    private TableColumn<Author, LocalDate> colBornAt;

    @FXML
    private TableColumn<Author, String> colFirstName;

    @FXML
    private TableColumn<Author, String> colLastName;

    @FXML
    private TableColumn<Author, Void> colActions;

    private final ObservableList<Author> authorList = FXCollections.observableArrayList();
    private final AuthorDAO authorDAO = new AuthorDAO();

    @FXML
    public void initialize(){
        colFirstName.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getFirstName()));
        colLastName.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getLastName()));
        colBornAt.setCellValueFactory(cell -> new SimpleObjectProperty<LocalDate>(cell.getValue().getBorn_at()));

        colActions.setCellFactory(cell -> new TableCell<>(){
            private final Button buttonEdit = new Button("Modifier");
            private final Button buttonDelete = new Button("Supprimer");
            private final HBox box = new HBox(10, buttonEdit, buttonDelete);
            {
                buttonEdit.setOnAction(event -> {
                    int index = getIndex();
                    Author authorToEdit = authorsTable.getItems().get(index);
                    try {
                        FXMLLoader loader = new FXMLLoader(App.class.getResource("edit-author.fxml"));
                        Parent parent = loader.load();

                        EditAuthorController editAuthorController = loader.getController();
                        editAuthorController.setAuthor(authorToEdit);

                        App.getScene().setRoot(parent);
                        // Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        // stage.setScene(new Scene(parent));
                        // stage.show();
                    } catch (IOException e) {
                        System.err.println("Erreur lors de la création du bouton edit ->" + e.getMessage());
                    }
                });
                buttonDelete.setOnAction(event -> {
                    int index = getIndex();
                    Author authorToDelete = authorsTable.getItems().get(index);
                    authorDAO.deleteAuthor(authorToDelete.getId());
                    loadAuthors();
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

        loadAuthors();
    }

    private void loadAuthors(){
        authorList.setAll(authorDAO.getAllAuthors());
        authorsTable.setItems(authorList);
    }

    @FXML
    private void switchToCreate() throws IOException{
        App.setRoot("create-author");
    }
}
