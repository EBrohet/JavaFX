package cda.bibliotheque.controller;

import java.io.IOException;

import cda.bibliotheque.App;
import cda.bibliotheque.model.Editor;
import cda.bibliotheque.dao.EditorDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CreateEditorController {

    @FXML
    private DatePicker inputCreatedAt;

    @FXML
    private TextField inputLabel;

    private final EditorDAO editorDAO = new EditorDAO();

    @FXML
    void submit(ActionEvent event) throws IOException {
        Editor editor = new Editor();
        editor.setCreated_at(inputCreatedAt.getValue());
        editor.setLabel(inputLabel.getText());
        editorDAO.addEditor(editor);
        App.setRoot("editors");
    }

    @FXML
    private void switchToEditors() throws IOException{
        App.setRoot("editors");
    }
}