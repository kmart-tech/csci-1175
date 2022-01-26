/*
Kevin Martinsen
CSCI 1175 - Industry Projects
01/26/22

Exercise 33_09 - chat program
 */
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Exercise33_09Client extends Application {
  private TextArea taServer = new TextArea();
  private TextArea taClient = new TextArea();

  private DataInputStream in;
  private DataOutputStream out;
 
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    taServer.setWrapText(true);
    taClient.setWrapText(true);

    // set the top text area not editable
    taServer.setEditable(false);

    BorderPane pane1 = new BorderPane();
    pane1.setTop(new Label("History"));
    pane1.setCenter(new ScrollPane(taServer));
    BorderPane pane2 = new BorderPane();
    pane2.setTop(new Label("New Message"));
    pane2.setCenter(new ScrollPane(taClient));
    
    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(pane1, pane2);

    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, 400, 400);
    primaryStage.setTitle("Exercise31_09Client"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    // send the typed message when the enter key is pressed
    taClient.setOnKeyPressed(e -> {
      if (e.getCode() == KeyCode.ENTER) {
        try {
          String message = taClient.getText().trim();
          if (!message.isEmpty()) {
            out.writeUTF(message);
            taServer.appendText("C: " + message + "\n");
            taClient.clear();
          }
        } catch (IOException ex) {
          ex.printStackTrace();
        }
        e.consume(); // without this a new line is created in taClient
      }
    });

    // start the client socket and listen for input
    new Thread(() -> {
      try {
        Thread.sleep(2000);
        Socket socket = new Socket("localhost", 8000);

        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        while (true) {
          taServer.appendText("S: " + in.readUTF() + "\n");
        }
      } catch (Exception ex) { //fix
        ex.printStackTrace();
      }
    }).start();

  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
