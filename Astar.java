public class Astar {

    public static int f(Location current, Location next){
        int f=g(current, next)+h(next);
        next.setF(f);
        return f;
    }
    public static int g(Location current, Location next){
        int g;
        if(Location.getManhattanDistance(current, next)==1){
            g=current.getG()+10;
            next.setG(g);
            return g;
        }
        else {
            g=current.getG()+14;
            next.setG(g);
            return g;
        }
    }
    public static int h(Location location){
        int h=Location.getManhattanDistance(location, Constants.FINISH)*10;
        location.setH(h);
        return h;
    }
}
