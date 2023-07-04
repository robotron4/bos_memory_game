import jserver.*;
import jserver.Board;
import plotter.Graphic;

// xsendadapter, board, graphic

public class Gui implements BoardClickListener {

    public static final int SIZE_X = 6;
    public static final int SIZE_Y = 5;

    private MemoryBoard memoryBoard;
    private XSendAdapterEN xsend;
    private Pair selectedPair1;
    private Pair selectedPair2;
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
        if (!this.isSelected) {
            this.setPair1(clickedPosition);
            this.isSelected = true;
            this.draw(clickedPosition, this.selectedPair1.getColor());
        } else {
            this.setPair2(clickedPosition);
            this.draw(clickedPosition,this.selectedPair2.getColor());
            this.checkPairs();
            this.isSelected = false;
        }
    }

    public void checkPairs() {
        if (this.isPairMatched()) {
            this.removeMatchedPair();
            this.checkGameOver();
        } else {
            this.hidePairs();
        }
    }

    private void checkGameOver() {
        if (this.memoryBoard.getPairs().size() == 0){
            System.out.println("GAME OVER!!!");
        }
    }

    public boolean isPairMatched() {
        return this.selectedPair1.equals(this.selectedPair2);
    }

    public void removeMatchedPair() {
        this.memoryBoard.getPairs().remove(this.selectedPair1);
        this.draw(this.selectedPair1.getPosition1(), 0xEEEEEE);
        this.draw(this.selectedPair2.getPosition2(), 0xEEEEEE);
    }
//        if (this.isSelected) {
//            if (this.selectedPair1.isPositionFound(clickedPosition)) {
//                this.memoryBoard.getPairs().remove(selectedPair1);
//
//            } else {
//                clear();
//            }
//            this.setPair2(clickedPosition);
//            this.draw(clickedPosition, this.selectedPair2.getColor());
//            this.isSelected = false;
//        } else {
//            this.setPair1(clickedPosition);
//            this.draw(clickedPosition, this.selectedPair1.getColor());
//        }


    public void setPair1(Position clickedPosition) {
        for (Pair pair : memoryBoard.getPairs()) {
            if (pair.isPositionFound(clickedPosition)) {
                this.selectedPair1 = pair;
            }
        }
    }

    public void setPair2(Position clickedPosition) {
        for (Pair pair : memoryBoard.getPairs()) {
            if (pair.isPositionFound(clickedPosition)) {
                this.selectedPair2 = pair;
            }
        }
    }

    public void draw(Position position, int color) {
        this.xsend.color2(position.getX(), position.getY(), color);
    }

    public void hidePairs() {
        this.xsend.color2(this.selectedPair1.getPosition1().getX(), this.selectedPair1.getPosition1().getY(), XSend.SILVER);
        this.xsend.color2(this.selectedPair1.getPosition2().getX(), this.selectedPair1.getPosition2().getY(), XSend.SILVER);
        this.xsend.color2(this.selectedPair2.getPosition1().getX(), this.selectedPair2.getPosition1().getY(), XSend.SILVER);
        this.xsend.color2(this.selectedPair2.getPosition2().getX(), this.selectedPair2.getPosition2().getY(), XSend.SILVER);
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