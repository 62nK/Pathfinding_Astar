import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.Collections;

public class GuessBoard extends Pane {

    // Parameters
    private Location currentLocation;
    private ArrayList<Location> visitedLocationList;
    private ArrayList<Location> openLocationList;
    private ArrayList<Location> solutionLocationList;
    private boolean goalReached;
    private boolean solutionFound;
    private int solutionCost;

    public GuessBoard(Location locationStart){
        visitedLocationList = new ArrayList<>();
        openLocationList = new ArrayList<>();
        this.currentLocation = locationStart;
        goalReached  = false;
        solutionFound = false;
        drawAnalysis();
    }

    private void drawAnalysis(){
        getChildren().clear();
        ArrayList<Rectangle> squares = new ArrayList<>();
        ArrayList<Text> textOverlay = new ArrayList<>();
        ArrayList<Circle> directionCircle = new ArrayList<>();
        ArrayList<Line> directionLine = new ArrayList<>();
        for(Location o: openLocationList){
            Rectangle square = new Rectangle(o.getCoordX()*Constants.SQUARE_LENGTH, o.getCoordY()*Constants.SQUARE_LENGTH, Constants.SQUARE_LENGTH, Constants.SQUARE_LENGTH);
            square.setStroke(Color.GREEN);
            square.setFill(Color.TRANSPARENT);
            square.setStrokeType(StrokeType.INSIDE);
            square.setStrokeWidth(2);
            squares.add(square);

            // Arrows
            Circle circle = new Circle(Constants.SQUARE_LENGTH*(o.getCoordX()+0.5),Constants.SQUARE_LENGTH*(o.getCoordY()+0.5), Constants.SQUARE_LENGTH*0.05);
            circle.setFill(Color.TRANSPARENT);
            circle.setStroke(Color.WHITE);
            circle.setStrokeWidth(1);
            double endX=0, endY=0;
            switch (o.getDirection()){
                case n:
                case nw:
                case ne:
                    endY = Constants.SQUARE_LENGTH*(o.getCoordY()+0.75);
                    break;
                case e:
                case w:
                    endY = Constants.SQUARE_LENGTH*(o.getCoordY()+0.5);
                    break;
                case s:
                case se:
                case sw:
                    endY = Constants.SQUARE_LENGTH*(o.getCoordY()+0.25);
                    break;
            }
            switch (o.getDirection()){
                case w:
                case nw:
                case sw:
                    endX = Constants.SQUARE_LENGTH*(o.getCoordX()+0.75);
                    break;
                case s:
                case n:
                    endX = Constants.SQUARE_LENGTH*(o.getCoordX()+0.5);
                    break;
                case e:
                case se:
                case ne:
                    endX = Constants.SQUARE_LENGTH*(o.getCoordX()+0.25);
                    break;
            }
            Line line = new Line(Constants.SQUARE_LENGTH*(o.getCoordX()+0.5), Constants.SQUARE_LENGTH*(o.getCoordY()+0.5), endX, endY);
            line.setStroke(Color.WHITE);
            line.setStrokeWidth(1);

            directionCircle.add(circle);
            directionLine.add(line);

            // F
            Text fFunction = new Text(Constants.SQUARE_LENGTH*(o.getCoordX()+0.1), Constants.SQUARE_LENGTH*(o.getCoordY()+0.25), ""+o.getF());
            fFunction.setFill(Color.WHITE);
            fFunction.setFont(Font.font("Arial", Constants.SQUARE_LENGTH/5));
            textOverlay.add(fFunction);

            // G
            Text gFunction = new Text(Constants.SQUARE_LENGTH*(o.getCoordX()+0.1), Constants.SQUARE_LENGTH*(o.getCoordY()+0.90), ""+o.getG());
            gFunction.setFill(Color.WHITE);
            gFunction.setFont(Font.font("Arial", Constants.SQUARE_LENGTH/5));
            textOverlay.add(gFunction);

            // H
            Text hFunction = new Text(Constants.SQUARE_LENGTH*(o.getCoordX()+0.65), Constants.SQUARE_LENGTH*(o.getCoordY()+0.90), ""+o.getH());
            hFunction.setFill(Color.WHITE);
            hFunction.setFont(Font.font("Arial", Constants.SQUARE_LENGTH/5));
            textOverlay.add(hFunction);
        }
        for(Location v: visitedLocationList){
            Rectangle square = new Rectangle(v.getCoordX()*Constants.SQUARE_LENGTH, v.getCoordY()*Constants.SQUARE_LENGTH, Constants.SQUARE_LENGTH, Constants.SQUARE_LENGTH);
            square.setStroke(Color.DEEPSKYBLUE);
            square.setFill(Color.TRANSPARENT);
            square.setStrokeType(StrokeType.INSIDE);
            square.setStrokeWidth(2);
            squares.add(square);

            // Arrows
            Circle circle = new Circle(Constants.SQUARE_LENGTH*(v.getCoordX()+0.5),Constants.SQUARE_LENGTH*(v.getCoordY()+0.5), Constants.SQUARE_LENGTH*0.05);
            circle.setFill(Color.TRANSPARENT);
            circle.setStroke(Color.WHITE);
            circle.setStrokeWidth(1);
            double endX=0, endY=0;
            switch (v.getDirection()){
                case n:
                case nw:
                case ne:
                    endY = Constants.SQUARE_LENGTH*(v.getCoordY()+0.75);
                    break;
                case e:
                case w:
                    endY = Constants.SQUARE_LENGTH*(v.getCoordY()+0.5);
                    break;
                case s:
                case se:
                case sw:
                    endY = Constants.SQUARE_LENGTH*(v.getCoordY()+0.25);
                    break;
            }
            switch (v.getDirection()){
                case w:
                case nw:
                case sw:
                    endX = Constants.SQUARE_LENGTH*(v.getCoordX()+0.75);
                    break;
                case s:
                case n:
                    endX = Constants.SQUARE_LENGTH*(v.getCoordX()+0.5);
                    break;
                case e:
                case se:
                case ne:
                    endX = Constants.SQUARE_LENGTH*(v.getCoordX()+0.25);
                    break;
            }
            Line line = new Line(Constants.SQUARE_LENGTH*(v.getCoordX()+0.5), Constants.SQUARE_LENGTH*(v.getCoordY()+0.5), endX, endY);
            line.setStroke(Color.WHITE);
            line.setStrokeWidth(1);

            directionCircle.add(circle);
            directionLine.add(line);

            // F
            Text fFunction = new Text(Constants.SQUARE_LENGTH*(v.getCoordX()+0.1), Constants.SQUARE_LENGTH*(v.getCoordY()+0.25), ""+v.getF());
            fFunction.setFill(Color.WHITE);
            fFunction.setFont(Font.font("Arial", Constants.SQUARE_LENGTH/5));
            textOverlay.add(fFunction);

            // G
            Text gFunction = new Text(Constants.SQUARE_LENGTH*(v.getCoordX()+0.1), Constants.SQUARE_LENGTH*(v.getCoordY()+0.90), ""+v.getG());
            gFunction.setFill(Color.WHITE);
            gFunction.setTextAlignment(TextAlignment.RIGHT);
            gFunction.setFont(Font.font("Arial", Constants.SQUARE_LENGTH/5));
            textOverlay.add(gFunction);

            // H
            Text hFunction = new Text(Constants.SQUARE_LENGTH*(v.getCoordX()+0.65), Constants.SQUARE_LENGTH*(v.getCoordY()+0.90), ""+v.getH());
            hFunction.setFill(Color.WHITE);
            hFunction.setFont(Font.font("Arial", Constants.SQUARE_LENGTH/5));
            textOverlay.add(hFunction);
        }
        getChildren().addAll(squares);
        getChildren().addAll(directionCircle);
        getChildren().addAll(directionLine);
        getChildren().addAll(textOverlay);
        if(Constants.DISPLAY_VERBOSE_ANALYSIS)
            drawOutput();
    }
    private void drawOutput(){
        Text output1 = new Text(Constants.BOARD_WIDTH+10, 20.0, "");
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("List of open nodes\n");
        for(Location o: openLocationList) {
            stringBuilder1.append(o.toString("Open node")+"\n");
        }
        output1.setText(stringBuilder1.toString());

        Text output2 = new Text(Constants.BOARD_WIDTH+Constants.SQUARE_LENGTH*7+10, 20.0, "");
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("List of visited nodes\n");
        for(Location v: visitedLocationList) {
            stringBuilder2.append(v.toString("Visited node")+"\n");
        }
        output1.setText(stringBuilder1.toString());
        output2.setText(stringBuilder2.toString());
        getChildren().addAll(output1, output2);
    }
    private void drawSolution(){
        ArrayList<Circle> solutionCircle = new ArrayList<>();
        for(Location s: solutionLocationList){
            Circle circle = new Circle(Constants.SQUARE_LENGTH*(s.getCoordX()+0.5),Constants.SQUARE_LENGTH*(s.getCoordY()+0.5), Constants.SQUARE_LENGTH*0.25);
            circle.setFill(Color.ORANGERED);
            circle.setStroke(Color.BLACK);
            solutionCircle.add(circle);
        }
        Text textCost = new Text(10,10, "Cost= "+solutionCost);
        textCost.setFill(Color.ORANGERED);
        textCost.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        textCost.setStroke(Color.BLACK);

        getChildren().addAll(solutionCircle);
        getChildren().add(textCost);
    }
    private static boolean isObstacle(Location location){
        for(Location l: Constants.OBSTACLES){
            if(location.equals(l))
                return true;
        }
        return false;
    }

