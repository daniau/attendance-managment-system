/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance1;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author hp
 */
public class Main extends Application {
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
       Parent root= FXMLLoader.load(getClass().getResource("/attendance1/Login.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("University");
        stage.show();
    }

    
}
