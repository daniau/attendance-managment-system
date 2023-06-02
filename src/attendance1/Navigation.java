/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance1;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author hp
 */
public class Navigation {

    public final String fxmlogin = "/attendance1/Login.fxml";
    public final String fxmlCtreateIns = "/attendance1/creatIns.fxml";
    public final String fxmlCtreateCourse = "/attendance1/CreateCourse.fxml";
    public final String fxmlRecordingAttendance = "/attendance1/Recordatten.fxml";
    public final String fxmleditCourse = "/attendance1/admineditcourse.fxml";
    public final String fxmleditIns = "/attendance1/editIns.fxml";
    public final String fxmlCreatStud = "/attendance1/creatStud.fxml";
    public final String fxmlEditStud = "/attendance1/editStud.fxml";
    public final String fxmlEditLec = "/attendance1/editLec.fxml";
    public final String fxmlCreatLec = "/attendance1/creatLec.fxml";

    public void navTo(Parent rootPane, String path) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));
            rootPane.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Navigation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