    public void next(){
        System.out.println("\n\n################## next step ##################");
        if(!goalReached) {
            visit();
            open();

            drawAnalysis();
        }
        else if(!solutionFound){
            solve();

            drawSolution();
        }
        else
            System.out.println("Problem solved, stop clicking...");

    }

    private void open(){
        for(int y=-1; y<=1; y++){
            for(int x=-1; x<=1; x++){
                if(x==0 && y==0)
                    continue;
                Location locationTemp = new Location();
                locationTemp.copyLocation(currentLocation);

                if(x<0)
                    locationTemp.moveWest();
                else if(x>0)
                    locationTemp.moveEast();

                if(y<0)
                    locationTemp.moveNorth();
                else if(y>0)
                    locationTemp.moveSouth();

                if(!locationTemp.isValid())
                    continue;

                if(x==1 && y==0)
                    locationTemp.setDirection(Constants.direction.e);
                else if(x==-1 && y==-1)
                    locationTemp.setDirection(Constants.direction.nw);
                else if(x==0 && y==-1)
                    locationTemp.setDirection(Constants.direction.n);
                else if(x==1 && y==-1)
                    locationTemp.setDirection(Constants.direction.ne);
                else if(x==1 && y==1)
                    locationTemp.setDirection(Constants.direction.se);
                else if(x==0 && y==1)
                    locationTemp.setDirection(Constants.direction.s);
                else if(x==-1 && y==1)
                    locationTemp.setDirection(Constants.direction.sw);
                else if(x==-1 && y==0)
                    locationTemp.setDirection(Constants.direction.w);

                if(!isObstacle(locationTemp) && !locationTemp.equals(Constants.START) && !isVisited(locationTemp)) {

                    Astar.f(currentLocation, locationTemp);
                    if(openLocationList.removeIf(l->l.equals(locationTemp) && l.getF()>locationTemp.getF()) || !isOpen(locationTemp))
                        openLocationList.add(locationTemp);
                }
            }
        }
        Collections.sort(openLocationList, new SortByFValue());
        if(Constants.DEBUG) {
            System.out.println("Priority Queue (Opened nodes):");
            for (Location l : openLocationList)
                System.out.println(l.toString("open node :"));
        }
    }
    private void visit(){
        if(!openLocationList.isEmpty()) {
            Location locationTemp = openLocationList.remove(0);
            currentLocation = locationTemp;
            visitedLocationList.add(locationTemp);
            if(locationTemp.equals(Constants.FINISH))
                goalReached = true;
        }
        if (Constants.DEBUG) {
            System.out.println("Visited-nodes List (Closed nodes):");
            for (Location l : visitedLocationList)
                System.out.println(l.toString("closed node:") + " f(" + l.getF() + ")=" + l.getG() + "+" + l.getH());
        }
    }
    private void solve(){
        solutionLocationList = new ArrayList<>();
        Collections.reverse(visitedLocationList);
        solutionLocationList.add(visitedLocationList.get(0));
        solutionCost=0;
        for(Location l: visitedLocationList){
            if (solutionLocationList.get(solutionLocationList.size()-1).backTraceDirection().equals(l)){
                solutionCost+=solutionLocationList.get(solutionLocationList.size()-1).backTraceDirectionCost();
                solutionLocationList.add(l);
            }
        }
        solutionCost+=solutionLocationList.get(solutionLocationList.size()-1).backTraceDirectionCost();
        solutionLocationList.add(Constants.START);
        solutionFound = true;
    }

    private boolean isVisited(Location location){
        for (Location l : visitedLocationList)
            if(l.equals(location))
                return true;
        return false;
    }
    private boolean isOpen(Location location){
        for (Location l : openLocationList)
            if(l.equals(location))
                return true;
        return false;
    }
}
