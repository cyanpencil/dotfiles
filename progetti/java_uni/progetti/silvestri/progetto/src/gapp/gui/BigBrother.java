package gapp.gui;

import gapp.ulg.game.board.GameRuler;
import gapp.ulg.game.board.Move;
import gapp.ulg.game.util.*;
import javafx.application.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.*;

// TODO: 05/09/2016 METTERE TUTTO SYNCHRONIZED, POTREBBERE ESSERE USATO SIA DALLA GUI CHE DAL THREAD DI CONTROLLO, QUANOD CHIAMA INTERRUPT() 

/** Un {@code BigBrother} è un oggetto che osserva lo svolgimento di una o più
 * partite. Lo scopo principale è di aggiornare la GUI che visualizza la board
 * ed eventuali altre informazioni a seguito dell'inizio di una nuova partita e
 * di ogni mossa eseguita.
 * @param <P>  tipo del modello dei pezzi */
public class BigBrother<P> implements PlayGUI.Observer<P> {

    private GameRuler<P> gameRuler;

    private final TheView<P> changesManager; // IL NOSTRO THE VIEW PER AGGIORNARE LA BOARD
    
    public BigBrother(TheView<P> changesManager) {
        this.changesManager = changesManager;
    }

    /** Comunica allo {@code BigBrother} il gioco (la partita) che sta iniziando.
     * Può essere nello stato iniziale o in uno stato diverso, ad es. se la
     * partita era stata sospesa ed ora viene ripresa. L'oggetto {@code g} è
     * una copia del {@link GameRuler} ufficiale del gioco. Lo {@code Observer}
     * può usare e modificare {@code g} a piacimento senza che questo abbia
     * effetto sul {@link GameRuler} ufficiale. In particolare lo {@code Observer}
     * può usare {@code g} per mantenersi sincronizzato con lo stato del gioco
     * riportando in {@code g} le mosse dei giocatori, vedi
     * {@link BigBrother#moved(int, Move)}. L'uso di {@code g} dovrebbe avvenire
     * solamente nel thread in cui il metodo è invocato.
     * <br>
     * <b>Il metodo non blocca, non usa altri thread e ritorna velocemente.</b>
     * @param g  un gioco, cioè una partita
     * @throws NullPointerException se {@code g} è null */
    @Override
    public void setGame(GameRuler<P> g) {
        if(g == null) throw new NullPointerException("GameRuler non valido");
        this.gameRuler = g;
        int turn = gameRuler.turn();


        try{
            double scoreFirst = g.score(1);
            double scoreSecond = g.score(2);
            ArrayList<Double> scores = new ArrayList<>(Arrays.asList(scoreFirst, scoreSecond));
            boolean isOver = gameRuler.result() != -1;
            Platform.runLater(() -> {this.changesManager.accept(gameRuler.getBoard(), turn, scores, gameRuler.result());});
        } catch (UnsupportedOperationException e){
            boolean isOver = gameRuler.result() != -1;
            Platform.runLater(() -> {this.changesManager.accept(gameRuler.getBoard(), turn, null, gameRuler.result());});
        }
        // MODIFICA TUTTA LA BOARD CON I PEZZI NELLE POSIZONI DEL GANERULER
    }


    /** Comunica allo {@code BigBrother} la mossa eseguita da un giocatore. Lo
     * {@code Observer} dovrebbe usare tale informazione per aggiornare la sua
     * copia del {@link GameRuler}. L'uso del GameRuler dovrebbe avvenire
     * solamente nel thread in cui il metodo è invocato.
     * <br>
     * <b>Il metodo non blocca, non usa altri thread e ritorna velocemente.</b>
     * @param i  indice di turnazione di un giocatore
     * @param m  la mossa eseguita dal giocatore
     * @throws IllegalStateException se non c'è un gioco impostato o c'è ma è
     * terminato.
     * @throws NullPointerException se {@code m} è null
     * @throws IllegalArgumentException se {@code i} non è l'indice di turnazione
     * di un giocatore o {@code m} non è una mossa valida nell'attuale situazione
     * di gioco */
    @Override
    public void moved(int i, Move<P> m) {
        if(this.gameRuler == null || this.gameRuler.result() != -1) throw new IllegalStateException("Nello stato attuale del GameRuler non è possibile effettuare mosse");
        if(m == null) throw new NullPointerException("Mossa non valida");
        if(!this.gameRuler.isValid(m)) throw new IllegalArgumentException("Mossa non valida proprio per niente");
        try { this.gameRuler.isPlaying(i);} 
        catch (IllegalArgumentException e) {throw new IllegalArgumentException("L'indice " + i + " non è l'indice di nessun giocatore");}
        this.gameRuler.move(m);

        int turn = gameRuler.turn();


        try{
            double scoreFirst = gameRuler.score(1);
            double scoreSecond = gameRuler.score(2);
            ArrayList<Double> scores = new ArrayList<>(Arrays.asList(scoreFirst, scoreSecond));
            boolean isOver = gameRuler.result() != -1;
            Platform.runLater(() -> {this.changesManager.accept(gameRuler.getBoard(), turn, scores, gameRuler.result());});
        } catch (UnsupportedOperationException e){
            boolean isOver = gameRuler.result() != -1;
            Platform.runLater(() -> {this.changesManager.accept(gameRuler.getBoard(), turn, null, gameRuler.result());});
        }
    }


    /** Comunica allo {@code BigBrother} che il giocatore con indice di turnazione
     * {@code i} ha violato un vincolo sull'esecuzione (ad es. il tempo concesso
     * per una mossa). Dopo questa invocazione il giocatore {@code i} è
     * squalificato e ciò produce gli stessi effetti che si avrebbero se tale
     * giocatore si fosse arreso. Quindi lo {@code Observer} per sincronizzare
     * la sua copia con la partita esegue un {@link Move.Kind#RESIGN} per il
     * giocatore {@code i}. L'uso del GameRuler dovrebbe avvenire solamente nel
     * thread in cui il metodo è invocato.
     * @param i  indice di turnazione di un giocatore
     * @param msg  un messaggio che descrive il tipo di violazione
     * @throws NullPointerException se {@code msg} è null
     * @throws IllegalArgumentException se {@code i} non è l'indice di turnazione
     * di un giocatore */
    @Override
    public void limitBreak(int i, String msg) {
        if(msg == null) throw new NullPointerException("Messaggio di squalifica non valido");
        try {this.gameRuler.isPlaying(i);}
        catch (IllegalArgumentException e) {throw new IllegalArgumentException("L'indice " + i + " non è l'indice di nessun giocatore");}
        this.gameRuler.move(new Move<P>(Move.Kind.RESIGN));
    }


    /** Comunica allo {@code BigBrother} che la partita è stata interrotta. Ad es.
     * è stato invocato il metodo {@link PlayGUI#stop()}.
     * @param msg  una stringa con una descrizione dell'interruzione */
    @Override
    public void interrupted(String msg) {
        Platform.runLater(() -> changesManager.interrupted());
    }


}
