// Exercise31_01Client.java: The client sends the input to the server and receives
// result back from the server
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Exercise33_01Client extends Application {
  // Text field for receiving radius
  private TextField tfAnnualInterestRate = new TextField();
  private TextField tfNumOfYears = new TextField();
  private TextField tfLoanAmount = new TextField();
  private Button btSubmit= new Button("Submit");

  double annualInterestRate;
  int numberOfYears;
  double loanAmount;
  double monthlyPayment;
  double totalPayment;

  DataInputStream in = null;
  DataOutputStream out = null;

  // Text area to display contents
  private TextArea ta = new TextArea();
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ta.setWrapText(true);
   
    GridPane gridPane = new GridPane();
    gridPane.add(new Label("Annual Interest Rate"), 0, 0);
    gridPane.add(new Label("Number Of Years"), 0, 1);
    gridPane.add(new Label("Loan Amount"), 0, 2);
    gridPane.add(tfAnnualInterestRate, 1, 0);
    gridPane.add(tfNumOfYears, 1, 1);
    gridPane.add(tfLoanAmount, 1, 2);
    gridPane.add(btSubmit, 2, 1);
    
    tfAnnualInterestRate.setAlignment(Pos.BASELINE_RIGHT);
    tfNumOfYears.setAlignment(Pos.BASELINE_RIGHT);
    tfLoanAmount.setAlignment(Pos.BASELINE_RIGHT);
    
    tfLoanAmount.setPrefColumnCount(5);
    tfNumOfYears.setPrefColumnCount(5);
    tfLoanAmount.setPrefColumnCount(5);
            
    BorderPane pane = new BorderPane();
    pane.setCenter(new ScrollPane(ta));
    pane.setTop(gridPane);

    btSubmit.setOnAction(e -> {
      try {
        annualInterestRate = Double.parseDouble(tfAnnualInterestRate.getText().trim());
        numberOfYears = Integer.parseInt(tfNumOfYears.getText().trim());
        loanAmount = Double.parseDouble(tfLoanAmount.getText().trim());

        out.writeDouble(annualInterestRate);
        out.writeInt(numberOfYears);
        out.writeDouble(loanAmount);
        out.flush();

        monthlyPayment = in.readDouble();
        totalPayment = in.readDouble();

        // display info in the server gui
        ta.appendText(
            "Annual Interest Rate: " + annualInterestRate + "\n"
            + "Number of Years: " + numberOfYears + "\n"
            + "Loan Amount: $" + loanAmount + "\n"
            + "Monthly Payment: $" + monthlyPayment + "\n"
            + "Total Payment: $" + totalPayment + "\n\n"
        );

      }
      catch (IOException ex) {
        ex.printStackTrace();
      }
    });

    // setup the connection to the server
    try {
      Socket socket = new Socket("localhost", 8000);

      in = new DataInputStream(socket.getInputStream());
      out = new DataOutputStream(socket.getOutputStream());
    }
    catch (IOException ex) {
      ta.appendText(ex.toString() + "\n");
    }
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Exercise31_01Client"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
