package gapp.ulg.game.util;

import gapp.ulg.game.GameFactory;
import gapp.ulg.game.board.Action;
import gapp.ulg.game.board.Board;
import gapp.ulg.game.board.GameRuler;
import gapp.ulg.game.board.GameRuler.*;
import gapp.ulg.game.board.Move;
import gapp.ulg.game.board.PieceModel;
import gapp.ulg.game.board.Player;
import gapp.ulg.game.board.Pos;

import static gapp.ulg.game.board.PieceModel.Species;

import java.util.*;
import java.util.function.Function;

/** <b>IMPLEMENTARE I METODI INDICATI CON "DA IMPLEMENTARE" SECONDO LE SPECIFICHE
 * DATE NEI JAVADOC. Non modificare le intestazioni dei metodi.</b>
 * <br>
 * Metodi di utilità */
public class Utils {
    /** Ritorna una view immodificabile della board b. Qualsiasi invocazione di uno
     * dei metodi che tentano di modificare la view ritornata lancia
     * {@link UnsupportedOperationException} e il metodo {@link Board#isModifiable()}
     * ritorna false. Inoltre essendo una view qualsiasi cambiamento della board b è
     * rispecchiato nella view ritornata.
     * @param b  una board
     * @param <P>  tipo del modello dei pezzi
     * @return una view immodificabile della board b
     * @throws NullPointerException se b è null */
    public static <P> Board<P> UnmodifiableBoard(Board<P> b) {
        if(b == null) throw new NullPointerException("Inserire una Board valida");
        Board<P> unModB = new Board<P>() {
            @Override
            public System system() { return b.system(); }
            @Override
            public int width() { return b.width(); }
            @Override
            public int height() { return b.height(); }
            @Override
            public Pos adjacent(Pos p, Dir d) { return b.adjacent(p, d); }
            @Override
            public List<Pos> positions() { return b.positions(); }
            @Override
            public P get(Pos p) { return b.get(p); }
        };
        return unModB;
    }

    /** Imposta i valori dei parametri specificati nella GameFactory gf, i nomi dei
     * giocatori pp poi ottiene il GameRuler dalla gf, passa a ogni giocatore una
     * copia del GameRuler e gioca la partita del GameRuler con i giocatori dati.
     * L'esito della partita sarà registrato nel GameRuler che è ritornato. Gli
     * eventuali parametri di gf non sono impostati.
     * @param gf  una GameFactory
     * @param pp  i giocatori
     * @param <P>  tipo del modello dei pezzi
     * @return il GameRuler usato per fare la partita
     * @throws NullPointerException se gf o uno degli elementi di pp è null
     * @throws IllegalArgumentException se il numero di giocatori in pp non è
     * compatibile con quello richiesto dalla GameFactory gf oppure se il valore di
     * un parametro è errato */
    @SafeVarargs
    public static <P> GameRuler<P> play(GameFactory<? extends GameRuler<P>> gf, Player<P>...pp) {
        if(gf == null || Arrays.asList(pp).contains(null) || pp == null) throw new NullPointerException("Gamefactory o giocatore nullo");
        String[] playerNames = new String[pp.length];
        for(int x = 0; x < pp.length; x++){
            String playerName = pp[x].name();
            playerNames[x] = playerName;
        }
        gf.setPlayerNames(playerNames);
        GameRuler<P> match = gf.newGame();
        for(Player<P> player : pp){
            player.setGame(match.copy());
        }


        while(match.turn() != 0){
            int turno = match.turn();
            Player<P> currPlayer = pp[turno - 1];


            Move<P> playerMove = currPlayer.getMove();



            for(Player<P> player : pp){
                player.moved(turno, playerMove);
            }

            match.move(playerMove);

            /**
             for(Player<P> player : pp){
             player.setGame(match.copy());
             }
             currPlayer.setGame(match);
             **/
            if(match.result() != -1) return match;
        }
        return match;
    }


