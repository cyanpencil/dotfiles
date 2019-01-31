package gapp.ulg.games;

import gapp.ulg.game.board.*;
import gapp.ulg.game.util.BoardOct;
import gapp.ulg.game.util.Utils;

import java.util.*;

import static gapp.ulg.game.board.Move.Kind.RESIGN;
import static gapp.ulg.game.board.PieceModel.Species;
import static java.lang.Integer.max;
import static java.lang.Integer.min;

/** <b>IMPLEMENTARE I METODI SECONDO LE SPECIFICHE DATE NEI JAVADOC. Non modificare
 * le intestazioni dei metodi.</b>
 * <br>
 * Un oggetto {@code MNKgame} rappresenta un GameRuler per fare una partita a un
 * (m,n,k)-game, generalizzazioni del ben conosciuto Tris o Tic Tac Toe.
 * <br>
 * Un gioco (m,n,k)-game si gioca su una board di tipo {@link Board.System#OCTAGONAL}
 * di larghezza (width) m e altezza (height) n. Si gioca con pezzi o pedine di specie
 * {@link Species#DISC} di due colori "nero" e "bianco". All'inizio la board è vuota.
 * Poi a turno ogni giocatore pone una sua pedina in una posizione vuota. Vince il
 * primo giocatore che riesce a disporre almeno k delle sue pedine in una linea di
 * posizioni consecutive orizzontale, verticale o diagonale. Chiaramente non è
 * possibile passare il turno e una partita può finire con una patta.
 * <br>
 * Per ulteriori informazioni si può consultare
 * <a href="https://en.wikipedia.org/wiki/M,n,k-game">(m,n,k)-game</a> */
public class MNKgame implements GameRuler<PieceModel<Species>> {

    // ----------------------------------------> VARIABILI DEL GIOCO <---------------------------------------------

    public final BoardOct<PieceModel<Species>> board;
    private final Board<PieceModel<Species>> unModofiableBoard;
    private List<Move<PieceModel<Species>>> movesList = new ArrayList<>();

    private final List<String> playerNames;

    private final int m;        // width
    private final int n;        // height
    private final int k;        // strike length
    private final long time;     // time

    public int turn = 1, res = -1;

    private static PieceModel<PieceModel.Species> blackdisc = new PieceModel<>(PieceModel.Species.DISC, "nero");           // white disc
    private static PieceModel<PieceModel.Species> whitedisc = new PieceModel<>(PieceModel.Species.DISC, "bianco");         // black disc

    private final HashMap<Pos, PieceModel<Species>> initialMap;
    private final Situation<PieceModel<Species>> initialSituation;
    private final Mechanics<PieceModel<Species>> MNKMechanics;
    // ------------------------------------> FINE VARIABILI DEL GIOCO <--------------------------------------------


