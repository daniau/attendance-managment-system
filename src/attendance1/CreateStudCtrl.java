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
import java.util.ArrayList;
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
public class CreateStudCtrl implements Initializable {
     @FXML
    private Label l;

    @FXML
    private TextField id;
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
    private ComboBox <String> gender1 ;
    DBModel db = new DBModel();
   // Connection con = null;

    Navigation nav = new Navigation();
    @FXML
    public AnchorPane pane3;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        ArrayList<String> gender7 = new ArrayList<>();
        gender7.add("Femal");
        gender7.add("Male");

        gender1.setItems(FXCollections.observableArrayList(gender7));
               db.connect();


    }

    

    public void insert() {
        String id1=id.getText();
        String n1=name1.getText();
        String n2=name2.getText();
        String n3=name3.getText();
        String n4=name4.getText();
        String n5=gender1.getValue();
        String n6=adderss.getText();
        
        System.out.println(id1+""+n1+" "+n2+" "+n3+" "+n4+" "+n5+" "+n6);
     try {
    // Prepare the SQL query
    String sqlQuery = "INSERT INTO students (student_id, first_name, second_name, third_name, fourth_name, gender, address)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?)";
    PreparedStatement statement = db.con.prepareStatement(sqlQuery);

    // Set the parameter values
    statement.setString(1, id1);
    statement.setString(2, n1);
    statement.setString(3, n2);
    statement.setString(4, n3);
    statement.setString(5, n4);
    statement.setString(6, n5);
    statement.setString(7, n6);

    // Execute the query
    int rowsAffected = statement.executeUpdate();

    if (rowsAffected > 0) {
       l.setText("Data inserted successfully.");
    } else {
         l.setText("Failed to insert data.");
    }

    // Close the statement
    statement.close();

} catch (SQLException e) {
    // Handle the exception
    e.printStackTrace();
}

    }

    public void nav_insert() {
        insert();

    }

    public void nav_insertStud() {
        //  nav.navTo(pane3, nav.fxmlCtreateCourse);

    }

    public void nav_editStud() {
        nav.navTo(pane3, nav.fxmlEditStud);

    }

    public void nav_insertLectures() {
        nav.navTo(pane3, nav.fxmlCreatLec);
    }

    public void nav_editLectures() {
        nav.navTo(pane3, nav.fxmlEditLec);

    }

    public void nav_attendance() {
        nav.navTo(pane3, nav.fxmlRecordingAttendance);

    }
public void nav_logout(){
        nav.navTo(pane3, nav.fxmlogin);

//                System.out.println("Email does not exist in the database.");
}
}
