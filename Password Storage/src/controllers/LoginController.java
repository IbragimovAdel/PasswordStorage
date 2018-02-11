package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.FileHandler;
import main.Main;

import java.io.IOException;
import java.security.MessageDigest;

public class LoginController {

    private static String KEY;
    private static String LOGIN;
    private static String PASSWORD;

    @FXML
    private Button loginBtn;

    @FXML
    private TextField usernameTF;

    @FXML
    private PasswordField passwordTF;

    @FXML
    private Label incorrectLogin;

    @FXML PasswordField keyTF;

    private FileHandler fileHandler;

    public static String getKEY() {
        return KEY;
    }

    private String encKEY;

    @FXML
    private void initialize() throws IOException {
        fileHandler = new FileHandler();
        String[] data = fileHandler.loadAccountData();
        LOGIN = data[0];
        PASSWORD = data[1];
        encKEY = data[2];
    }


    @FXML
    private void onBtnClick() throws Exception {

        String password = passwordTF.getText();
        password = encryptMD5(password);

        String login = usernameTF.getText();

        if(login.equals(LOGIN)&&password.equals(PASSWORD)){
            KEY=keyTF.getText();
            if(keyTF.getText().length()==16&&encryptMD5(KEY).equals(encKEY)) {
                Stage stage = Main.getStage();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../fxml/main.fxml")), 380, 400));
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        try {
                            fileHandler.savePasswords();
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
                stage.show();

            } else {
                incorrectLogin.setText("Неверный ключ.");
                incorrectLogin.setVisible(true);
            }
        }
        else {
            incorrectLogin.setText("Неверное имя пользователя или пароль.");
            incorrectLogin.setVisible(true);
        }
    }

    public String encryptMD5(String s) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] bytes = messageDigest.digest(s.getBytes());
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<bytes.length;i++){
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

}
