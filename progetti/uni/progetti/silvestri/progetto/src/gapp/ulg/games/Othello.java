package gapp.ulg.games;

import gapp.ulg.game.board.*;
import gapp.ulg.game.util.BoardOct;
import gapp.ulg.game.util.Utils;

import static gapp.ulg.game.board.Move.Kind.RESIGN;
import static gapp.ulg.game.board.PieceModel.Species;

import java.util.*;


/** <b>IMPLEMENTARE I METODI SECONDO LE SPECIFICHE DATE NEI JAVADOC. Non modificare
 * le intestazioni dei metodi.</b>
 * <br>
 * Un oggetto Othello rappresenta un GameRuler per fare una partita a Othello. Il
 * gioco Othello si gioca su una board di tipo {@link Board.System#OCTAGONAL} 8x8.
 * Si gioca con pezzi o pedine di specie {@link Species#DISC} di due
 * colori "nero" e "bianco". Prima di inziare a giocare si posizionano due pedine
 * bianche e due nere nelle quattro posizioni centrali della board in modo da creare
 * una configurazione a X. Quindi questa è la disposzione iniziale (. rappresenta
 * una posizione vuota, B una pedina bianca e N una nera):
 * <pre>
 *     . . . . . . . .
 *     . . . . . . . .
 *     . . . . . . . .
 *     . . . B N . . .
 *     . . . N B . . .
 *     . . . . . . . .
 *     . . . . . . . .
 *     . . . . . . . .
 * </pre>
 * Si muove alternativamente (inizia il nero) appoggiando una nuova pedina in una
 * posizione vuota in modo da imprigionare, tra la pedina che si sta giocando e
 * quelle del proprio colore già presenti sulla board, una o più pedine avversarie.
 * A questo punto le pedine imprigionate devono essere rovesciate (da bianche a nere
 * o viceversa, azione di tipo {@link Action.Kind#SWAP}) e diventano
 * di proprietà di chi ha eseguito la mossa. È possibile incastrare le pedine in
 * orizzontale, in verticale e in diagonale e, a ogni mossa, si possono girare
 * pedine in una o più direzioni. Sono ammesse solo le mosse con le quali si gira
 * almeno una pedina, se non è possibile farlo si salta il turno. Non è possibile
 * passare il turno se esiste almeno una mossa valida. Quando nessuno dei giocatori
 * ha la possibilità di muovere o quando la board è piena, si contano le pedine e si
 * assegna la vittoria a chi ne ha il maggior numero. Per ulteriori informazioni si
 * può consultare
 * <a href="https://it.wikipedia.org/wiki/Othello_(gioco)">Othello</a> */
public class Othello implements GameRuler<PieceModel<Species>> {

    private final BoardOct<PieceModel<Species>> board;
    private final Board<PieceModel<Species>> unModofiableBoard;
    private final List<String> playerNames;
    private List<Move<PieceModel<Species>>> movesList = new ArrayList<>();

    private final HashMap<Pos, PieceModel<Species>> initialMap;
    private final Situation<PieceModel<Species>> initialSituation;
    private final Mechanics<PieceModel<Species>> othelloMechanics;


    private final int size;
    private final long time;

    // TODO RIGUARDA PER DUBBIO SULL'INDICE DI TURNAZIONE. RIPARTI DA QUI E RISCORRI TUTTO
    private int res = -1, turn = 1;

    private final PieceModel<Species> blackdisc = new PieceModel<>(Species.DISC, "nero");
    private final PieceModel<Species> whitedisc = new PieceModel<>(Species.DISC, "bianco");

    /** Crea un GameRuler per fare una partita a Othello, equivalente a
     * {@link Othello#Othello(long, int, String, String) Othello(0,8,p1,p2)}.
     * @param p1  il nome del primo giocatore
     * @param p2  il nome del secondo giocatore
     * @throws NullPointerException se p1 o p2 è null */
    public Othello(String p1, String p2) {
        this(0, 8, p1, p2);
    }