    /** Ritorna un oggetto funzione che per ogni oggetto di tipo {@link PieceModel}
     * produce una stringa corta che lo rappresenta. Specificatamente la stringa
     * prodotta consiste di due caratteri il primo identifica la specie del pezzo e
     * il secondo il colore. Il primo carattere è determinato come segue per le
     * diverse specie:
     * <table>
     *     <tr><th>Specie</th><th>Carattere</th></tr>
     *     <tr><td>DISC</td><td>T</td></tr>
     *     <tr><td>DAMA</td><td>D</td></tr>
     *     <tr><td>PAWN</td><td>P</td></tr>
     *     <tr><td>KNIGHT</td><td>J</td></tr>
     *     <tr><td>BISHOP</td><td>B</td></tr>
     *     <tr><td>ROOK</td><td>R</td></tr>
     *     <tr><td>QUEEN</td><td>Q</td></tr>
     *     <tr><td>KING</td><td>K</td></tr>
     * </table>
     * Il secondo è il carattere iniziale del nome del colore. L'oggetto ritornato
     * dovrebbe essere sempre lo stesso.
     * @return un oggetto funzione per rappresentare tramite stringhe corte i
     * modelli dei pezzi di tipo {@link PieceModel} */
    public static Function<PieceModel<Species>,String> PieceModelToString() {
        throw new UnsupportedOperationException("OPZIONALE");
    }

    /** Ritorna un oggetto funzione che per ogni oggetto di tipo {@link Board} con
     * tipo del modello dei pezzi {@link PieceModel} produce una stringa rappresenta
     * la board. La stringa prodotta usa la funzione pmToStr per rappresentare i
     * pezzi sulla board.
     * @param pmToStr  funzione per rappresentare i pezzi
     * @return un oggetto funzione per rappresentare le board */
    public static Function<Board<PieceModel<Species>>,String> BoardToString(
            Function<PieceModel<Species>,String> pmToStr) {
        throw new UnsupportedOperationException("OPZIONALE");
    }

    /** Tramite UI testuale permette all'utente di scegliere dei valori per gli
     * eventuali parametri della GameFactory gf, chiede all'utente i nomi per i
     * giocatori che giocano tramite UI che sono np - pp.length, poi imposta tutti
     * gli np nomi nella gf e ottiene da gf il GameRuler. Infine usa il GameRuler
     * per giocare una partita visualizzando sulla UI testuale la board dopo ogni
     * mossa e chiedendo la mossa a ogni giocatore che gioca con la UI.
     * @param gf  una GameFactory
     * @param pToStr  funzione per rappresentare i pezzi
     * @param bToStr  funzione per rappresentare la board
     * @param np  numero totale di giocatori
     * @param pp  i giocatori che non giocano con la UI
     * @param <P>  tipo del modello dei pezzi
     * @return il GameRuler usato per fare la partita
     * @throws NullPointerException se gf, pToStr, bToStr o uno degli elementi di pp
     * è null
     * @throws IllegalArgumentException se np non è compatibile con il numero di
     * giocatori della GameFactory gf o se il numero di giocatori in pp è maggiore
     * di np */
    @SafeVarargs
    public static <P> GameRuler<P> playTextUI(GameFactory<GameRuler<P>> gf,
                                              Function<P,String> pToStr,
                                              Function<Board<P>,String> bToStr,
                                              int np, Player<P>...pp) {
        throw new UnsupportedOperationException("OPZIONALE");
    }



    // ----------------------------------------------------> METODI DI OTHELLO <----------------------------------------------





    public static Set<Move<PieceModel<Species>>> validMoves(Function<Pos,PieceModel<Species>> posToPieceFunction, Board<PieceModel<Species>> board, int turn) {
        PieceModel<Species> blackdisc = new PieceModel<>(Species.DISC, "nero");
        PieceModel<Species> whitedisc = new PieceModel<>(Species.DISC, "bianco");

        Set<Move<PieceModel<Species>>> validMoves = new HashSet<>();
        PieceModel<Species> piece = turn == 1 ? blackdisc : whitedisc;
        PieceModel<Species> other = turn == 1 ? whitedisc : blackdisc;
        for (Pos p : board.positions()) {
            if (posToPieceFunction.apply(p) == null){
                List<Pos> swapList = new ArrayList<>();
                List<Pos> last = new ArrayList<>();
                boolean foundMatch = false;
                for (Board.Dir d : Board.Dir.values()) {
                    int n = 0;
                    int dirB = (d == Board.Dir.UP_L ? -1 : (d == Board.Dir.UP_R ? 1 : (d == Board.Dir.DOWN_L ? -1 : (d == Board.Dir.DOWN_R ? 1 : (d == Board.Dir.LEFT ? -1 : (d == Board.Dir.RIGHT ? 1 : (d == Board.Dir.UP_L ? -1 : 0)))))));
                    int dirT = (d == Board.Dir.UP_L ? 1 : (d == Board.Dir.UP_R ? 1 : (d == Board.Dir.DOWN_L ? -1 : (d == Board.Dir.DOWN_R ? -1 : (d == Board.Dir.DOWN ? -1 : (d == Board.Dir.UP_L ? 1 : (d == Board.Dir.UP ? 1 : 0)))))));
                    int b = p.b + dirB;
                    int t = p.t + dirT;

                    while (b >= 0 && t >= 0 && other.equals(posToPieceFunction.apply(new Pos(b, t)))) {
                        n++;
                        swapList.add(new Pos(b, t));
                        last.add(new Pos(b, t));
                        b += dirB;
                        t += dirT;
                    }
                    if (b >= 0 && t >= 0 && n > 0 && piece.equals(posToPieceFunction.apply(new Pos(b, t)))) foundMatch = true;
                    else swapList.removeAll(last);
                    last.clear();
                }
                if (foundMatch == true) {
                    validMoves.add(new Move<>(new Action<>(p,piece), new Action<>(piece,swapList.toArray(new Pos[swapList.size()]))));
                }
            }
        }
        validMoves.add(new Move<>(Move.Kind.RESIGN));
        return validMoves;
    }

