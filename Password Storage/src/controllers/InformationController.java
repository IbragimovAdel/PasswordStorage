package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;

import java.io.IOException;

public class InformationController {

    @FXML
    private Label rootLabel;

    @FXML
    private Label loginLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label descriptionLabel;

    public static String root,login,password,description;

    static Stage parentStage;
    @FXML
    private void onSettingClick() throws IOException {
        parentStage = MainController.getStage();
        Stage stage = parentStage;
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../fxml/settings.fxml")),480,180);
        stage.setScene(scene);
        stage.setResizable(false);
        parentStage = stage;
        stage.show();
    }

    public static Stage getStage(){
        return parentStage;
    }

    @FXML
    private void initialize(){
        rootLabel.setText(root);
        loginLabel.setText(login);
        passwordLabel.setText(password);
        descriptionLabel.setText(description);
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public static String getRoot() {
        return root;
    }

    public static String getLogin() {
        return login;
    }

    public static String getPassword() {
        return password;
    }

    public static String getDescription() {
        return description;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
