public class Pair {

    private final Position position1;
    private final Position position2;
    private final int color;

    public Pair(Position position1, Position position2, int color){
        this.position1 = position1;
        this.position2 = position2;
        this.color = color;
    }

    public boolean isPositionFound(Position clickedPosition) {
        if (this.getPosition1().equals(clickedPosition) || this.getPosition2().equals(clickedPosition)) {
            return true;
        }
        return false;
    }

    public boolean equals(Pair other) {
        return this.color == other.color;
    }

    public Position getPosition1(){
        return position1;
    }
    public Position getPosition2(){
        return position2;
    }
    public int getColor(){
        return color;
    }

}