    /** Crea un GameRuler per fare una partita a Othello.
     * @param time  tempo in millisecondi per fare una mossa, se <= 0 significa nessun
     *              limite
     * @param size  dimensione della board, sono accettati solamente i valori 6,8,10,12
     * @param p1  il nome del primo giocatore
     * @param p2  il nome del secondo giocatore
     * @throws NullPointerException se {@code p1} o {@code p2} è null
     * @throws IllegalArgumentException se size non è uno dei valori 6,8,10 o 12 */
    public Othello(long time, int size, String p1, String p2) {
        if(p1 == null || p2 == null) throw new NullPointerException("Almeno uno dei due giocatori non è un giocatore valido");
        ArrayList<Integer> acceptableSizes = new ArrayList<>(Arrays.asList(6, 8, 10, 12));
        if(!acceptableSizes.contains(size)) throw new IllegalArgumentException("Grandezza non consentita");

        board = new BoardOct<PieceModel<Species>>(size, size);
        board.put(blackdisc, new Pos(size/2-1, size/2-1));
        board.put(whitedisc, new Pos(size/2, size/2-1));
        board.put(whitedisc, new Pos(size/2-1, size/2));
        board.put(blackdisc, new Pos(size/2, size/2));

        this.size = size;
        this.time = time;

        playerNames = Collections.unmodifiableList(Arrays.asList(p1, p2));
        unModofiableBoard = new Board<PieceModel<Species>>() {
            @Override
            public System system() {return board.system();}
            @Override
            public int width() {return size;}
            @Override
            public int height() {return size;}
            @Override
            public Pos adjacent(Pos p, Dir d) {return board.adjacent(p, d);}
            @Override
            public List<Pos> positions() {return board.positions();}
            @Override
            public PieceModel<Species> get(Pos p) {return board.get(p);}
        };

        initialMap = board.getBoardMapWithoutEmpties();
        initialSituation = new Situation<>(initialMap, 1);
        List<PieceModel<Species>> piecesList = new ArrayList<>(Arrays.asList(blackdisc, whitedisc));

        othelloMechanics = new Mechanics<>(time > 0 ? time : -1, Collections.unmodifiableList(piecesList), board.positions(), 2, initialSituation, this::next);
    }

    /** Il nome rispetta il formato:
     * <pre>
     *     Othello<i>Size</i>
     * </pre>
     * dove <code><i>Size</i></code> è la dimensione della board, ad es. "Othello8x8". */
    @Override
    public String name() {
        return "Othello" + this.size + "x" + this.size;
    }

    private static final List<String> TIMES = Arrays.asList("No limit","1s","2s","3s","5s","10s","20s","30s","1m","2m","5m");
    private static final List<Integer> TO_MS = Arrays.asList(-1,1000,2000,3000,5000,10000,20000,30000,60000,120000,300000);

    @Override
    public <T> T getParam(String name, Class<T> c) {
        if(name == null || c == null) throw new NullPointerException("Inserire un nome e un classe non nulli");
        if(!name.equals("Time") && !name.equals("Board")) throw new IllegalArgumentException("Inserire il nome di un parametro valido");
        if(c != String.class) throw new ClassCastException("Classe non corrispondente");

        if(name.equals("Board")) return (T) (this.size + "x" + this.size);
        long realtime = this.time > 0 ? this.time : -1;
        int index = TO_MS.indexOf((int) realtime);
        return (T) (TIMES.get(index));
    }

    @Override
    public List<String> players() { return playerNames; }

    /** Assegna il colore "nero" al primo giocatore e "bianco" al secondo. */
    @Override
    public String color(String name) {
        if(name == null) throw new NullPointerException("Inserire un nome non nullo");
        if(!playerNames.contains(name)) throw new IllegalArgumentException("Non è un giocatore della partita");
        String color = playerNames.indexOf(name) == 0 ? "nero" : "bianco";
        return color;
    }

    @Override
    public Board<PieceModel<Species>> getBoard() { return unModofiableBoard; }

