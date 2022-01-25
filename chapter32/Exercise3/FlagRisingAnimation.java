/*
Kevin Martinsen
CSCI 1175 - Industry Projects
01/25/2022

Exercise 32_3 - Animation using a thread
 */
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FlagRisingAnimation extends Application {
	private double flagHeight = 200;

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		// Create a pane
		Pane pane = new Pane();
	
		// Add an image view and add it to pane
		ImageView imageView = new ImageView("image/us.gif");
		pane.getChildren().add(imageView);


		// set the x position of the flag
		imageView.setX(50.0);

		// lambda expression for animating the flag
		new Thread(() -> {
			try {
				while (flagHeight > 10) { // stop the flag when we height is at 10 or less
					Platform.runLater(() -> imageView.setY(flagHeight));
					flagHeight -= 1;
					Thread.sleep(20);
				}
			}
			catch (InterruptedException ex) {}
		}).start();

		// Create a scene and place it in the stage
		Scene scene = new Scene(pane, 250, 200); 
		primaryStage.setTitle("FlagRisingAnimation"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}