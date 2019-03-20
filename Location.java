// Course: CS4242
// Student name: Andrea Pinardi
// Student ID: 000 763 118
// Assignment #:01
// Due Date: 02/5/19
// Signature: Andrea Pinardi
// Score:

import java.util.Comparator;

public class Location {

    // Parameters
    private int coordX, coordY;
    private int f,g,h;
    private Constants.direction direction;

    // Constructors
    public Location(int coordX, int coordY){
        this.coordX = coordX;
        this.coordY = coordY;
    }
    public Location(){
        randLocation();
    }
    public void randLocation(){
        coordX = (int)(Math.random()* Constants.WIDTH);
        coordY = (int)(Math.random()* Constants.HEIGHT);
    }

    // Getters
    public int getCoordX(){
        return coordX;
    }
    public int getCoordY() {
        return coordY;
    }
    public int getF(){
        return f;
    }
    public int getG() {
        return g;
    }
    public int getH() {
        return h;
    }
    public int getIndex(){
        return coordY*Constants.BOARD_SIZE_X+coordX;
    }
    public Constants.direction getDirection(){
        return direction;
    }

    // Setters
    public void setCoord(int coordX, int coordY){
        this.coordX = coordX;
        this.coordY = coordY;
    }
    public void setCoordX(int coordX){
        this.coordX = coordX;
    }
    public void setCoordY(int coordY){
        this.coordY = coordY;
    }
    public void moveWest(){
        coordX--;
    }
    public void moveEast(){
        coordX++;
    }
    public void moveNorth(){
        coordY--;
    }
    public void moveSouth(){
        coordY++;
    }

    public void setF(int f) {
        this.f = f;
    }
    public void setG(int g) {
        this.g = g;
    }
    public void setH(int h) {
        this.h = h;
    }
    public void setDirection(Constants.direction direction){
        this.direction = direction;
    }

    // Condition Check
    public boolean isValid(){
        if(coordX<0 || coordX>= Constants.BOARD_SIZE_X)
            return false;
        if(coordY<0 || coordY>= Constants.BOARD_SIZE_Y)
            return false;
        return true;
    }
    public boolean canMoveWest(){
        if (coordX>0){
            return true;    // movement to left is possible
        }
        else
            return false;  // movement to left is impossible
    }
    public boolean canMoveEast(){
        if (coordX< Constants.WIDTH-1){
            return true;    // movement to right is possible
        }
        else
            return false;  // movement to right is impossible
    }
    public boolean canMoveNorth(){
        if (coordY>0){
            return true;    // movement upward is possible
        }
        else
            return false;  // movement upward is impossible
    }
    public boolean canMoveSouth(){
        if (coordY< Constants.HEIGHT-1){
            return true;    // movement downward is possible
        }
        else
            return false;  // movement to downward is impossible
    }

    public void copyLocation(Location source){
        coordX = source.getCoordX();
        coordY = source.getCoordY();
        f = source.getF();
        g = source.getG();
        h = source.getH();
    }
    public boolean equals(Location location2){
        if(coordY==location2.getCoordY() && coordX==location2.getCoordX())
            return true;
        else
            return false;
    }
    public static double getRealDistance(Location location1, Location location2){
        return Math.sqrt(Math.pow(location1.coordX-location2.coordX,2)+Math.pow(location1.getCoordY()-location2.getCoordY(),2));
    }
    public static int getManhattanDistance(Location location1, Location location2){
        return Math.abs(location1.coordX-location2.coordX)+Math.abs(location1.getCoordY()-location2.getCoordY());
    }
    public String toString(){
        return "("+coordX+", "+coordY+")"+ " f(" + f + ")=" + g + "+" + h+" opening direction:"+direction;
    }
    public String toString(String title){
        return title+"="+toString();
    }
    public Location backTraceDirection(){
        switch (direction) {
            case n:
                return new Location(coordX, coordY+1);
            case s:
                return new Location(coordX, coordY-1);
            case e:
                return new Location(coordX-1, coordY);
            case w:
                return new Location(coordX+1, coordY);
            case sw:
                return new Location(coordX+1, coordY-1);
            case se:
                return new Location(coordX-1, coordY-1);
            case nw:
                return new Location(coordX+1, coordY+1);
            case ne:
                return new Location(coordX-1, coordY+1);
            default:
                return null;
        }
    }
    public int backTraceDirectionCost(){
        switch (direction) {
            case n:
            case s:
            case e:
            case w:
                return 10;
            case sw:
            case se:
            case nw:
            case ne:
                return 14;
            default:
                return -1;
        }
    }
}

class SortByFValue implements Comparator<Location> {
    public int compare(Location a, Location b) {
        return (a.getF() - b.getF())==0?(a.getCoordY()*Constants.BOARD_SIZE_X+a.getCoordX() - (b.getCoordY()*Constants.BOARD_SIZE_X+b.getCoordX())):(a.getF() - b.getF());
    }
}