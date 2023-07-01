import jserver.*;
import jserver.Board;
import plotter.Graphic;

import java.sql.SQLOutput;

// xsendadapter, board, graphic

public class Gui implements BoardClickListener {

    public static final int SIZE_X = 6;
    public static final int SIZE_Y = 5;

    private MemoryBoard memoryBoard;
    private XSendAdapterEN xsend;
    private int firstClickColor;
    private Board board;

    public Gui(){
        xsend = new XSendAdapterEN();
        board = xsend.getBoard();
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
    }

    public void flipCard(Position clickPosition) {
        for (Pair pair : memoryBoard.getPairs()) {
            Position position1 = pair.getPosition1();
            Position position2 = pair.getPosition2();
            if (pair.getPosition1().equals(clickPosition)) {
                xsend.color2(position1.getX(), position1.getY(), pair.getColor());
                this.firstClickColor = pair.getColor();
            } else if (pair.getPosition2().equals(clickPosition)) {
                xsend.color2(position2.getX(), position2.getY(), pair.getColor());
                this.firstClickColor = pair.getColor();
            }
        }
    }

    public Symbol getSymbol(Position position) {
        int linearBoardNumber = position.getX() + position.getY() * SIZE_X;
        return this.board.getSymbols().get(linearBoardNumber);
    }

    public int getColorValueFrom(Symbol symbol) {
        int r = symbol.getFarbe().getRed();
        int g = symbol.getFarbe().getGreen();
        int b = symbol.getFarbe().getBlue();
        r = 256 * 256 * r;
        g = 256 * g;
        return r + g + b;
    }
}