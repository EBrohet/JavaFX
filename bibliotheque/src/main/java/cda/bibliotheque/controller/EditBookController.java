package cda.bibliotheque.controller;

import java.io.IOException;

import cda.bibliotheque.App;
import cda.bibliotheque.dao.BookDAO;
import cda.bibliotheque.model.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class EditBookController {
    private final ObjectProperty<Book> book = new SimpleObjectProperty<>();
    private final BookDAO bookDAO = new BookDAO();

    @FXML
    private TextField inputIsAvailable;

    @FXML
    private DatePicker inputReleaseDate;

    @FXML
    private TextField inputTitle;

    @FXML
    void submit(ActionEvent event) throws IOException {
        Book newBook = book.get();
        newBook.setIsAvailable(inputIsAvailable.getText());
        newBook.setReleaeDate(inputReleaseDate.getValue());
        newBook.setTitle(inputTitle.getText());
        bookDAO.updateBook(newBook);
        App.setRoot("books");
    }

    @FXML
    public void initialize(){
        book.addListener((obs, oldBook, newBook) -> {
            if (newBook != null) {
                inputIsAvailable.setText(newBook.getIsAvailable());
                inputReleaseDate.setValue(newBook.getReleaseDate());
                inputTitle.setText(newBook.getTitle());
            }
        });
        
    }

    public EditBookController(){

    }

    public void setBook(Book book) {
        this.book.set(book);
    }
}

