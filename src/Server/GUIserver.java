package Server;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;

public class GUIserver extends Application  {
    boolean bool=false;
    Button ok;
    Server server=new Server();
    Stage window;
    TextField nameInput = new TextField("Bucky");
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window=primaryStage;
        window.setTitle("file transfer");
        ok = new Button();
        ok.setText("OK");
        TextField filenameInput = new TextField("re.txt");
        GridPane.setConstraints(filenameInput, 1, 0);
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    window.close();
                    String b= filenameInput.getText();
                    set_Stuff(b);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        GridPane layout=new GridPane();
        layout.setPadding(new Insets(10,10,10,10));
        layout.setVgap(8);
        layout.setHgap(10);
        Label filename = new Label("FileName:");
        GridPane.setConstraints(filename, 0, 0);

        layout.getChildren().addAll(ok,filename,filenameInput);
        GridPane.setConstraints(ok, 1, 2);
        Scene scene = new Scene(layout, 500, 250);
        window.setScene(scene);
        window.show();
        if(bool==true){primaryStage.close();}
    }
//    public void handle(ActionEvent event) {
//        if (event.getSource() == ok){
//            bool=true;
//            }
//    }
    public void set_Stuff(String b) throws Exception {
        server.run(b);
    }


}