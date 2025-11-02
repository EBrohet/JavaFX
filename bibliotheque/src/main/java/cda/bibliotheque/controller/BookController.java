package cda.bibliotheque.controller;

import java.io.IOException;

import cda.bibliotheque.model.Book;
import cda.bibliotheque.dao.BookDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import cda.bibliotheque.App;

import java.sql.Date;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class BookController {
    @FXML
    private TableView<Book> booksTable;

    @FXML
    private TableColumn<Book, Boolean> colIsAvailable;

    @FXML
    private TableColumn<Book, String> colAuthors;

    @FXML
    private TableColumn<Book, String> colGenres;

    @FXML
    private TableColumn<Book, Date> colReleaseDate;

    @FXML
    private TableColumn<Book, String> colTitle;

    @FXML
    private TableColumn<Book, Void> colActions;

    private final ObservableList<Book> bookList = FXCollections.observableArrayList();
    private final BookDAO bookDAO = new BookDAO();

    @FXML
    public void initialize(){
        colTitle.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTitle()));
        colReleaseDate.setCellValueFactory(cell -> new SimpleObjectProperty<Date>(cell.getValue().getReleaseDate_Date()));
        colIsAvailable.setCellValueFactory(cell -> new SimpleBooleanProperty(cell.getValue().getIsAvailable()));
        colAuthors.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().toStringAuthors()));
        colGenres.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().toStringGenres()));

        colActions.setCellFactory(cell -> new TableCell<>(){
            private final Button buttonEdit = new Button("Modifier");
            private final Button buttonDelete = new Button("Supprimer");
            private final HBox box = new HBox(10, buttonEdit, buttonDelete);
            {
                buttonEdit.setOnAction(event -> {
                    int index = getIndex();
                    Book bookToEdit = booksTable.getItems().get(index);
                    try {
                        FXMLLoader loader = new FXMLLoader(App.class.getResource("edit-book.fxml"));
                        Parent parent = loader.load();

                        EditBookController editBookController = loader.getController();
                        editBookController.setBook(bookToEdit);

                        App.getScene().setRoot(parent);
                    } catch (IOException e) {
                        System.err.println("Erreur lors de la création du bouton edit -> " + e.getMessage());
                    }
                });
                buttonDelete.setOnAction(event -> {
                    int index = getIndex();
                    Book bookToDelete = booksTable.getItems().get(index);
                    bookDAO.deleteBook(bookToDelete.getId());
                    loadBooks();
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

        loadBooks();
    }

    private void loadBooks(){
        bookList.setAll(bookDAO.getAllBooks());
        booksTable.setItems(bookList);
    }

    @FXML
    private void switchToCreate() throws IOException{
        App.setRoot("create-book");
    }

    @FXML
    private void switchToMain() throws IOException{
        App.setRoot("primary");
    }
}
