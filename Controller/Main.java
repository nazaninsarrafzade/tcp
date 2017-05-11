package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by nazanin-sarrafzadeh on 5/11/2017.
 */
public class Main extends Application {
    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Main.stage=stage;
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/GUI/server.fxml")),600,400));
        stage.setTitle("Register Form");
        stage.show();
    }
}
