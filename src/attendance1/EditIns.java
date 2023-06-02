/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance1;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author hp
 */
public class EditIns implements Initializable {

    @FXML
    private Label l;

    @FXML
    private TextField phone;
    @FXML
    private TextField email;

    @FXML
    private TextField password;
    @FXML
    private ComboBox<String> name;
    Navigation nav = new Navigation();
    @FXML
    public AnchorPane pane7;
    DBModel db = new DBModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setItems(FXCollections.observableArrayList(db.getInstructorNames()));
    }

    public void nav_select() {
           db.connect();
        String selectedInfo = name.getValue();
//instructors (email, name, phone, join_date, password)
        try {
         
            // Prepare the SQL query
            String sqlQuery = "SELECT email, phone, password FROM instructors  WHERE name = ?";
            PreparedStatement statement = db.con.prepareStatement(sqlQuery);
            statement.setString(1, selectedInfo);
            /*-----------------------------------------------------------------------------------------------------------*/
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String data1 = resultSet.getString("email");
                String data2 = resultSet.getString("phone");
                String data3 = resultSet.getString("password");
                System.out.println(data1 + " " + data2 + " " + data3);
                email.setText(data1);
                phone.setText(data2);
                password.setText(data3);

            }
            /*-----------------------------------------------------------------------------------------------------------*/

            resultSet.close();
            statement.close();
            db.con.close();
        } catch (SQLException e) {
        }
    }

    public void nav_editCourse() {
         nav.navTo(pane7, nav.fxmleditCourse);

    }

    public void nav_insertC() {
       nav.navTo(pane7, nav.fxmlCtreateCourse);
    }
    public void nav_delete(){
        String selectedInfo =name.getValue();
        db.connect();
         String sqlQuery = "DELETE FROM instructors where name = ?";
        try {
            // Prepare the SQL query
           
            PreparedStatement statement = db.con.prepareStatement(sqlQuery);
            statement.setString(1, selectedInfo);
            // Execute the query
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                // Record deleted successfully
                l.setText("Record deleted successfully.");
            } else {
                // No rows affected, record not found or deletion failed
                l.setText("Failed to delete record.");
            }

            // Close the statement
            statement.close();
        } catch (SQLException e) {
            showErrorAlert("An error occurred while deleting data.");
        e.printStackTrace();
           
        } finally {
            // Close the database connection
            try {
                db.con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }
    public void nav_edit(){
        String selectedInfo = name.getValue();
        String updatedValue1 = email.getText();
        String updatedValue2 = phone.getText();
        String updatedValue3 = password.getText();
       db.connect();
       
        String updateQuery = "UPDATE instructors  SET email = ?, phone = ?, password  = ? WHERE name = ?";

        try (PreparedStatement statement = db.con.prepareStatement(updateQuery)) {
            // Set the updated values in the prepared statement
            statement.setString(1, updatedValue1);
            statement.setString(2, updatedValue2);
            statement.setString(3, updatedValue3);
             statement.setString(4, selectedInfo);

            // Execute the update query
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected >0) {
                // Update successful
                l.setText("Data updated successfully.");
            } else {
                // No rows affected, update failed
                l.setText("Failed to update data.");
            }

            // Close the update statement
            statement.close();
        } catch (SQLException e) {
            // Handle any exceptions that occur during the update operation
            e.printStackTrace();
        } finally {
            // Close the database connection
            try {
                db.con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }

   
    public void nav_inserIns() {
        nav.navTo(pane7, nav.fxmlCtreateIns);
    }

    public void nav_editins() {
       // nav.navTo(pane7, nav.fxmleditIns);

    }

    private void showErrorMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInformationMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
private void showInfoAlert(String message) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Information");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
private void showErrorAlert(String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
public void nav_logout(){
        nav.navTo(pane7, nav.fxmlogin);

//                System.out.println("Email does not exist in the database.");
}
}
