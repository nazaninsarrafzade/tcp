package Client;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
public class GUIclient extends Application {
    Client client=new Client();
    public static void main(String[] args) {
        launch(args);
    }
Stage window;
    @Override
    public void start(Stage primaryStage) {
       window= primaryStage;
       window.setTitle("file transfer");
        Button Download = new Button();
        Download.setText("Download me:)");
        GridPane layout=new GridPane();
        layout.setPadding(new Insets(10,10,10,10));
        layout.setVgap(8);
        layout.setHgap(10);
        Label filepath = new Label("FilePath:");
        Label ip = new Label("IP:");
        GridPane.setConstraints(ip, 0, 0);
        GridPane.setConstraints(filepath, 0, 1);
        TextField Filepath = new TextField("C:\\Users\\nic\\Downloads");
        TextField IP = new TextField("localhost");
        GridPane.setConstraints(IP, 1, 0);
        GridPane.setConstraints(Filepath, 1, 1);
        layout.getChildren().addAll(Download,filepath,IP,ip,Filepath);
        GridPane.setConstraints(Download, 2, 2);
        Download.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    window.close();
                    String b= IP.getText();
                    String c=Filepath.getText();
                    set_Stuff(b,c,"Get");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        Scene scene = new Scene(layout, 700, 300);
        window.setScene(scene);
        window.show();
    }

    public void set_Stuff(String b,String d,String c) throws Exception {
        client.run(b,c,d);
    }


}