    /** Se il giocatore di turno non ha nessuna mossa valida il turno è
     * automaticamente passato all'altro giocatore. Ma se anche l'altro giuocatore
     * non ha mosse valide, la partita termina. */
    @Override
    public int turn() {
        if(this.res != -1) return 0;
        return this.turn;
    }

    /** Se la mossa non è valida termina il gioco dando la vittoria all'altro
     * giocatore. */
    @Override
    public boolean move(Move<PieceModel<Species>> m) {
        if(m == null) throw new NullPointerException("Inserire una mossa non nulla");
        if(res != -1) throw new IllegalStateException("Partita conclusa");

        if(!validMoves().contains(m)){
            res = 3 - turn;
            turn = 3 - turn;
            return false;
        }

        if(m.kind == RESIGN){
            movesList.add(m);
            res = 3 - turn;
            turn = 3 - turn;
            return true;
        }

        if(m.kind == Move.Kind.ACTION){
            movesList.add(m);
            Action<PieceModel<Species>> add = m.actions.get(0);
            PieceModel<Species> piece = add.piece;
            Pos position = add.pos.get(0);
            board.put(piece, position);

            Action<PieceModel<Species>> swap = m.actions.get(1);

            List<Pos> swaplist = swap.pos;
            for(Pos swapos : swaplist){
                board.put(piece, swapos);
            }
            turn = 3 - turn;
        }

        if(validMoves().size() == 1){
            turn = 3 - turn;
            if(validMoves().size() == 1){
                res = (countPieces().get(1) == countPieces().get(0) ? 0 : (countPieces().get(1) > countPieces().get(0) ? 2 : 1));
                turn = 3 - turn;
            }
        }

        return true;
    }

    @Override
    public boolean unMove() {
        if(movesList.size() == 0){
            board.put(blackdisc, new Pos(3, 3));
            board.put(whitedisc, new Pos(3, 4));
            board.put(whitedisc, new Pos(4, 3));
            board.put(blackdisc, new Pos(4, 4));
            turn = 1;
            res = -1;
            return false; // La partita è appena cominciata
        }

        Move<PieceModel<Species>> lastMove = movesList.get(movesList.size()-1); // Ottieni l'ultima mossa dalla lista di mosse

        if(lastMove.kind == Move.Kind.RESIGN){
            movesList.remove(lastMove);
            this.res = -1;
            turn = 3 - turn;
            return true;
        }

        if(res != -1) res = -1; // Se la partita è conclusa riportala allo stato precedente

        List<Pos> unSwap = lastMove.actions.get(1).pos;
        PieceModel<Species> piece = lastMove.actions.get(0).piece;
        String otherColor = piece.color;
        String pieceColor = piece.color == "nero" ? "bianco" : "nero";

        PieceModel<Species> otherPiece = new PieceModel<Species>(Species.DISC, pieceColor);
        for(int index = 0; index <= unSwap.size()-1; index++){
            board.put(otherPiece, unSwap.get(index));
        }
        board.remove(lastMove.actions.get(0).pos.get(0)); // Rimuove l'ultimo pezzo aggiunto

        movesList.remove(movesList.size()-1);
        turn = 3 - turn;
        return true;
    }

    @Override
    public boolean isPlaying(int i) {
        if (i != 1 && i != 2) throw new IllegalArgumentException();
        if (res != -1) return false;
        return true;
    }

    @Override
    public int result() {
        return res;
    }

    /** Ogni mossa, eccetto l'abbandono, è rappresentata da una {@link Action} di tipo
     * {@link Action.Kind#ADD} seguita da una {@link Action} di tipo
     * {@link Action.Kind#SWAP}. */
    @Override
    public Set<Move<PieceModel<Species>>> validMoves() {
        if(res != -1) throw new IllegalStateException("Partita terminata");
        PieceModel<Species> piece = (turn == 1 ? blackdisc : whitedisc);

        Set<Move<PieceModel<Species>>> moveSet = new HashSet<>();

        Set<Move<PieceModel<Species>>> nullSet = new HashSet<>();
        nullSet.add(null);

        for(Pos p : board.positions()){
            if(board.get(p) == null && findMatchPos(p, piece) != null && findMatchPos(p, piece).length != 0){
                Action<PieceModel<Species>> addAction = new Action<>(p, piece);
                Action<PieceModel<Species>> swapAction;
                swapAction = new Action<>(piece, findMatchPos(p, piece));
                Move<PieceModel<Species>> newMove = new Move<>(addAction, swapAction);
                moveSet.add(newMove);
            }
        }

        moveSet.add(new Move<>(Move.Kind.RESIGN));

        return Collections.unmodifiableSet(moveSet);
    }

