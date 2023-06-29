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
        //xsend.color2(event.getX(), event.getY(), memoryBoard.pairs[].getColor());
        //System.out.println(event.getX() + " " + event.getY());
        System.out.println(event);
    }
}