package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.awt.event.KeyListener;

public class Controller {
    @FXML
    TextArea mainTextArea;
    @FXML
    TextField textField;

    public void btnOneClickAction() {
        String textFieldValue = textField.getText();
        mainTextArea.appendText(textFieldValue + "\n");
        textField.clear();
    }

    public void clearBtnClickAction() {
        mainTextArea.clear();
    }

    public void textFieldPressEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            String textFieldValue = textField.getText();
            mainTextArea.appendText(textFieldValue + "\n");
            textField.clear();
        }
    }
}
