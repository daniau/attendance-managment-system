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
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author hp
 */
public class CreateCourseCtrl implements Initializable {
     @FXML
    private Label l;

    @FXML
    private TextField c_name;
    @FXML
    private TextField b;
    @FXML
    private ComboBox<String> room1;
    @FXML
    private ComboBox<String> instr_name1;

    DBModel db = new DBModel();
    Navigation nav = new Navigation();
    @FXML
    public AnchorPane pane9;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
instr_name1.setItems(FXCollections.observableArrayList(db.getInstructorNames()));
room1.setItems(FXCollections.observableArrayList(db.getRoom()));
    }
//

    public void insert() throws SQLException {
        db.connect();
         String n1=c_name.getText();
        String n2=b.getText();
        String n3=room1.getValue();

        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        try {
            // Prepare the SQL query
            String sqlQuery = "INSERT INTO  courses (title, book, room_name,created_at, instructor_id)  VALUES (?,?, ?, ?,?)";
            PreparedStatement statement = db.con.prepareStatement(sqlQuery);
//            statement.setString(1, c_name.getText());
//            statement.setString(2, b.getText());
//            statement.setTimestamp(3, createdAt);
//            statement.setString(4, room1.getText());
/*--------------------------------------------------------------------------------------------------*/
            String sqlQuery1 = "SELECT instructor_id FROM instructors  WHERE name = ?";
            PreparedStatement selectStatement = db.con.prepareStatement(sqlQuery1);

            String instructorName = instr_name1.getValue();
            selectStatement.setString(1, instructorName);
            ResultSet resultSet = selectStatement.executeQuery();
            int instructorId = 0;

            if (resultSet.next()) {
                instructorId = resultSet.getInt("instructor_id");
                System.out.println("Instructor ID: " + instructorId);
            } else {
                System.out.println("Instructor not found.");
            }

            resultSet.close();
            selectStatement.close();
/*---------------------------------------------------------------------------------------------------------*/
  statement.setString(1, c_name.getText());
            statement.setString(1, n1);
            statement.setString(2, n2);
           statement.setString(3, n3);
            statement.setTimestamp(4, createdAt);
            statement.setInt(5, instructorId);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                l.setText("Data inserted successfully.");
            } else {
                 l.setText("Failed to insert data.");
            }
            statement.close();
            db.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void nav_insert() throws SQLException {
        insert();

    }

    public void nav_insertCoursre() {
        //nav.navTo(pane9, nav.fxmlCtreateCourse);

    }

    public void nav_edit() {
        nav.navTo(pane9, nav.fxmleditCourse);

    }

    public void nav_inserIns() {
        nav.navTo(pane9, nav.fxmlCtreateIns);
    }

    public void nav_editins() {
        nav.navTo(pane9, nav.fxmleditIns);

    }
     public void nav_logout() {
        nav.navTo(pane9, nav.fxmlogin);

    }

}
