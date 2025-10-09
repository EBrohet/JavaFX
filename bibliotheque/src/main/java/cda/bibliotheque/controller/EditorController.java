package cda.bibliotheque.controller;

import java.io.IOException;

import cda.bibliotheque.model.Editor;
import cda.bibliotheque.dao.EditorDAO;
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
import java.sql.Date;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class EditorController {
    @FXML
    private TableView<Editor> editorsTable;

    @FXML
    private TableColumn<Editor, LocalDate> colCreatedAt;

    @FXML
    private TableColumn<Editor, String> colLabel;

    @FXML
    private TableColumn<Editor, Void> colActions;

    private final ObservableList<Editor> editorList = FXCollections.observableArrayList();
    private final EditorDAO editorDAO = new EditorDAO();

    @FXML
    public void initialize(){
        colLabel.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getLabel()));
        colCreatedAt.setCellValueFactory(cell -> new SimpleObjectProperty<LocalDate>(cell.getValue().getCreated_at()));

        colActions.setCellFactory(cell -> new TableCell<>(){
            private final Button buttonEdit = new Button("Modifier");
            private final Button buttonDelete = new Button("Supprimer");
            private final HBox box = new HBox(10, buttonEdit, buttonDelete);
            {
                buttonEdit.setOnAction(event -> {
                    int index = getIndex();
                    Editor editorToEdit = editorsTable.getItems().get(index);
                    try {
                        FXMLLoader loader = new FXMLLoader(App.class.getResource("edit-editor.fxml"));
                        Parent parent = loader.load();

                        EditEditorController editEditorController = loader.getController();
                        editEditorController.setEditor(editorToEdit);

                        App.getScene().setRoot(parent);
                    } catch (IOException e) {
                        System.err.println("Erreur lors de la création du bouton edit ->" + e.getMessage());
                    }
                });
                buttonDelete.setOnAction(event -> {
                    int index = getIndex();
                    Editor editorToDelete = editorsTable.getItems().get(index);
                    editorDAO.deleteEditor(editorToDelete.getId());
                    loadEditors();
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

        loadEditors();
    }

    private void loadEditors(){
        editorList.setAll(editorDAO.getAllEditors());
        editorsTable.setItems(editorList);
    }

    @FXML
    private void switchToCreate() throws IOException{
        App.setRoot("create-editor");
    }

    @FXML
    private void switchToMain() throws IOException{
        App.setRoot("primary");
    }
}
