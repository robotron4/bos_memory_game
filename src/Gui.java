import jserver.Board;
import jserver.XSendAdapterEN;
import plotter.Graphic;

// xsendadapter, board, graphic

public class Gui {
    private XSendAdapterEN xsend;

    public Gui(){
        xsend = new XSendAdapterEN();
        Board board = xsend.getBoard();
        Graphic graphic = board.getGraphic();
        graphic.setTitle("hello motherfucker");
        xsend.size(5,5);
        graphic.setLocationRelativeTo(null);
    }

}