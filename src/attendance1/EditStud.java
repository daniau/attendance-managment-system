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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author hp
 */
public class EditStud  implements Initializable{
     @FXML
    private Label l;
       @FXML
    private ComboBox <String>  id;
    @FXML
    private TextField name1;
    @FXML
    private TextField name2;
    @FXML
    private TextField name3;
    @FXML
    private TextField name4;
    @FXML
    private TextField adderss;
    @FXML
    private TextField gender1 ;
    DBModel db = new DBModel();
   // Connection con = null;

    Navigation nav = new Navigation();
    @FXML
    public AnchorPane pane4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         
         id.setItems(FXCollections.observableArrayList(db.getStud()));
    }
    public void nav_select() {
           db.connect();
        String selectedInfo = id.getValue();
//first_name,second_name ,third_name ,fourth_name,gender, address
        try {
         
            // Prepare the SQL query
   String sqlQuery = "SELECT first_name,second_name ,third_name ,fourth_name,gender, address FROM students WHERE student_id = ?";
            PreparedStatement statement = db.con.prepareStatement(sqlQuery);
            statement.setString(1, selectedInfo);
            /*-----------------------------------------------------------------------------------------------------------*/
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String data1 = resultSet.getString("first_name");
                String data2 = resultSet.getString("second_name");
                String data3 = resultSet.getString("third_name");
                String data4 = resultSet.getString("fourth_name");
                String data5 = resultSet.getString("gender");
                String data6 = resultSet.getString("address");
             
                name1.setText(data1);
                name2.setText(data2);
                name3.setText(data3);
                name4.setText(data4);
                gender1.setText(data5);
                adderss.setText(data6);


            }
            /*-----------------------------------------------------------------------------------------------------------*/

            resultSet.close();
            statement.close();
            db.con.close();
        } catch (SQLException e) {
        }
    }
     public void nav_delete(){
        String selectedInfo =id.getValue();
        db.connect();
         String sqlQuery = "DELETE FROM students where student_id = ?";
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
         String selectedInfo = id.getValue(); 
        String updatedValue0 = name2.getText();
        String updatedValue1 = name2.getText();
        String updatedValue2 = name3.getText();
          String updatedValue3 = name4.getText();
        String updatedValue4= gender1.getText();
        String updatedValue5= adderss.getText();
//    //first_name,second_name ,third_name ,fourth_name,gender, address
      db.connect();
      
String updateQuery = "UPDATE students SET first_name = ?,second_name = ? ,third_name = ?,fourth_name = ?,gender = ?,address = ?  WHERE student_id = ?";
//
        try (PreparedStatement statement = db.con.prepareStatement(updateQuery)) {
            // Set the updated values in the prepared statement
            statement.setString(1, updatedValue0);
            statement.setString(2, updatedValue1);
             statement.setString(3, updatedValue2);
            statement.setString(4, updatedValue3);
           statement.setString(5, updatedValue4);
            statement.setString(6, updatedValue5);
             statement.setString(7, selectedInfo);

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

   

    
    public void nav_insert() {
          nav.navTo(pane4, nav.fxmlCreatStud);

    }

    public void nav_editStud() {
        nav.navTo(pane4, nav.fxmlEditStud);

    }

    public void nav_insertLectures() {
        nav.navTo(pane4, nav.fxmlCreatLec);
    }

    public void nav_editLectures() {
        nav.navTo(pane4, nav.fxmlEditLec);

    }

    public void nav_attendance() {
        nav.navTo(pane4, nav.fxmlRecordingAttendance);

    }
    private void showInformationMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
private void showInfoAlert(String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Information");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
private void showErrorAlert(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    public void nav_logout(){
        nav.navTo(pane4, nav.fxmlogin);

//                System.out.println("Email does not exist in the database.");
}
}