    /** Crea un {@code MNKgame} con le impostazioni date.
     * @param time  tempo in millisecondi per fare una mossa, se <= 0 significa nessun
     *              limite
     * @param m  larghezza (width) della board
     * @param n  altezza (height) della board
     * @param k  lunghezza della linea
     * @param p1  il nome del primo giocatore
     * @param p2  il nome del secondo giocatore
     * @throws NullPointerException se {@code p1} o {@code p2} è null
     * @throws IllegalArgumentException se i valori di {@code m,n,k} non soddisfano
     * le condizioni 1 <= {@code k} <= max{{@code M,N}} <= 20 e 1 <= min{{@code M,N}} */
    public MNKgame(long time, int m, int n, int k, String p1, String p2) {

        if(p1 == null | p2 == null) throw new NullPointerException("Uno dei due giocatori è nullo");
        if(max(m, n) > 20 | max(m, n) < k | k < 1 | min(m , n) < 1 ) throw new IllegalArgumentException("Uno dei parametri non è valido");

        this.time = time;
        this.m = m;
        this.n = n;
        this.k = k;
        playerNames = Collections.unmodifiableList(new ArrayList<>(Arrays.asList(p1, p2)));

        board = new BoardOct<PieceModel<Species>>(m, n);

        unModofiableBoard = new Board<PieceModel<Species>>() {
            @Override
            public System system() {return board.system();}
            @Override
            public int width() {return m;}
            @Override
            public int height() {return n;}
            @Override
            public Pos adjacent(Pos p, Dir d) {return board.adjacent(p, d);}
            @Override
            public List<Pos> positions() {return board.positions();}
            @Override
            public PieceModel<Species> get(Pos p) {return board.get(p);}
        };


        Next<PieceModel<Species>> getNext = (situation) -> {
            if (situation == null) throw new NullPointerException("Situazione nulla");       // Se la situazione è nulla lancia eccezione
            if (situation.turn <= 0) return new HashMap<>();                                 // Se la situazione data è finale ritorna la mappa vuota

            Map<Move<PieceModel<Species>>, Situation<PieceModel<Species>>> nextMap = new HashMap<>();

            ArrayList<Move<PieceModel<Species>>> validMoves = new ArrayList<>(Utils.MNKValidMovesSituation(situation, this.m, this.n));        // Mosse valide
            Move<PieceModel<Species>> resign = new Move<PieceModel<Species>>(RESIGN);
            validMoves.remove(resign);                                                              // Senza il resign

            for(Move<PieceModel<Species>> move : validMoves){
                Situation<PieceModel<Species>> moveSituation = Utils.MNKMoveSituation(situation, move, this.m, this.n, this.k);
                if(moveSituation != null) nextMap.put(move, moveSituation);
            }
            return nextMap;
        };

        initialMap = board.getBoardMapWithoutEmpties();
        initialSituation = new Situation<>(initialMap, 1);
        List<PieceModel<Species>> piecesList = new ArrayList<>(Arrays.asList(blackdisc, whitedisc));
        MNKMechanics = new Mechanics<>(time, Collections.unmodifiableList(piecesList), board.positions(), 2, initialSituation, getNext);
    }

    /** Il nome rispetta il formato:
     * <pre>
     *     <i>M,N,K</i>-game
     * </pre>
     * dove <code><i>M,N,K</i></code> sono i valori dei parametri M,N,K, ad es.
     * "4,5,4-game". */
    @Override
    public String name() { return m + "," + n + "," + k + "-game"; }

    @Override
    public <T> T getParam(String name, Class<T> c) {
        if(name == null || c == null) throw new NullPointerException("Inserire un nome e un classe non nulli");
        if(name != "Time" && name != "M" && name != "N" && name != "K") throw new IllegalArgumentException("Inserire il nome di un parametro valido");

        // TODO da cambiare perchè bisogna verificare le coppie m,n,k -> Integer e Time -> String
        if(c != String.class && c != Integer.class) throw new ClassCastException("Classe non corrispondente");

        if(name == "M") return (T) (Integer.valueOf(this.n));
        if(name == "N") return (T) (Integer.valueOf(this.m));
        if(name == "K") return (T) (Integer.valueOf(this.k));
        long realtime = this.time > 0 ? this.time : -1;
        int index = TO_MS.indexOf((int) realtime);
        return (T) (TIMES.get(index));
    }

    private static final List<String> TIMES = Arrays.asList("No limit","1s","2s","3s","5s","10s","20s","30s","1m","2m","5m");
    private static final List<Integer> TO_MS = Arrays.asList(-1,1000,2000,3000,5000,10000,20000,30000,60000,120000,300000);

    @Override
    public List<String> players() { return playerNames; }

    /** @return il colore "nero" per il primo giocatore e "bianco" per il secondo */
    @Override
    public String color(String name) {
        if(name == null) throw new NullPointerException("Inserire un nome non nullo");
        if(!playerNames.contains(name)) throw new IllegalArgumentException("Non è un giocatore della partita");
        String color = playerNames.indexOf(name) == 0 ? "nero" : "bianco";
        return color;
    }

    @Override
    public Board<PieceModel<Species>> getBoard() { return unModofiableBoard; }

    @Override
    public int turn() {
        if(this.res != -1) return 0;
        return this.turn;
    }