    public static Integer andTheWinnerIs(Map<Pos, PieceModel<Species>> situationMap){
        Integer white = 0;
        Integer black = 0;
        for(Pos position : situationMap.keySet()){
            if(situationMap.get(position) != null && situationMap.get(position).color == "nero") black++;
            if(situationMap.get(position) != null && situationMap.get(position).color == "bianco") white++;
        }
        return (white == black ? 0 : (white > black ? -2 : -1));
    }

    public static void printSituation(GameRuler.Situation situation, int size){
        Map<Pos, PieceModel<Species>> situationMap = situation.newMap();
        PieceModel<Species> blackdisc = new PieceModel<>(Species.DISC, "nero");
        PieceModel<Species> whitedisc = new PieceModel<>(Species.DISC, "bianco");     // Pezzi

        System.out.println(" -----------------");
        for(int y = size-1; y >= 0; y--){
            for(int x = 0; x < size; x++){
                if(x == 0) System.out.print("| ");
                Pos position = new Pos(x, y);
                PieceModel<Species> piecem = situationMap.get(position);
                String piece = (blackdisc.equals(piecem) ? "N " : (whitedisc.equals(piecem) ? "B " : "- "));
                System.out.print(piece);
                if(x == size-1) System.out.print("|");
            }
            System.out.print("\n");
        }
        System.out.println(" -----------------");
    }
    // ----------------------------------------------------> FINE METODI DI OTHELLO <----------------------------------------------



    // ----------------------------------------------------> INIZIO METODI DI MNK-GAME <----------------------------------------------

    // PENSO GIUSTO
    public static GameRuler.Situation<PieceModel<Species>> MNKMoveSituation(GameRuler.Situation<PieceModel<Species>> situation, Move<PieceModel<Species>> move, int m, int n, int k){
        if(situation.turn <= 0) return null;
        // AGGIUNGERE SE LA MOSSA NON E' VALIDA

        BoardOct<PieceModel<Species>> board = MNKBoardOfSituation(situation, m, n);
        int turn = situation.turn;

        PieceModel<Species> piece = move.actions.get(0).piece;
        Pos position = move.actions.get(0).pos.get(0);
        board.put(piece, position);
        if(findMNKMatches(board, position, false, k) != null){          // In caso di vittoria
            turn = -turn;
            return new GameRuler.Situation<>(board.getBoardMap(), turn);
        }
        if(!isWinnable(board, k, 3-turn)){
            turn = 0;
            return new GameRuler.Situation<>(board.getBoardMap(), turn);
        }

        turn = 3 - turn;
        GameRuler.Situation<PieceModel<Species>> newSituation = new GameRuler.Situation<>(board.getBoardMap(), turn);

        return newSituation;
    }

