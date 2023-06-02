/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance1;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.postgresql.ds.PGSimpleDataSource;

/**
 *
 * @author hp
 */
public class CreatLecCtrl implements Initializable{
     @FXML
    private TextField l_name;
      @FXML
    private Label l;
    @FXML
    private TextField c_name;
    @FXML
    private ComboBox <String> r;
    
    DBModel db = new DBModel();
   
  
    Navigation nav = new Navigation();
    @FXML
    public AnchorPane pane5;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       r.setItems(FXCollections.observableArrayList(db.getRoom()));
        db.connect();
    }
 
    public void insert() {
        String n4=l_name.getText();
        String n5=r.getValue();
        String n6=c_name.getText();
      db.connect(); 
        try {
            // Prepare the SQL query
            String sqlQuery = "INSERT INTO lectures (l_title ,title, room_name) VALUES (?, ?, ?)";
            PreparedStatement statement = db.con.prepareStatement(sqlQuery);
             //Set the parameter values
            statement.setString(1,n4);
            statement.setString(2,n6);
            statement.setString(3,n5);           
            // Execute the query
          int rowsAffected = statement.executeUpdate();

    if (rowsAffected > 0) {
        System.out.println("Data inserted successfully.");
        l.setText("Data inserted successfully.");
    } else {
        System.out.println("Failed to insert data.");
        l.setText("Failed to insert data.");
    }

    // Close the statement
    statement.close();
        } catch (SQLException e) {


        }
    }

    public void nav_insert() {
        insert();

    }

    public void nav_insert_Stud() {
          nav.navTo(pane5, nav.fxmlCreatStud);

    }

    public void nav_edit_Stud() {
        nav.navTo(pane5, nav.fxmlEditStud);

    }

    public void nav_inser_tLectures() {
        nav.navTo(pane5, nav.fxmlCreatLec);
    }

    public void nav_edit_Lectures() {
        nav.navTo(pane5, nav.fxmlEditLec);

    }

    public void nav_attendance1() {
        nav.navTo(pane5, nav.fxmlRecordingAttendance);

    }
    public void nav_logout(){
        nav.navTo(pane5, nav.fxmlogin);

//                System.out.println("Email does not exist in the database.");
}

    
}
