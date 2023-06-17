import jserver.*;
import jserver.Board;
import plotter.Graphic;

// xsendadapter, board, graphic

public class Gui implements BoardClickListener {
    private XSendAdapterEN xsend;

    public Gui(){
        xsend = new XSendAdapterEN();
        Board board = xsend.getBoard();
        board.addClickListener(this);
        Graphic graphic = board.getGraphic();
        graphic.setTitle("MEMORY");
        xsend.size(5, 5);
        graphic.setLocationRelativeTo(null);
    }

    @Override
    public void boardClick(BoardClickEvent event) {
        xsend.color2(event.getX(), event.getY(), XSend.BLUEVIOLET);
    }
}