import javafx.scene.paint.Color;

import java.util.ArrayList;

public interface Constants {

    // Problem size
    public static final int BOARD_SIZE_X = 16;
    public static final int BOARD_SIZE_Y = 20;

    // JavaFX
    public static final double SQUARE_LENGTH = 30; // Pixels
    public static final double BOARD_WIDTH = BOARD_SIZE_X*SQUARE_LENGTH;  // Pixels
    public static final double BOARD_HEIGHT = BOARD_SIZE_Y*SQUARE_LENGTH; // Pixels
    public static final double WIDTH = BOARD_WIDTH+20;    // Pixels
    public static final double HEIGHT = BOARD_HEIGHT+20;   // Pixels

    // Colors
    public static final Color START_COLOR = Color.GREEN;
    public static final Color FINISH_COLOR = Color.RED;
    public static final Color OBSTACLE_COLOR = Color.BLUE;


    // Squares
//    public static final Location START = new Location(2,2);
//    public static final Location FINISH = new Location(6,2);
//    public static final ArrayList<Location> OBSTACLES = new ArrayList<>(){     // needs 9x5 board
//        {
//            add(new Location(4, 1));
//            add(new Location(4,2));
//            add(new Location(4,3));
//        }
//    };

    public static final Location FINISH = new Location(2,2);
    public static final Location START = new Location(15,19);
    public static final ArrayList<Location> OBSTACLES = new ArrayList<>(){    // needs 16x20 board
        {
            add(new Location(4, 1));
            add(new Location(4,2));
            add(new Location(4,3));
            add(new Location(4,0));
            add(new Location(5,3));
            add(new Location(6,3));
            add(new Location(7,3));
            add(new Location(7,2));
            add(new Location(7,1));
            add(new Location(7,0));
            add(new Location(6,1));
            add(new Location(3,3));
            add(new Location(2,3));
            add(new Location(1,3));
            add(new Location(1,2));
            add(new Location(1,1));
            add(new Location(2,1));
            add(new Location(7,7));
            add(new Location(7,8));
            add(new Location(7,9));
            add(new Location(0,7)); // try removing this
            add(new Location(8,9));
            add(new Location(8,10));
            add(new Location(8,11));
            add(new Location(8,12));
            add(new Location(8,7));
            add(new Location(8,6));
            add(new Location(8,5));
            add(new Location(9,5));
            add(new Location(9,4));
            add(new Location(8,8));
            add(new Location(8,7));
            add(new Location(6,7));
            add(new Location(5,7));
            add(new Location(4,7));
            add(new Location(3,7));
            add(new Location(2,7));
            add(new Location(1,7));
            add(new Location(8,15));
            add(new Location(9,15));
            add(new Location(10,15));
            add(new Location(11,15));
            add(new Location(12,15));
            add(new Location(13,15));
            add(new Location(8,16));
            add(new Location(8,17));
            add(new Location(8,18));
            add(new Location(8,19));

        }
    };  // needs 16x20 board

    // Directions
    public static enum direction{n,s,e,w,ne,nw,se,sw};

    // Conditions
    public static final boolean RANDOM_START = false;
    public static final boolean RANDOM_FINISH = false;
    public static final boolean RANDOM_OBSTACLE = false;

    // Parameters
    public static final boolean RANDOMIZE = false; // -1 not random
    public static final boolean DISPLAY_VERBOSE_ANALYSIS = true;

    // Debug
    public static final boolean DEBUG = false;
}
