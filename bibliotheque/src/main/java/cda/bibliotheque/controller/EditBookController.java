package cda.bibliotheque.controller;

import java.io.IOException;
import java.util.List;

import java.sql.Date;
import cda.bibliotheque.App;
import cda.bibliotheque.dao.BookDAO;
import cda.bibliotheque.dao.AuthorDAO;
import cda.bibliotheque.dao.GenreDAO;
import cda.bibliotheque.model.Book;
import cda.bibliotheque.model.Author;
import cda.bibliotheque.model.Genre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class EditBookController {
    private final ObjectProperty<Book> book = new SimpleObjectProperty<>();
    private final BookDAO bookDAO = new BookDAO();
    private final AuthorDAO authorDAO = new AuthorDAO();
    private final GenreDAO genreDAO = new GenreDAO();

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


    @FXML
    void submit(ActionEvent event) throws IOException {
        Book newBook = book.get();
        newBook.setIsAvailable(inputIsAvailable.isSelected());
        newBook.setReleaseDate(inputReleaseDate.getValue());
        newBook.setTitle(inputTitle.getText());
        newBook.setAuthors(inputListAuthor.getSelectionModel().getSelectedItems());
        newBook.setGenres(inputListGenre.getSelectionModel().getSelectedItems());
        bookDAO.updateBook(newBook);
        App.setRoot("books");
    }

    @FXML
    public void initialize(){
        List<Author> authors = authorDAO.getAllAuthors();
        inputListAuthor.getItems().addAll(authors);
        inputListAuthor.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        List<Genre> genres = genreDAO.getAllGenre();
        inputListGenre.getItems().addAll(genres);
        inputListGenre.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        book.addListener((obs, oldBook, newBook) -> {
            if (newBook != null) {
                MultipleSelectionModel<Author> selectionModel = inputListAuthor.getSelectionModel();
                selectionModel.clearSelection();
                for (Author author : newBook.getAuthors()) {
                    int index = inputListAuthor.getItems().indexOf(author);
                    if (index >= 0) {
                        selectionModel.select(index);
                    }
                }
                MultipleSelectionModel<Genre> selectionModelGenre = inputListGenre.getSelectionModel();
                selectionModelGenre.clearSelection();
                for (Genre genre : newBook.getGenres()) {
                    int index = inputListGenre.getItems().indexOf(genre);
                    if (index >= 0) {
                        selectionModelGenre.select(index);
                    }
                }
                inputIsAvailable.setSelected(newBook.getIsAvailable());
                Date inputRD = newBook.getReleaseDate_Date();
                inputReleaseDate.setValue(inputRD.toLocalDate());
                inputTitle.setText(newBook.getTitle());
            }
        });
        
    }

    public EditBookController(){

    }

    public void setBook(Book book) {
        this.book.set(book);
    }

    @FXML
    private void switchToBooks() throws IOException{
        App.setRoot("books");
    }
}

