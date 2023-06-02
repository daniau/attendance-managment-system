/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance1;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author hp
 */
public class CtreateInsCtrl implements Initializable {

    @FXML
    private TextField password;
    @FXML
    private Label l;
    @FXML
    private TextField name;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    DBModel db = new DBModel();


    Navigation nav = new Navigation();
    @FXML
    public AnchorPane pane8;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void insert() {
        db.connect();
        String n1=email.getText();
        String n2=name.getText();
        String n3=phone.getText();
        String n4=password.getText();
        
        LocalDate joinDate = LocalDate.now();
        Date joinDateSql = Date.valueOf(joinDate);

        try {
            // Prepare the SQL query
            String sqlQuery = "INSERT INTO instructors  (email,name,phone,join_date ,password) VALUES (?, ?, ?, ?,?)";
            PreparedStatement statement = db.con.prepareStatement(sqlQuery);

            // Set the parameter values
            statement.setString(1, n1);
            statement.setString(2, n2);
            statement.setString(3, n3);
            statement.setDate(4, joinDateSql);
            statement.setString(5, n4);
            System.out.println(n1);


            // Execute the query
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully.");
                l.setText("Data inserted successfully.");
            } else {
                System.out.println("Failed to insert data.");
                l.setText("Failed to insert data.");
            }
        } catch (SQLException e) {

        }
    }

    public void nav_insert() {
        insert();
    }

    public void nav_insertCourse() {
           nav.navTo(pane8, nav.fxmlCtreateCourse);
    }

    public void nav_edit() {
          nav.navTo(pane8, nav.fxmleditCourse);

    }

    public void nav_inserIns() {
         //nav.navTo(pane8, nav.fxmlCtreateIns);

    }

    public void nav_editins() {
         nav.navTo(pane8, nav.fxmleditIns);

    }
    public void nav_logout(){
        nav.navTo(pane8, nav.fxmlogin);

//                System.out.println("Email does not exist in the database.");
}

}
