import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;

public class PathfinderTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
// Objects
        StackPane stackPane;
        BorderPane borderPane;

        stackPane = new StackPane();
        borderPane = new BorderPane();

        Board board = new Board(Constants.START, Constants.FINISH, Constants.OBSTACLES);
        GuessBoard guessBoard = new GuessBoard(Constants.START);
        stackPane.getChildren().addAll(board, guessBoard);

        borderPane.setCenter(stackPane);
        borderPane.setPadding(new Insets(10));
        borderPane.setOnMouseClicked(event -> {
            guessBoard.next();
        });

        // Put the pane on the scene and the scene on the stage
        Scene scene = new Scene(borderPane, Constants.WIDTH, Constants.HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pathfinding with A* - Andrea Pinardi");
        primaryStage.show();
    }
}
