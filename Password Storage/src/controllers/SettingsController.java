package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.Password;

import java.util.ArrayList;

public class SettingsController {

    @FXML
    private TextField rootTF;
    @FXML
    private TextField loginTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private TextField descriptionTF;

    private ArrayList<Password> passwords;
    private int id;

    @FXML
    private void initialize(){
        rootTF.setText(InformationController.getRoot());
        loginTF.setText(InformationController.getLogin());
        passwordTF.setText(InformationController.getPassword());
        descriptionTF.setText(InformationController.getDescription());
    }

    @FXML
    private void onSaveClick(){
        this.passwords = MainController.getPasswords();
        this.id = MainController.getID();

        if(!rootTF.getText().isEmpty()) passwords.get(id).setRoot(rootTF.getText());
        if(!loginTF.getText().isEmpty()) passwords.get(id).setLogin(loginTF.getText());
        if(!passwordTF.getText().isEmpty()) passwords.get(id).setPassword(passwordTF.getText());
        if(!descriptionTF.getText().isEmpty()) passwords.get(id).setDescription(descriptionTF.getText());

        InformationController.getStage().close();
    }
}
