package cda.bibliotheque.controller;

import java.io.IOException;

// import java.time.ZoneId;
import java.time.LocalDate;
import java.sql.Date;
import cda.bibliotheque.App;
import cda.bibliotheque.dao.BookDAO;
import cda.bibliotheque.model.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class EditBookController {
    private final ObjectProperty<Book> book = new SimpleObjectProperty<>();
    private final BookDAO bookDAO = new BookDAO();

    @FXML
    private CheckBox inputIsAvailable;

    @FXML
    private DatePicker inputReleaseDate;

    @FXML
    private TextField inputTitle;

    @FXML
    void submit(ActionEvent event) throws IOException {
        Book newBook = book.get();
        newBook.setIsAvailable(inputIsAvailable.isSelected());
        LocalDate inputRD = inputReleaseDate.getValue();
        if (inputRD != null) {
            Date sqlDate = Date.valueOf(inputRD);
            newBook.setReleaseDate(sqlDate);
        }
        // newBook.setReleaseDate(Date.from(inputRD.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        newBook.setTitle(inputTitle.getText());
        bookDAO.updateBook(newBook);
        App.setRoot("books");
    }

    @FXML
    public void initialize(){
        book.addListener((obs, oldBook, newBook) -> {
            if (newBook != null) {
                inputIsAvailable.setSelected(newBook.getIsAvailable());
                Date inputRD = newBook.getReleaseDate();
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

