package cda.bibliotheque.controller;

import java.io.IOException;

import cda.bibliotheque.App;
import cda.bibliotheque.dao.EditorDAO;
import cda.bibliotheque.model.Editor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class EditEditorController {
    private final ObjectProperty<Editor> editor = new SimpleObjectProperty<>();
    private final EditorDAO editorDAO = new EditorDAO();

    @FXML
    private DatePicker inputCreatedAt;

    @FXML
    private TextField inputLabel;

    @FXML
    void submit(ActionEvent event) throws IOException {
        Editor newEditor = editor.get();
        newEditor.setCreated_at(inputCreatedAt.getValue());
        newEditor.setLabel(inputLabel.getText());
        editorDAO.updateEditor(newEditor);
        App.setRoot("editors");
    }

    @FXML
    public void initialize(){
        editor.addListener((obs, oldEditor, newEditor) -> {
            if (newEditor != null) {
                inputCreatedAt.setValue(newEditor.getCreated_at());
                inputLabel.setText(newEditor.getLabel());
            }
        });
        
    }

    public EditEditorController(){

    }

    public void setEditor(Editor editor) {
        this.editor.set(editor);
    }

    @FXML
    private void switchToEditors() throws IOException{
        App.setRoot("editors");
    }
}
