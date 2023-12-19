/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package db_test;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class LoginController implements Initializable {

    @FXML
    private TextField userName;
    @FXML
    private PasswordField passWord;
    @FXML
    private Button login;
    @FXML
    private Label labelS;
   
    @FXML
    private void login(ActionEvent event)throws IOException {
        if (event.getSource() == login) {
            String userNameN = userName.getText();
            String passwordN = passWord.getText();
            if (userNameN.equalsIgnoreCase("admin") && passwordN.equalsIgnoreCase("admin")) {
                Stage primaryStage =new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
                System.out.println("Login Successfully");
                Stage stage = (Stage) login.getScene().getWindow();
                stage.close();
                
            }else
                System.out.println("Unable to login.");
                labelS.setText("Wrong username or password");
            
        }
//        Stage primaryStage =new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//        Scene scene = new Scene(root);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//        Stage stage = (Stage) login.getScene().getWindow();
//        stage.close();
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
}
