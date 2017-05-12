package Server;

import com.sun.corba.se.spi.activation.Server;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by nazanin-sarrafzadeh on 5/11/2017.
 */
public class GUIserver extends Application implements EventHandler<ActionEvent>{

    Button Ok;
    Server server=new Server();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("File Transfer");
        Ok=new Button();
        Ok.setText("Ok");
        Ok.setOnAction(this);
        StackPane layout=new StackPane();
        layout.getChildren().add(Ok);
        Scene scene=new Scene(layout,300,250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource()==Ok){

        }

    }
    public void SetStuff(){

    }
}
