/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package db_test;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


//import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


/**
 *
 * @author Hp
 */
public class FXMLDocumentController implements Initializable {
    
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    
    
    @FXML
    private Label label;
    
    @FXML
    private TableView<Products> tableview;
    @FXML
    private TableColumn<Products, String> tname;
    @FXML
    private TableColumn<Products, String> tuniqueId;
    @FXML
    private TableColumn<Products, String> tdescription;
    @FXML
    private TableColumn<Products, String> tquantity;
    
    @FXML
    private TextField uniqueIdE;

    @FXML
    private Button addItem;

    @FXML
    private TextField descriptionE;


    @FXML
    private TextField nameE;

    @FXML
    private TextField quantityE;
    
    @FXML
    private Button close;

    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
//        label.setText("Data inserted");
        connectToDatabaseF();
          
    }
    @FXML
    private void addItem(ActionEvent event){
        System.out.println("add button");
        connectToDatabaseI();
    }
    @FXML
    private void close(ActionEvent event){
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connectToDatabaseF();
    }    
     private void connectToDatabaseI() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD)) {
            // You can perform database operations here
            System.out.println("Connected to the database successfully!");
            
            insertSampleData(connection);
//            fetchAndDisplayData(connection);
            // Show a sample JavaFX alert
//            showAlert("Database Connection", "Connected to the database successfully!");
            

        } catch (SQLException e) {
            e.printStackTrace();
            showErrorAlert("Database Connection Error", "Failed to connect to the database. Check your credentials and database server.");
        }
    }
     
          private void connectToDatabaseF() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD)) {
            // You can perform database operations here
            System.out.println("Connected to the database successfully!");
//            insertSampleData(connection);
            fetchAndDisplayData(connection);
            // Show a sample JavaFX alert
//            showAlert("Database Connection", "Connected to the database successfully!");
            

        } catch (SQLException e) {
            e.printStackTrace();
            showErrorAlert("Database Connection Error", "Failed to connect to the database. Check your credentials and database server.");
        }
    }
     
    private void insertSampleData(Connection connection) {
        // Example: Insert data into a 'users' table
        String insertQuery = "INSERT INTO product_list (name, unique_id, description, quantity) VALUES (?, ?, ?, ?)";
        
        String ename = nameE.getText();
        String euniqueId = uniqueIdE.getText();
        String edescription = descriptionE.getText();
        String equantity = quantityE.getText();
        System.out.println(ename+euniqueId+edescription+equantity);
            
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            // Set values for the parameters (replace with your actual data)
            preparedStatement.setString(1, ename);
            preparedStatement.setString(2, euniqueId);
            preparedStatement.setString(3, edescription);
            preparedStatement.setString(4, equantity);
            
            nameE.clear();
            uniqueIdE.clear();
            descriptionE.clear();
            quantityE.clear();
            // Execute the query
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if the data was successfully inserted
            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully!");
            } else {
                System.out.println("Failed to insert data.");
            }
            connectToDatabaseF();
            
        } catch (SQLException e) {
            e.printStackTrace();
            showErrorAlert("Insert Data Error", "Failed to insert data into the database.");
        }
    }
    private void fetchAndDisplayData(Connection connection) {
        // Example: Fetch data from a 'users' table
        String selectQuery = "SELECT name, unique_id, description, quantity FROM product_list";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            tableview.getItems().clear();
            // Process the result set
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String uniqueId = resultSet.getString("unique_id");
                String description = resultSet.getString("description");
                String quantity = resultSet.getString("quantity");
                
                
                Products product = new Products(name, uniqueId, description, quantity);
                tableview.getItems().add(product);
                
                tname.setCellValueFactory(new PropertyValueFactory<>("name"));
                tuniqueId.setCellValueFactory(new PropertyValueFactory<>("uniqueId"));
                tdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
                tquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                
                // Display or process the fetched data (replace with your actual logic)
                System.out.println("Name: " + name +", UniqueId: " + uniqueId +", Description: " + description + ", Quantity: " + quantity);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showErrorAlert("Fetch Data Error", "Failed to fetch data from the database.");
        }
    }
    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
}
