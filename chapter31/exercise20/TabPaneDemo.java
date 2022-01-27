/*
Kevin Martinsen
CSCI 1175 - Industry Projects
01/27/22

Exercise 31_20 - tab pane demo
 */
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TabPaneDemo extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        BorderPane boarderPane = new BorderPane();
        TabPane tabPane = new TabPane();
        HBox hbox = new HBox();

        boarderPane.setCenter(tabPane);
        boarderPane.setBottom(hbox);

        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);

        Tab tab1 = new Tab("Line");
        StackPane pane1 = new StackPane();
        pane1.getChildren().add(new Line(10, 10, 80, 80));
        tab1.setContent(pane1);
        Tab tab2 = new Tab("Rectangle");
        tab2.setContent(new Rectangle(10, 10, 200, 200));
        Tab tab3 = new Tab("Circle");
        tab3.setContent(new Circle(50, 50, 20));
        Tab tab4 = new Tab("Ellipse");
        tab4.setContent(new Ellipse(10, 10, 100, 80));
        tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);

        ToggleGroup group = new ToggleGroup();
        RadioButton rbTop = new RadioButton("Top");
        RadioButton rbBottom = new RadioButton("Bottom");
        RadioButton rbLeft = new RadioButton("Left");
        RadioButton rbRight = new RadioButton("Right");

        rbTop.setSelected(true);

        rbTop.setToggleGroup(group);
        rbBottom.setToggleGroup(group);
        rbLeft.setToggleGroup(group);
        rbRight.setToggleGroup(group);

        hbox.getChildren().addAll(rbTop, rbBottom, rbLeft, rbRight);

        rbTop.setOnAction(e -> {
            if (rbTop.isSelected()) tabPane.setSide(Side.TOP);
        });

        rbBottom.setOnAction(e -> {
            if (rbBottom.isSelected()) tabPane.setSide(Side.BOTTOM);
        });

        rbLeft.setOnAction(e -> {
            if (rbLeft.isSelected()) tabPane.setSide(Side.LEFT);
        });

        rbRight.setOnAction(e -> {
            if (rbRight.isSelected()) tabPane.setSide(Side.RIGHT);
        });




        Scene scene = new Scene(boarderPane, 300, 250);
        primaryStage.setTitle("Exercise 31_20"); // Set the window title
        primaryStage.setScene(scene); // Place the scene in the window
        primaryStage.show(); // Display the window
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     * line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}