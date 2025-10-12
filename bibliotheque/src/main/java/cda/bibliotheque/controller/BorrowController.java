package cda.bibliotheque.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import cda.bibliotheque.App;
import cda.bibliotheque.dao.BorrowDAO;
import cda.bibliotheque.model.Borrow;

import java.io.IOException;
import java.time.LocalDate;

public class BorrowController {
        @FXML
    private TableColumn<Borrow, Void> colActions;

    @FXML
    private TableColumn<Borrow, String> colBook;

    @FXML
    private TableColumn<Borrow, String> colClient;

    @FXML
    private TableColumn<Borrow, LocalDate> colEndDate;

    @FXML
    private TableColumn<Borrow, Boolean> colIsDone;

    @FXML
    private TableColumn<Borrow, LocalDate> colStartDate;

    @FXML
    private TableView<Borrow> borrowsTable;

    private final BorrowDAO borrowDAO = new BorrowDAO();
    private final ObservableList<Borrow> borrowList = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        colClient.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getClient().toString()));
        colBook.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getBook().getTitle()));
        colStartDate.setCellValueFactory(cell -> new SimpleObjectProperty<LocalDate>(cell.getValue().getStartDate()));
        colEndDate.setCellValueFactory(cell -> new SimpleObjectProperty<LocalDate>(cell.getValue().getEndDate()));
        colIsDone.setCellValueFactory(cell -> new SimpleBooleanProperty(cell.getValue().getIsDone()));

        colActions.setCellFactory(cell -> new TableCell<>(){
            private final Button buttonEdit = new Button("Modifier");
            private final Button buttonDelete = new Button("Supprimer");
            private final HBox box = new HBox(10, buttonEdit, buttonDelete);
            {
                buttonEdit.setOnAction(event -> {
                    int index = getIndex();
                    Borrow borrowToEdit = borrowsTable.getItems().get(index);
                    try {
                        FXMLLoader loader = new FXMLLoader(App.class.getResource("edit-borrow.fxml"));
                        Parent parent = loader.load();

                        EditBorrowController editBorrowController = loader.getController();
                        editBorrowController.setBorrow(borrowToEdit);

                        App.getScene().setRoot(parent);
                    } catch (IOException e) {
                        System.err.println("Erreur lors de la création du bouton edit -> " + e.getMessage());
                    }
                });
                buttonDelete.setOnAction(event -> {
                    int index = getIndex();
                    Borrow borrowToDelete = borrowsTable.getItems().get(index);
                    borrowDAO.deleteBorrow(borrowToDelete);
                    loadBorrows();
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

        loadBorrows();
    }

    private void loadBorrows(){
        borrowList.setAll(borrowDAO.getAllBorrow());
        borrowsTable.setItems(borrowList);
    }

    @FXML
    void switchToCreate(ActionEvent event) throws IOException {
        App.setRoot("create-borrow");

    }

    @FXML
    void switchToMain(ActionEvent event) throws IOException {
        App.setRoot("primary");

    }
}
