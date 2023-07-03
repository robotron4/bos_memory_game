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
    private Pair selectedPair;
    private boolean isSelected;
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
        isSelected = false;
    }

    @Override
    public void boardClick(BoardClickEvent event) {
        this.update(new Position(event.getX(), event.getY()));
    }

    public void update(Position clickedPosition) {
        if (this.isSelected) {
            if (this.isPositionFound(selectedPair, clickedPosition)) {
                this.isSelected = false;
            }
        } else {
            this.flipCard(clickedPosition);
        }
    }

    public void flipCard(Position clickedPosition) {
        for (Pair pair : memoryBoard.getPairs()) {
            if (this.isPositionFound(pair, clickedPosition)) {
                this.selectedPair = pair;
                this.isSelected = true;
            } else {
                System.out.println("cdwasg!!!!!!!vrae");
            }
        }
    }

    public boolean isPositionFound(Pair pair, Position clickedPosition) {
        if (pair.getPosition1().equals(clickedPosition)) {
            this.draw(pair.getPosition1(), pair.getColor());
            return true;
        } else if (pair.getPosition2().equals(clickedPosition)) {
            this.draw(pair.getPosition2(), pair.getColor());
            return true;
        }
        return false;
    }

    public void draw(Position position, int color) {
        this.xsend.color2(position.getX(), position.getY(), color);
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