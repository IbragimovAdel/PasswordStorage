package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.Password;

import java.util.ArrayList;

public class AddController {
    @FXML
    private TextField rootTF;
    @FXML
    private TextField loginTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private TextField descriptionTF;

    private String root,login,password,description;
    private ArrayList<Password> passwords;

    @FXML
    private void onSaveClick(){
        passwords=MainController.getPasswords();
        root = rootTF.getText();
        login = loginTF.getText();
        password = passwordTF.getText();
        description = descriptionTF.getText();

        if(!root.isEmpty() && !login.isEmpty() && !password.isEmpty() && !description.isEmpty()){
            Password pass = new Password(root, login, password, root, description);
            passwords.add(pass);
            MainController.getAddStage().close();
        } else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Ошибка!");
            alert.setContentText("Все поля должны быть заполнены!");
            alert.showAndWait();
        }
    }

}
