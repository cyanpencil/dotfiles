package gapp.ulg.Utilities;

import gapp.ulg.game.board.*;
import gapp.ulg.game.board.GameRuler.*;
import gapp.ulg.game.util.BoardOct;

import java.util.*;

/**
 * Created by Edoardo on 26/05/2016.
 */
public class SituationUtilities {

    /** Lista delle mosse valide a partire da una situazione di un qualsiasi gioco
     * @param gM Meccanica del gioco
     * @param s Situazione del gioco
     * @param <P> Tipo dei pezzi del gioco
     * @return La lista delle mosse valide o la lista vuota se la situazione è finale
     */
    public static <P> List<Move<P>> allValidMoves(Mechanics<P> gM, Situation<P> s){
        return new ArrayList<>(gM.next.get(s).keySet());
    }

    /** Situazione dopo una mossa (ritenuta a priori valida)
     * @param move Mossa da giocare
     * @param s Situazione corrente
     * @param gM Meccanica del gioco
     * @param <P> Tipo dei pezzi di gioco
     * @return La situazione dopo la mosa
     */
    public static <P> Situation<P> moveSituation(Move<P> move, Situation<P> s, Mechanics<P> gM){
        Map<Move<P>, Situation<P>> nextSituations = gM.next.get(s);
        return nextSituations.get(move);
    }

    /** Board di una situazione
     * @param s Situazione corrente
     * @param width Parametro width del gioco
     * @param height Parametro height del gioco
     * @param <P> Tipo di pezzi del gioco
     * @return la Board corrispondente alla Situation
     */
    public static <P> Board<P> boardOfSituation(Situation<P> s, int width, int height){
        BoardOct<P> board = new BoardOct<P>(width, height);
        board.addAll(s.newMap());
        return board;
    }

    /** Situazione di una board
     * @param board board corrente
     * @param turn turno del giocatore corrente
     * @param <P> tipo di pezzi del gioco
     * @return la Situation corrispondente alla Board
     */
    public static <P> Situation<P> situationOfBoard(Board<P> board, int turn){
        Map<Pos, P> pieceMap = new HashMap<>();
        for(Pos p : board.get())
            pieceMap.put(p, board.get(p));
        return new Situation<>(pieceMap, turn);
    }


    /** Situazione random tra le next Situations di una situazione di partenza
     * @param startinSituation
     * @param gM
     * @param <P>
     * @return la situazione random
     */
    public static <P> Situation<P> randomNextSituation(Situation<P> startinSituation, Mechanics<P> gM){
        Random rnd = new Random();
        List<Situation<P>> nextSituations = new ArrayList<>(gM.next.get(startinSituation).values());
        return nextSituations.get(rnd.nextInt(nextSituations.size()));
    }
}