    @Override
    public double score(int i) {
        if(i <= 0 || i > 2) throw new IllegalArgumentException("Indice di turnazione non corretto");
        return countPieces().get(i-1);
    }

    @Override
    public GameRuler<PieceModel<Species>> copy() {
        return new Othello(this);
    }

    @Override
    public Mechanics<PieceModel<Species>> mechanics() {
        return othelloMechanics;
    }



    //------------------------->Inizio utilities<-------------------------


    // ****** DA RIVEDERE ******* //
    public Othello(Situation<PieceModel<Species>> othelloSituation, int size, List<Pos> positionList, int res, int turn){

        this.board = new BoardOct<PieceModel<Species>>((HashMap<Pos, PieceModel<Species>>) othelloSituation.newMap(), size, size, positionList);

        this.initialMap = null;
        this.initialSituation = null;
        this.othelloMechanics = null;
        this.unModofiableBoard = null;
        this.playerNames = new ArrayList<>(Arrays.asList("Player 1", "Player 2"));
        this.movesList = null;

        this.res = res;
        this.turn = turn;
        this.size = size;
        this.time = -1;
    }

    public static Situation<PieceModel<Species>> situationFromOthello(Othello game){
        Board<PieceModel<Species>> board = game.getBoard();
        Situation<PieceModel<Species>> situation = new Situation<>(game.getCurrentMap(), game.turn);

        return situation;
    }

    // ******** //

    public Situation<PieceModel<Species>> getCurrSituation(){
        return new Situation<>(this.board.getBoardMapWithoutEmpties(), (this.res == -1 ? this.turn : -this.res));
    }

    /** Costruttore di Othello a partire da un altro Othello
     * @param g partita di Othello
     */
    public Othello(Othello g) {
        board = new BoardOct<PieceModel<Species>>(g.board);

        initialMap = g.initialMap;

        unModofiableBoard = new Board<PieceModel<Species>>() {
            @Override
            public System system() { return board.system(); }
            @Override
            public int width() { return board.width(); }
            @Override
            public int height() { return board.height(); }
            @Override
            public Pos adjacent(Pos p, Dir d) { return board.adjacent(p, d); }
            @Override
            public List<Pos> positions() { return board.positions(); }
            @Override
            public PieceModel<Species> get(Pos p) { return board.get(p); }
        };
        List<String> nn = new ArrayList<>();
        nn.addAll(g.playerNames);
        playerNames = Collections.unmodifiableList(nn);

        movesList = new ArrayList<>();
        for(Move move : g.movesList){
            movesList.add(move);
        }

        res = g.res;
        turn = g.turn;
        this.time = g.time;
        this.size = g.size;
        this.initialSituation = g.initialSituation;
        this.othelloMechanics = g.othelloMechanics;
    }

    public Pos[] findMatchPos(Pos position, PieceModel<Species> piece){
        Pos[] matchPos = new Pos[0];
        for(Board.Dir d : Board.Dir.values()) {
            List<Pos> candidateMatch = new ArrayList<>();
            Pos currPosition = board.adjacent(position, d);
            int n = 0;
            while(currPosition != null && board.get(currPosition) != null && !board.get(currPosition).equals(piece)){
                n++;
                candidateMatch.add(currPosition);
                currPosition = board.adjacent(currPosition, d);
            }

            if(currPosition != null && board.get(currPosition) != null && board.get(currPosition).equals(piece) && n>0){
                for(Pos posInCandidate : candidateMatch){
                    if(!Arrays.asList(matchPos).contains(posInCandidate)){
                        matchPos = Arrays.copyOf(matchPos, matchPos.length + 1);
                        matchPos[matchPos.length - 1] = posInCandidate;
                    }
                }
            }
        }

        if(matchPos.length == 0) return null;
        return matchPos;
    }

