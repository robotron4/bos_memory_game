public class Position {

    private final int x;
    private final int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return this.x + " | " + this.y;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
