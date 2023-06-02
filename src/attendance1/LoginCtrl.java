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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.postgresql.ds.PGSimpleDataSource;

/**
 *
 * @author hp
 */
public class LoginCtrl implements Initializable {

    @FXML
    private TextField p;
    @FXML
    private Label status;

    @FXML
    private TextField e;
    Connection con = null;

    Navigation nav = new Navigation();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public AnchorPane rootPane;

    public void connect() {
        PGSimpleDataSource source = new PGSimpleDataSource();
        source.setServerName("localhost");
        source.setDatabaseName("Project2");
        source.setUser("postgres");
        source.setPassword("Dania123**");
        source.setCurrentSchema("public");

        try {
            con = source.getConnection();
            System.out.println("Connected to database");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void nav_login() throws SQLException {
        if (CheckeIsInstructor()) {
            nav.navTo(rootPane, nav.fxmlCreatStud);
        } else if (CheckeIsAdmin()) {
            nav.navTo(rootPane, nav.fxmlCtreateCourse);
        }

    }

    public boolean CheckeIsAdmin() throws SQLException {
        String email1 = e.getText();
        String p1 = p.getText();

        connect();

        String sqlQuery = "SELECT password FROM admins  WHERE email = ?";
        try (PreparedStatement statement = con.prepareStatement(sqlQuery);) {
            statement.setString(1, email1);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String retrievedPassword = resultSet.getString("password");

                if (retrievedPassword.equals(p1)) {
                    status.setText("Email and password combination is valid.");
                    return true;
                } else {
                    status.setText("Invalid password.");

                }
            } else {
                status.setText("Email does not exist in the database.");

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;

//                System.out.println("Email does not exist in the database.");
    }

    public boolean CheckeIsInstructor() throws SQLException {
        String email1 = e.getText();
        String p1 = p.getText();

        connect();

        String sqlQuery = "SELECT password FROM instructors WHERE email = ?";
        try (PreparedStatement statement = con.prepareStatement(sqlQuery);) {
            statement.setString(1, email1);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println("8");

                String retrievedPassword = resultSet.getString("password");

                if (retrievedPassword.equals(p1)) {
                    status.setText("Email and password combination is valid.");
                    return true;
                } else {
                    status.setText("Invalid password.");
                }

            }
            status.setText("Email does not exist in the database.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("12345");

        return false;
    }
    
}



    

//nav.navTo(rootPane, nav.fxmlRecordingAttendance);
