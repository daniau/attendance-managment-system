/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance1;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author hp
 */
public class RecordCtrl implements Initializable {

   // DBModel db = new DBModel();
   // Connection con = null;
   /// private PreparedStatement statement;
    Navigation nav = new Navigation();
    @FXML
    public AnchorPane pane2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void nav_select() {

    }

    public void nav_insertStud() {
        nav.navTo(pane2, nav.fxmlCreatStud);

    }

    public void nav_editStud() {

    }

    public void nav_insertLectures() {
        nav.navTo(pane2, nav.fxmlCreatLec);
    }

    public void nav_editLectures() {

    }

    public void nav_attendance() {

    }
    public void nav_logout(){
        nav.navTo(pane2, nav.fxmlogin);

//                System.out.println("Email does not exist in the database.");
}

}
