package Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by nazanin-sarrafzadeh on 5/11/2017.
 */
public class GUIserver implements Initializable{
    @FXML
    private TextField filename;
    @FXML
    private Button ok;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        filename.setPromptText("filename");
        ok.setText("Ok");
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                filename.getText();
//                try {
//                   // personManager.Registerperson(person);
//                    JOptionPane.showMessageDialog(null, "thanks");
//                    Main.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/GUI/server.fxml")), 600, 400));
//                    Main.stage.setTitle("Register Form");
//                    Main.stage.show();
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

            }
        });
    }
}
