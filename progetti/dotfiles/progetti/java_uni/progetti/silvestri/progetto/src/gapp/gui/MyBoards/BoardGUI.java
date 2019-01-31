package gapp.gui.MyBoards;

import gapp.gui.DialogBox;
import gapp.ulg.game.board.Action;
import gapp.ulg.game.board.Board;
import gapp.ulg.game.board.Pos;
import gapp.gui.*;
import javafx.scene.layout.StackPane;
import java.util.List;

public interface BoardGUI<P> {


    /** Imposta la copia della board con cui verranno ottenute le disposizioni dei pezzi
     * per essere visualizzate nella GUI
     * @param board la board da usare per ottenere la disposizione dei pezzi */
    public void setBoard(Board<P> board);

    /** Comunica alla BoardGUI quale master verrà usato. La BoardGUI comunicherà a questo
     * master le posizioni dei click che l'utente ha fatto sulla scacchiera.
     * @param master il master della partita corrente.  */
    public void setMaster(MasterChef master);

    /** Ritorna la board che BoardGUI usa per visualizzare la disposizione dei pezzi
     * @return la board usata da BoardGUI */
    public Board<P> getBoard();

    /** Riempie uno stackPane con l'attuale disposizione dei pezzi sulla board.
     * @return uno stackPane con le immagini dei pezzi.  */
    public StackPane drawPieces();

    /** Ritorna uno stackPane con disegnata sopra la scacchiera.
     * @return uno stackPane con dentro la scacchiera colorata.  */
    public StackPane drawBOARD();

    /** Ritorna uno stackPane con disegnati sopra i suggerimenti delle mosse valide.
     * @return uno stackPane con dentro i suggerimenti delle mosse valide.  */
    public StackPane drawCirclesPane();

    /** Aggiorna la BoardGUI. Disegna sullo schermo i suggerimenti delle mosse valide, 
     * se l'opzione "ValidMoves" è settata a "True". Inoltre, questo metodo può essere
     * chiamato dal master nel mezzo della della scelta di una mossa molto lunga per 
     * visualizzare sullo schermo una disposizione intermedia dei pezzi.
     * @param highlightedPositions la lista delle posizione dove mostrare i suggerimenti.
     * @param actionsList la lista di azione che la BoardGUI deve simulare per far vedere
     * una disposizione intermedia di pezzi mentre il giocatore sceglie una mossa.  */
    public void updateBoard(List<Pos> highlightedPositions, List<Action<P>> actionsList);

    /** Visualliza sullo schermo una casellina di avvertimento. Usare questo metodo per
     * chiedere conferma all'utente di alcune azioni particolari, come per esempio la 
     * mossa RESIGN o il reset della partita corrente.
     * @param dialog la casellina da visualizzare.  */
    public default void sendDialog(DialogBox dialog) { throw new UnsupportedOperationException("OPERAZIONE NON SUPPORTATA.");}

    /** Rimuove dallo schermo la casellina di avvertimento visualizzata.  */
    public default void clearDialog(){ throw new UnsupportedOperationException("OPERAZIONE NON SUPPORTATA."); }


}