    public void printBoard(){
        Board<PieceModel<Species>> board = this.board;
        System.out.println(" -----------------");
        for(int y = this.size-1; y >= 0; y--){
            for(int x = 0; x < this.size; x++){
                if(x == 0) System.out.print("| ");
                Pos position = new Pos(x, y);
                PieceModel<Species> piecem = this.board.get(position);
                String piece = (blackdisc.equals(piecem) ? "N " : (whitedisc.equals(piecem) ? "B " : "- "));
                System.out.print(piece);
                if(x == this.size-1) System.out.print("|");
            }
            System.out.print("\n");
        }
        System.out.println(" -----------------");
    }

    public List<Integer> countPieces(){
        Integer white = 0;
        Integer black = 0;
        for(Pos position : board.positions()){
            if(board.get(position) != null && board.get(position).color == "nero") black++;
            if(board.get(position) != null && board.get(position).color == "bianco") white++;
        }
        List<Integer> countPiece = new ArrayList<Integer>(Arrays.asList(black, white));
        return countPiece;
    }

    public void printAllValidMoves(){
        Set<Move<PieceModel<Species>>> validMovess = validMoves();
        ArrayList<Move<PieceModel<Species>>> validMoves = new ArrayList<>(validMovess);
        Move<PieceModel<Species>> resign = new Move<PieceModel<Species>>(RESIGN);
        validMoves.remove(resign);
        for(Move<PieceModel<Species>> move : validMoves){
            if(move.equals(resign)) System.out.println("ARRENDERSI");
            else System.out.println(move.toString());
        }
    }

    public int movesNumber(){
        return movesList.size();
    }

    public Move<PieceModel<Species>> lastMove(){
        if(movesList.size() == 0) return null;
        return movesList.get(movesList.size()-1);
    }

    public Map<Pos, PieceModel<Species>> getCurrentMap(){
        return this.board.getBoardMap();
    }


    private Map<Move<PieceModel<Species>>,Situation<PieceModel<Species>>> next(Situation<PieceModel<Species>> situation) {
        if(situation == null) throw new NullPointerException("Situazione non valida");
        Map<Move<PieceModel<Species>>, Situation<PieceModel<Species>>> nextMoveSituationMap = new HashMap<>();
        if (situation.turn <= 0) return new HashMap<>();
        List<Move<PieceModel<Species>>> validMoves = new ArrayList<>(Utils.validMoves(situation::get, this.board, situation.turn));
        validMoves.remove(new Move<>(Move.Kind.RESIGN));
        for (Move<PieceModel<Species>> move : validMoves) {
            Map<Pos, PieceModel<Species>> nextSituationMap = situation.newMap();
            Action<PieceModel<Species>> addAction = move.actions.get(0);
            Action<PieceModel<Species>> swapAction = move.actions.get(1);
            nextSituationMap.put(addAction.pos.get(0), addAction.piece);
            for (Pos p : swapAction.pos)
                nextSituationMap.put(p, swapAction.piece);
            int nextSituationTurn = 3 - situation.turn;
            if ( Utils.validMoves(nextSituationMap::get, this.board, nextSituationTurn).size() == 1) {
                nextSituationTurn = 3 - nextSituationTurn;
                if (Utils.validMoves(nextSituationMap::get, this.board, nextSituationTurn).size() == 1) {
                    nextSituationTurn = Utils.andTheWinnerIs(nextSituationMap);
                }
            }
            nextMoveSituationMap.put(move, new Situation<>(nextSituationMap, nextSituationTurn));
        }
        if (nextMoveSituationMap.isEmpty()) return null;
        return nextMoveSituationMap;
    }

    private static String millisToSM(long millis) {
        if (millis <= 0) return "No limit";
        if (millis < 60_000)
            return (millis/1000)+"s";
        else
            return (millis/60_000)+"m";
    }
}
