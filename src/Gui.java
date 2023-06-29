import jserver.*;
import jserver.Board;
import plotter.Graphic;

// xsendadapter, board, graphic

public class Gui implements BoardClickListener {
    private XSendAdapterEN xsend;
    public static final int SIZE_X = 6;
    public static final int SIZE_Y = 5;
    MemoryBoard memoryBoard;

    public Gui(){
        xsend = new XSendAdapterEN();
        Board board = xsend.getBoard();
        board.addClickListener(this);
        board.setSize(800, 600);
        Graphic graphic = board.getGraphic();
        graphic.setTitle("MEMORY");
        xsend.size(SIZE_X, SIZE_Y);
        xsend.forms("s");
        xsend.symbolSizes(0.43);
        graphic.setLocationRelativeTo(null);
        memoryBoard = new MemoryBoard(xsend);
    }

    @Override
    public void boardClick(BoardClickEvent event) {
        flipCard(new Position(event.getX(), event.getY()));
        //System.out.println(event.getX() + " " + event.getY());
        System.out.println(event);
    }
    public void flipCard(Position clickPosition) {

        for(Pair pair : memoryBoard.getPairs()){
            Position position1 = pair.getPosition1();
            Position position2 = pair.getPosition2();
            if(pair.getPosition1().equals(clickPosition)){
                xsend.color2(position1.getX(), position1.getY(), pair.getColor());
            } else if (pair.getPosition2().equals(clickPosition)) {
                xsend.color2(position2.getX(), position2.getY(), pair.getColor());
            }
        }
    }

}