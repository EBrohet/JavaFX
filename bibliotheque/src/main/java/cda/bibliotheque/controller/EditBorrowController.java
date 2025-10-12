package cda.bibliotheque.controller;

import java.io.IOException;

import cda.bibliotheque.App;
import cda.bibliotheque.dao.BorrowDAO;
import cda.bibliotheque.model.Book;
import cda.bibliotheque.model.Borrow;
import cda.bibliotheque.model.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class EditBorrowController {
    private final ObjectProperty<Borrow> borrow = new SimpleObjectProperty<>();
    private final BorrowDAO borrowDAO = new BorrowDAO();

    @FXML
    private ChoiceBox<Book> inputBook;

    @FXML
    private ChoiceBox<Client> inputClient;

    @FXML
    private DatePicker inputEndDate;

    @FXML
    private DatePicker inputStartDate;

    @FXML
    void submit(ActionEvent event) throws IOException {
        Borrow newBorrow = borrow.get();
        newBorrow.setBook(inputBook.getValue());
        newBorrow.setClient(inputClient.getValue());
        newBorrow.setStartDate(inputStartDate.getValue());
        newBorrow.setEndDate(inputEndDate.getValue());
        borrowDAO.updateBorrow(newBorrow);
        App.setRoot("borrows");
    }

    @FXML
    public void initialize(){
        borrow.addListener((obs, oldBorrow, newBorrow) -> {
            if (newBorrow != null) {
                inputBook.setValue(newBorrow.getBook());
                inputClient.setValue(newBorrow.getClient());
                inputStartDate.setValue(newBorrow.getStartDate());
                inputEndDate.setValue(newBorrow.getEndDate());
            }
        });
        
    }

    public EditBorrowController(){

    }

    public void setBorrow(Borrow borrow) {
        this.borrow.set(borrow);
    }

    @FXML
    private void switchToBorrows() throws IOException{
        App.setRoot("borrows");
    }
}

