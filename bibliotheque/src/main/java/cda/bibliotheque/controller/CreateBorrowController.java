package cda.bibliotheque.controller;

import java.io.IOException;

import cda.bibliotheque.App;
import cda.bibliotheque.model.Book;
import cda.bibliotheque.model.Client;
import cda.bibliotheque.model.Borrow;
import cda.bibliotheque.dao.BorrowDAO;
import cda.bibliotheque.dao.BookDAO;
import cda.bibliotheque.dao.ClientDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

public class CreateBorrowController {

    @FXML
    private ChoiceBox<Book> inputBook;

    @FXML
    private ChoiceBox<Client> inputClient;

    @FXML
    private DatePicker inputEndDate;

    @FXML
    private DatePicker inputStartDate;


    private final BorrowDAO borrowDAO = new BorrowDAO();
    private final BookDAO bookDAO = new BookDAO();
    private final ClientDAO clientDAO = new ClientDAO();

    @FXML
    void submit(ActionEvent event) throws IOException {
        Borrow borrow = new Borrow();
        borrow.setBook(inputBook.getValue());
        borrow.setClient(inputClient.getValue());
        borrow.setStartDate(inputStartDate.getValue());
        borrow.setEndDate(inputEndDate.getValue());
        borrowDAO.addBorrow(borrow);
        App.setRoot("borrows");
    }

    @FXML
    void switchToBorrows(ActionEvent event) throws IOException{
        App.setRoot("borrows");
    }
}