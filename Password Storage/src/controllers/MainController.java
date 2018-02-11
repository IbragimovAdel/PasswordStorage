package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.FileHandler;
import main.Main;
import main.Password;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainController {

    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    public ListView listView;

    private static ArrayList<Password> passwords;

    public static ArrayList<Password> getPasswords(){
        return passwords;
    }

    @FXML
    private void initialize() throws Exception {

        FileHandler fileHandler = new FileHandler();
        passwords = fileHandler.loadPasswords();

        for(int i=0;i<passwords.size();i++){
            listView.getItems().add(listView.getItems().size(),passwords.get(i).getLabel());
        }

    }

    static Stage addStage;

    @FXML
    private void onAddBtnClick() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../fxml/add.fxml")),480,180);
        stage.setTitle("Password Storage");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Main.getStage());
        addStage = stage;
        stage.showAndWait();
        listView.getItems().add(listView.getItems().size(),passwords.get(passwords.size()-1).getLabel());
    }

    static Stage getAddStage(){
        return addStage;
    }

    @FXML
    private void onDeleteBtnClick(){
        if(listView.getSelectionModel().getSelectedItems().size()>0) {
            listView.getItems().remove(listView.getSelectionModel().getSelectedIndex());
            passwords.remove(listView.getSelectionModel().getSelectedIndex()+1);
        }
    }


    private static Stage stage;
    private static int id;
    @FXML
    private void onInfoButtonClick() throws IOException {

        if(listView.getSelectionModel().getSelectedItems().size()==0) return;
        id = listView.getSelectionModel().getSelectedIndex();

        InformationController.root = (passwords.get(listView.getSelectionModel().getSelectedIndex()).getRoot());
        InformationController.password = (passwords.get(listView.getSelectionModel().getSelectedIndex()).getPassword());
        InformationController.login = (passwords.get(listView.getSelectionModel().getSelectedIndex()).getLogin());
        InformationController.description = (passwords.get(listView.getSelectionModel().getSelectedIndex()).getDescription());

        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../fxml/information.fxml")),480,180);
        stage.setTitle(listView.getSelectionModel().getSelectedItems().get(0).toString());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Main.getStage());

        this.stage = stage;
        stage.show();
    }

    public static int getID(){
        return id;
    }

    public static Stage getStage(){
        return stage;
    }



}
