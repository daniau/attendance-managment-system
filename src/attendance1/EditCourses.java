/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance1;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class EditCourses implements Initializable {

    @FXML
    private Label l;
    @FXML
    private ComboBox<String> course_name;
    @FXML
    private TextField book;

    @FXML
    private TextField room;
    @FXML
    private ComboBox<String> ins_name;
    Navigation nav = new Navigation();
    @FXML
    public AnchorPane pane1;
    DBModel db = new DBModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ins_name.setItems(FXCollections.observableArrayList(db.getInstructorNames()));
        course_name.setItems(FXCollections.observableArrayList(db.getCourseName()));
    }

    public void nav_select() {
        String selectedInfo = course_name.getValue();

        try {
            db.connect();
            // Prepare the SQL query
            String sqlQuery = "SELECT book,room_name,instructor_id FROM courses  WHERE title = ?";
            PreparedStatement statement = db.con.prepareStatement(sqlQuery);
            statement.setString(1, selectedInfo);
            /*-----------------------------------------------------------------------------------------------------------*/
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String data1 = resultSet.getString("book");
                String data2 = resultSet.getString("room_name");
                int data3 = resultSet.getInt("instructor_id");
                System.out.println(data1 + " " + data2 + " " + data3);
                System.out.println("jkhkjhni");
                book.setText(data1);
                room.setText(data2);
                String instructorName = getInstructorNameFromDatabase(data3);
                ins_name.setValue(instructorName);
            }
            /*-----------------------------------------------------------------------------------------------------------*/

            resultSet.close();
            statement.close();
            db.con.close();
        } catch (SQLException e) {
        }
    }

    private String getInstructorNameFromDatabase(int instructorId) {
        // Code to retrieve the instructor name from the database using the instructorId
        // Implement your database connection and query logic here
        db.connect();
        String instructorName = ""; // Placeholder

        try {
            // Connect to the database

            // Prepare and execute the SQL query
            String sqlQuery = "SELECT name FROM instructors WHERE instructor_id = ?";
            PreparedStatement statement = db.con.prepareStatement(sqlQuery);
            statement.setInt(1, instructorId);
            ResultSet resultSet = statement.executeQuery();

            // Retrieve the instructor name
            if (resultSet.next()) {
                instructorName = resultSet.getString("name");
            }

            // Close the statement and result set
            resultSet.close();
            statement.close();

            // Close the database connection
            db.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return instructorName;
    }

    public void nav_delete() {
        String selectedInfo = course_name.getValue();
        db.connect();
        try {
            // Prepare the SQL query
            String sqlQuery = "DELETE FROM courses where title =?";
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
            // Handle any exceptions that occur during the deletion process
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

    public void nav_edit() {
        String selectedInfo = course_name.getValue();
        String updatedValue1 = room.getText();
        String updatedValue2 = book.getText();
        String updatedValue3 = ins_name.getValue();
        int instructor_id = getInstructorIdFromDatabase(updatedValue3);
       db.connect();
       
        String updateQuery = "UPDATE courses  SET book = ?, room_name = ?, instructor_id  = ? WHERE title = ?";

        try (PreparedStatement statement = db.con.prepareStatement(updateQuery)) {
            // Set the updated values in the prepared statement
            statement.setString(1, updatedValue1);
            statement.setString(2, updatedValue2);
            statement.setInt(3, instructor_id);
             statement.setString(4, selectedInfo);

            // Execute the update query
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
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
     private int getInstructorIdFromDatabase(String name) {
        // Code to retrieve the instructor name from the database using the instructorId
        // Implement your database connection and query logic here
        db.connect();
        int instructor_id = 0; // Placeholder

        try {
            // Connect to the database

            // Prepare and execute the SQL query
            String sqlQuery = "SELECT instructor_id FROM instructors WHERE name = ?";
            PreparedStatement statement = db.con.prepareStatement(sqlQuery);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            // Retrieve the instructor name
            if (resultSet.next()) {
                instructor_id = resultSet.getInt("instructor_id");
            }

            // Close the statement and result set
            resultSet.close();
            statement.close();

            // Close the database connection
            db.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return instructor_id;
    }


    public void nav_editCourse() {
      //  nav.navTo(pane1, nav.fxmleditCourse);

    }

    public void nav_insert() {
nav.navTo(pane1, nav.fxmlCtreateCourse);
    }


//    public void nav_insert() {
//    }
    public void nav_inserIns() {
        nav.navTo(pane1, nav.fxmlCtreateIns);
    }

    public void nav_editins() {
        nav.navTo(pane1, nav.fxmleditIns);

    }

    private void showErrorMessage(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInformationMessage(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void nav_logout(){
        nav.navTo(pane1, nav.fxmlogin);

//                System.out.println("Email does not exist in the database.");
}

}
//comment 2