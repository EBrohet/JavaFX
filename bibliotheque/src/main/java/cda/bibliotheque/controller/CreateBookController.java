package cda.bibliotheque.controller;

import java.io.IOException;
import java.util.List;

import cda.bibliotheque.App;
import cda.bibliotheque.model.Book;
import cda.bibliotheque.model.Editor;
import cda.bibliotheque.model.Author;
import cda.bibliotheque.model.Genre;
import cda.bibliotheque.dao.BookDAO;
import cda.bibliotheque.dao.AuthorDAO;
import cda.bibliotheque.dao.GenreDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

public class CreateBookController {

    @FXML
    private CheckBox inputIsAvailable;

    @FXML
    private ListView<Author> inputListAuthor;

    @FXML
    private ListView<Genre> inputListGenre;

    @FXML
    private DatePicker inputReleaseDate;

    @FXML
    private TextField inputTitle;

    private final BookDAO bookDAO = new BookDAO();
    private final AuthorDAO authorDAO = new AuthorDAO();
    private final GenreDAO genreDAO = new GenreDAO();

    @FXML   
    public void initialize() {
        List<Author> authors = authorDAO.getAllAuthors();
        inputListAuthor.getItems().setAll(authors);
        inputListAuthor.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        List<Genre> genres = genreDAO.getAllGenre();
        inputListGenre.getItems().setAll(genres);
        inputListGenre.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    void submit(ActionEvent event) throws IOException {
        Book book = new Book(
            inputTitle.getText(),
            inputReleaseDate.getValue(),
            inputIsAvailable.isSelected(),
            inputListAuthor.getSelectionModel().getSelectedItems(),
            inputListGenre.getSelectionModel().getSelectedItems()
        );
        bookDAO.addBook(book);
        App.setRoot("books");
    }

    @FXML
    private void switchToBooks() throws IOException{
        App.setRoot("books");
    }
}