    //GIUSTO
    public static Set<Move<PieceModel<Species>>> MNKValidMovesSituation(GameRuler.Situation<PieceModel<Species>> situation, int m, int n) {
        PieceModel<Species> blackdisc = new PieceModel<>(Species.DISC, "nero");
        PieceModel<Species> whitedisc = new PieceModel<>(Species.DISC, "bianco");     // Pezzi

        int turn = situation.turn;                                                    // Turno attuale
        PieceModel<Species> piece = (turn == 1 ? blackdisc : whitedisc);              // Pezzo attuale
        BoardOct<PieceModel<Species>> board = MNKBoardOfSituation(situation, m, n);              // Board attuale

        Set<Move<PieceModel<Species>>> moveSet = new HashSet<>();
        for(Pos p : board.positions()){
            if(board.get(p) == null){
                Move<PieceModel<Species>> move = new Move(new Action(p, piece));
                moveSet.add(move);
            }
        }
        moveSet.add(new Move<>(Move.Kind.RESIGN));
        return Collections.unmodifiableSet(moveSet);
    }

    //PENSO GIUSTO
    public static BoardOct<PieceModel<Species>> MNKBoardOfSituation(GameRuler.Situation<PieceModel<Species>> situation, int m, int n){
        Map<Pos, PieceModel<Species>> boardMap = situation.newMap();                  // Mappa della situazione
        BoardOct<PieceModel<Species>> board = new BoardOct<PieceModel<Species>>(m, n);        // Creo la board della situazione
        board.addAll(boardMap);
        return board;
    }

    //GIUSTO
    public static List<Pos> findMNKMatches(BoardOct<PieceModel<Species>> board, Pos startPosition, boolean wnull, int k){
        List<Board.Dir> verticalDir = new ArrayList<>(Arrays.asList(Board.Dir.DOWN, Board.Dir.UP));
        List<Board.Dir> horizontalDir = new ArrayList<>(Arrays.asList(Board.Dir.LEFT, Board.Dir.RIGHT));
        List<Board.Dir> rightDiagonal = new ArrayList<>(Arrays.asList(Board.Dir.DOWN_L, Board.Dir.UP_R));
        List<Board.Dir> leftDiagonal = new ArrayList<>(Arrays.asList(Board.Dir.DOWN_R, Board.Dir.UP_L));

        List<List<Board.Dir>> dirList = new ArrayList<>(Arrays.asList(verticalDir, horizontalDir, rightDiagonal, leftDiagonal));

        for(List<Board.Dir> line : dirList){
            List<Pos> candidateMatch = new ArrayList<>();
            candidateMatch.add(startPosition);

            String foundColor = "not";
            boolean foundC = false;

            for(Board.Dir d : line){
                Pos currPosition = board.adjacent(startPosition, d);
                if(wnull == false){
                    while(currPosition != null && board.get(currPosition) != null && board.get(currPosition).equals(board.get(startPosition))){
                        candidateMatch.add(currPosition);
                        currPosition = board.adjacent(currPosition, d);
                    }
                }

                else {
                    while(currPosition != null &&
                            (board.get(currPosition) == null || (board.get(currPosition).equals(board.get(startPosition)) ||
                                    (board.get(startPosition) == null && board.get(currPosition) != null && (board.get(currPosition).color == foundColor || foundColor == "not")))))
                    {
                        if(board.get(currPosition) != null && foundC == false){
                            foundC = true;
                            foundColor = board.get(currPosition).color;
                        }
                        candidateMatch.add(currPosition);
                        currPosition = board.adjacent(currPosition, d);
                    }
                }

            }
            if(candidateMatch.size() >= k) return candidateMatch;
        }
        return null;
    }

    //GIUSTO
    public static boolean isWinnable(BoardOct<PieceModel<Species>> board, int k, int turn){
        List<Pos> emptyPositions = new ArrayList<>();
        for(Pos p : board.positions()){
            if(board.get(p) == null) emptyPositions.add(p);
        }
        for(Pos p : board.positions()){
            boolean colored = false;
            String color = "";
            List<Pos> match = findMNKMatches(board, p, true, k);
            List<Pos> matchMI = new ArrayList<>();
            if(match != null){
                for(Pos empty : match){
                    if(colored == false && board.get(empty) != null){
                        colored = true;
                        color = board.get(empty).color;
                    }
                    if(board.get(empty) == null) matchMI.add(empty);
                }
                if(emptyPositions != null && matchMI != null){
                    String currColor = (turn == 1 ? "nero" : "bianco");
                    int needMissing = (colored == true ? (currColor != color ? 2*matchMI.size() : ((2*matchMI.size())-1)) : ((2*matchMI.size())-1));
                    if(emptyPositions.size() >= needMissing){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // ----------------------------------------------------> FINE METODI DI MNK-GAME <----------------------------------------------


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
}
