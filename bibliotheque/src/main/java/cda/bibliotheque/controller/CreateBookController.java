package cda.bibliotheque.controller;

import java.io.IOException;

import cda.bibliotheque.App;
import cda.bibliotheque.model.Book;
import cda.bibliotheque.dao.BookDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CreateBookController {

    @FXML
    private TextField inputIsAvailable;

    @FXML
    private DatePicker inputReleaseDate;

    @FXML
    private TextField inputTitle;

    private final BookDAO bookDAO = new BookDAO();

    @FXML
    void submit(ActionEvent event) throws IOException {
        Book book = new Book();
        book.setIsAvailable(inputIsAvailable.getText());
        book.setReleaseDate(inputReleaseDate.getValue());
        book.setTitle(inputTitle.getText());
        bookDAO.addBook(book);
        App.setRoot("books");
    }
}