import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Board extends Pane {

    // Paramters
    private Location locationStart, locationFinish;
    private ArrayList<Location> listObstacle;

    // Constructor
    public Board(Location locationStart, Location locationFinish, ArrayList<Location> listObstacle){
        this.locationStart = locationStart;
        this.locationFinish = locationFinish;
        this.listObstacle = listObstacle;

        drawBoard();
    }

    // Spawn
    private void drawBoard(){
        ArrayList<Rectangle> squares = new ArrayList<>();
        for(int y=0; y<Constants.BOARD_SIZE_Y; y++){
            for(int x=0; x<Constants.BOARD_SIZE_X; x++){
                Rectangle square = new Rectangle(x*Constants.SQUARE_LENGTH, y*Constants.SQUARE_LENGTH, Constants.SQUARE_LENGTH, Constants.SQUARE_LENGTH);
                square.setFill(Color.BLACK);
                square.setStroke(Color.BLUE);
                square.setStrokeWidth(1);
                squares.add(square);
            }
        }

        // Start
        squares.get(locationStart.getCoordY()*Constants.BOARD_SIZE_X+locationStart.getCoordX()).setFill(Constants.START_COLOR);
        // Finish
        squares.get(locationFinish.getCoordY()*Constants.BOARD_SIZE_X+locationFinish.getCoordX()).setFill(Constants.FINISH_COLOR);
        // Obstacles
        for(Location l: listObstacle){
            squares.get(l.getCoordY()*Constants.BOARD_SIZE_X+l.getCoordX()).setFill(Constants.OBSTACLE_COLOR);
        }

        getChildren().addAll(squares);
    }
}

