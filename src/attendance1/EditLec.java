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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author hp
 */
public class EditLec implements Initializable{
     @FXML
    private Label l;

    @FXML
    private TextField c_name;
    @FXML
    private TextField room;
    @FXML
    private ComboBox<String> l_name;

    DBModel db = new DBModel();
    Navigation nav = new Navigation();
    @FXML
    public AnchorPane pane6;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         l_name.setItems(FXCollections.observableArrayList(db.getLec()));
    }
    public void nav_select() {
           db.connect();
        String selectedInfo = l_name.getValue();
//lectures (l_title ,title, room_name)
        try {
         
            // Prepare the SQL query
            String sqlQuery = "SELECT title, room_name FROM lectures WHERE l_title = ?";
            PreparedStatement statement = db.con.prepareStatement(sqlQuery);
            statement.setString(1, selectedInfo);
            /*-----------------------------------------------------------------------------------------------------------*/
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String data1 = resultSet.getString("title");
                String data2 = resultSet.getString("room_name");
             
                c_name.setText(data1);
                room.setText(data2);


            }
            /*-----------------------------------------------------------------------------------------------------------*/

            resultSet.close();
            statement.close();
            db.con.close();
        } catch (SQLException e) {
        }
    }
     public void nav_delete(){
        String selectedInfo =l_name.getValue();
        db.connect();
        //lectures (l_title ,title, room_name)
         String sqlQuery = "DELETE FROM lectures where l_title = ?";
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
                //lectures (l_title ,title, room_name)
        String selectedInfo = l_name.getValue();
        String updatedValue1 = c_name.getText();
        String updatedValue2 = room.getText();
    
       db.connect();
       
        String updateQuery = "UPDATE instructors  SET title = ?, room_name = ? WHERE l_title = ?";

        try (PreparedStatement statement = db.con.prepareStatement(updateQuery)) {
            // Set the updated values in the prepared statement
            statement.setString(1, updatedValue1);
            statement.setString(2, updatedValue2);
         
             statement.setString(3, selectedInfo);

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

   

    
    public void nav_insertStud() {
          nav.navTo(pane6, nav.fxmlCreatStud);

    }

    public void nav_editStud() {
        nav.navTo(pane6, nav.fxmlEditStud);

    }

    public void nav_insertLectures() {
        nav.navTo(pane6, nav.fxmlCreatLec);
    }

    public void nav_editLectures() {
        nav.navTo(pane6, nav.fxmlEditLec);

    }

    public void nav_attendance() {
        nav.navTo(pane6, nav.fxmlRecordingAttendance);

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
        nav.navTo(pane6, nav.fxmlogin);

//                System.out.println("Email does not exist in the database.");
}
}
