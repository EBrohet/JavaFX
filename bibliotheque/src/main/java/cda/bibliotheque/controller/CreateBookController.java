package cda.bibliotheque.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import cda.bibliotheque.App;
import cda.bibliotheque.model.Book;
import cda.bibliotheque.dao.BookDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CreateBookController {

    @FXML
    private CheckBox inputIsAvailable;

    @FXML
    private DatePicker inputReleaseDate;

    @FXML
    private TextField inputTitle;

    private final BookDAO bookDAO = new BookDAO();

    @FXML
    void submit(ActionEvent event) throws IOException {
        Book book = new Book();
        book.setIsAvailable(inputIsAvailable.isSelected());
        LocalDate inputRD = inputReleaseDate.getValue();
        if (inputRD != null) {
            Date sqlDate = Date.valueOf(inputRD);
            book.setReleaseDate(sqlDate);
        }
        book.setTitle(inputTitle.getText());
        bookDAO.addBook(book);
        App.setRoot("books");
    }

    @FXML
    private void switchToBooks() throws IOException{
        App.setRoot("books");
    }
}