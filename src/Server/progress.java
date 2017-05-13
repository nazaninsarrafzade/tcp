package Server;

import javafx.scene.control.TextField;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class progress extends Application {

    private Server send;

    @Override
    public void start(Stage primaryStage) {
        TextField filenameInput = new TextField("o.txt");
        final Label label = new Label("send files:");
        final ProgressBar progressBar = new ProgressBar(0);
        final ProgressIndicator progressIndicator = new ProgressIndicator(0);
        final Button startButton = new Button("Start");
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startButton.setDisable(true);
                progressBar.setProgress(0);
                progressIndicator.setProgress(0);

                send = new Server(filenameInput.getText());


                progressBar.progressProperty().unbind();


                progressBar.progressProperty().bind(send.progressProperty());


                progressIndicator.progressProperty().unbind();


                progressIndicator.progressProperty().bind(send.progressProperty());


                new Thread(send).start();

            }
        });

        FlowPane root = new FlowPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);

        root.getChildren().addAll(label, progressBar, progressIndicator, //
                 startButton, filenameInput);

        Scene scene = new Scene(root, 500, 120, Color.WHITE);
        primaryStage.setTitle("ProgressBar & ProgressIndicator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}