    /** Se la mossa non è valida termina il gioco dando la vittoria all'altro
     * giocatore.
     * Se dopo la mossa la situazione è tale che nessuno dei due giocatori può
     * vincere, si tratta quindi di una situazione che può portare solamente a una
     * patta, termina immediatamente il gioco con una patta. Per determinare se si
     * trova in una tale situazione controlla che nessun dei due giocatori può
     * produrre una linea di K pedine con le mosse rimanenti (in qualsiasi modo siano
     * disposte le pedine rimanenti di entrambi i giocatori). */
    @Override
    public boolean move(Move<PieceModel<Species>> m) {
        if(m == null) throw new NullPointerException("Mossa nulla");
        if(this.res != -1 | this.turn() == 0) throw new IllegalStateException("Partita terminata");
        // TODO CONTROLLARE UNMOVE IN PARTITA CONCLUSA PER MOSSA INVALIDA (PURE IN OTHELLO)
        if(!isValid(m)){
            this.res = 3 - this.turn;
            this.turn = 3 - this.turn;
            return false;
        }
        movesList.add(m);
        if(m.kind == Move.Kind.RESIGN){
            this.res = 3 - this.turn;
            this.turn = 3 - this.turn;
            return false;
        }
        else {
            PieceModel<Species> piece = m.actions.get(0).piece;
            Pos position = m.actions.get(0).pos.get(0);
            this.board.put(piece, position);
            if(findMatches(position, false) != null){          // In caso di vittoria
                this.res = this.turn;
                this.turn = 3 - this.turn;
                return true;
            }
            this.turn = 3 - this.turn;
            if(!isWinnable()){
                res = 0;
            }
        }
        return true;
    }

    public boolean isWinnable(){


        List<Pos> emptyPositions = new ArrayList<>();
        for(Pos p : board.positions()){
            if(board.get(p) == null) emptyPositions.add(p);
        }

        for(Pos p : this.board.positions()){
            boolean colored = false;
            String color = "";

            List<Pos> match = findMatches(p, true);
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
                    int needMissing = (colored == true ? (color(playerNames.get(this.turn-1)) != color ? 2*matchMI.size() : ((2*matchMI.size())-1)) : ((2*matchMI.size())-1));


                    if(emptyPositions.size() >= needMissing){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean unMove() {
        if(movesList.size() == 0){
            return false;
        }

        Move<PieceModel<Species>> lastMove = movesList.get(movesList.size()-1);

        if(lastMove.kind == Move.Kind.RESIGN){
            movesList.remove(lastMove);
            this.res = -1;
            this.turn = 3 - this.turn;
            return true;
        }

        if(res != -1) res = -1;
        Pos p = lastMove.actions.get(0).pos.get(0);
        this.board.remove(p);
        movesList.remove(lastMove);
        this.turn = 3 - this.turn;

        return true;
    }

    @Override
    public boolean isPlaying(int i) {
        if (i != 1 && i != 2) throw new IllegalArgumentException();
        if (res != -1) return false;
        return true;
    }

    @Override
    public int result() { return this.res; }

    /** Ogni mossa (diversa dall'abbandono) è rappresentata da una sola {@link Action}
     * di tipo {@link Action.Kind#ADD}. */
    @Override
    public Set<Move<PieceModel<Species>>> validMoves() {
        if(res != -1 | this.turn() == 0) throw new IllegalStateException("La partita è terminata");
        PieceModel<Species> piece = (turn == 1 ? blackdisc : whitedisc);

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

    @Override
    public GameRuler<PieceModel<Species>> copy() {
        return new MNKgame(this);
    }

    @Override
    public Mechanics<PieceModel<Species>> mechanics() { return MNKMechanics; }

    // ------------------------------> INIZIO METODI DI UTILITA' <-------------------------------------
    public MNKgame(MNKgame g){

        board = new BoardOct<PieceModel<Species>>(g.board);

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
        this.m = g.m;
        this.n = g.n;
        this.k = g.k;

        this.initialMap = g.initialMap;
        this.initialSituation = g.initialSituation;
        this.MNKMechanics = g.MNKMechanics;
    }

    public List<Pos> findMatches(Pos startPosition, boolean wnull){
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

    public void printBoard(){
        for(int y = this.n-1; y >= 0; y--){
            for(int x = 0; x < m; x++){
                Pos position = new Pos(x, y);
                String piece = (this.board.get(position) == null ? "- " : (this.board.get(position).equals(whitedisc) ? "B " : "N "));
                System.out.print(piece);
            }
            System.out.print("\n");
        }
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
}
