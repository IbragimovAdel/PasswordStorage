package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage stage;

    public static Stage getStage(){
        return stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/loginForm.fxml"));
        primaryStage.setTitle("Password Storage login");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 400, 180));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
