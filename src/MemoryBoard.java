import jserver.XSend;
import jserver.XSendAdapterEN;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryBoard {

    private List<Position> positions;
    private Pair[] pairs;
    private XSendAdapterEN xSend;

    public MemoryBoard(XSendAdapterEN xSend){
        this.xSend = xSend;
        this.positions = new ArrayList<>();
        initBoardPositions();
        Collections.shuffle(positions);
        //positions.forEach(System.out::println);
        this.pairs = new Pair[positions.size() / 2];
        initPairs();
        //draw();
    }
    public void initBoardPositions() {
        for(int x = 0; x < Gui.SIZE_X; x++){
            for(int y = 0; y < Gui.SIZE_Y; y++){
                positions.add(new Position(x, y));
            }
        }
    }
    public void initPairs(){
        pairs = new Pair[] {
                new Pair(positions.get(0), positions.get(1), XSend.RED),
                new Pair(positions.get(2), positions.get(3), XSend.BLUE),
                new Pair(positions.get(4), positions.get(5), XSend.GREEN),
                new Pair(positions.get(6), positions.get(7), XSend.YELLOW),
                new Pair(positions.get(8), positions.get(9), XSend.BLACK),
                new Pair(positions.get(10), positions.get(11), XSend.GRAY),
                new Pair(positions.get(12), positions.get(13), XSend.DARKBLUE),
                new Pair(positions.get(14), positions.get(15), XSend.AQUA),
                new Pair(positions.get(16), positions.get(17), XSend.BEIGE),
                new Pair(positions.get(18), positions.get(19), XSend.BLUEVIOLET),
                new Pair(positions.get(20), positions.get(21), XSend.MISTYROSE),
                new Pair(positions.get(22), positions.get(23), XSend.ORANGE),
                new Pair(positions.get(24), positions.get(25), XSend.DARKRED),
                new Pair(positions.get(26), positions.get(27), XSend.LIGHTGREEN),
                new Pair(positions.get(28), positions.get(29), XSend.CRIMSON)
        };
    }

    public Pair[] getPairs() {
        return pairs;
    }

    public void draw () {
        for(Pair pair : pairs){
            xSend.color2(pair.getPosition1().getX(), pair.getPosition1().getY(), pair.getColor());
            xSend.color2(pair.getPosition2().getX(), pair.getPosition2().getY(), pair.getColor());
        }
    }
}