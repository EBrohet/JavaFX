package cda.bibliotheque.controller;

import java.io.IOException;

import cda.bibliotheque.model.Client;
import cda.bibliotheque.dao.ClientDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import cda.bibliotheque.App;

import java.time.LocalDate;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ClientController {
    @FXML
    private TableView<Client> clientsTable;

    @FXML
    private TableColumn<Client, String> colEmail;

    @FXML
    private TableColumn<Client, String> colFirstName;

    @FXML
    private TableColumn<Client, String> colLastName;

    @FXML
    private TableColumn<Client, Void> colActions;

    private final ObservableList<Client> clientList = FXCollections.observableArrayList();
    private final ClientDAO clientDAO = new ClientDAO();

    @FXML
    public void initialize(){
        colFirstName.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getFirstName()));
        colLastName.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getLastName()));
        colEmail.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEmail()));

        colActions.setCellFactory(cell -> new TableCell<>(){
            private final Button buttonEdit = new Button("Modifier");
            private final Button buttonDelete = new Button("Supprimer");
            private final HBox box = new HBox(10, buttonEdit, buttonDelete);
            {
                buttonEdit.setOnAction(event -> {
                    int index = getIndex();
                    Client clientToEdit = clientsTable.getItems().get(index);
                    try {
                        FXMLLoader loader = new FXMLLoader(App.class.getResource("edit-client.fxml"));
                        Parent parent = loader.load();

                        EditClientController editClientController = loader.getController();
                        editClientController.setClient(clientToEdit);

                        App.getScene().setRoot(parent);
                    } catch (IOException e) {
                        System.err.println("Erreur lors de la création du bouton edit -> " + e.getMessage());
                    }
                });
                buttonDelete.setOnAction(event -> {
                    int index = getIndex();
                    Client clientToDelete = clientsTable.getItems().get(index);
                    clientDAO.deleteClient(clientToDelete.getId());
                    loadClients();
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

        loadClients();
    }

    private void loadClients(){
        clientList.setAll(clientDAO.getAllClients());
        clientsTable.setItems(clientList);
    }

    @FXML
    private void switchToCreate() throws IOException{
        App.setRoot("create-client");
    }

    @FXML
    private void switchToMain() throws IOException{
        App.setRoot("primary");
    }
}
