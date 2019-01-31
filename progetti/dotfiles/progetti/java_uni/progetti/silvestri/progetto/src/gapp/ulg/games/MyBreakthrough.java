package gapp.ulg.games;

import gapp.ulg.Utilities.UsefulMethods;
import gapp.ulg.game.board.*;
import gapp.ulg.game.board.Board.Dir;
import gapp.ulg.game.board.PieceModel.Species;
import gapp.ulg.game.util.BoardOct;

import java.util.*;
import java.util.function.Function;

import static gapp.ulg.game.board.Board.Dir.*;
import static gapp.ulg.game.board.Move.Kind.RESIGN;

/**
 * Created by Edoardo & Luca on 02/08/2016.
 *
 * Da fare:
 *      1) Aggiungere patta per stallo
 *      2) Ottimizzare i punti segnati
 */
public class MyBreakthrough implements GameRuler<PieceModel<Species>> {

    private PieceModel[] whitePieces = {
            new PieceModel(Species.PAWN, "bianco")
    };

    private PieceModel<Species>[] blackPieces = new PieceModel[]{
            new PieceModel(Species.PAWN, "nero")
    };

    private BoardOct<PieceModel<Species>> board;
    private final Board<PieceModel<Species>> unModifiableBoard;
    private final List<String> playerNames;
    private int res = -1, turn = 1;
    private final long time;

    int[] count = {16, 16};

    private final Mechanics<PieceModel<Species>> mechanics;

    List<Move<PieceModel<Species>>> moveHistory = new ArrayList<>();

    public MyBreakthrough(long time, String p1, String p2){
        if(p1 == null || p2 == null) throw new NullPointerException("Almeno uno dei due giocatori non è un giocatore valido");
        this.time = time; playerNames = Collections.unmodifiableList(Arrays.asList(p1, p2)); this.board = new BoardOct<>(8, 8);

        for(int i = 0; i <= 7; i++){
            board.put(whitePieces[0], new Pos(i, 0));
            board.put(whitePieces[0], new Pos(i, 1));

            board.put(blackPieces[0], new Pos(i, 6));
            board.put(blackPieces[0], new Pos(i, 7));
        }


        unModifiableBoard = new Board<PieceModel<Species>>() {
            @Override
            public System system() {return board.system();}
            @Override
            public int width() {return 8;}
            @Override
            public int height() {return 8;}
            @Override
            public Pos adjacent(Pos p, Dir d) {return board.adjacent(p, d);}
            @Override
            public List<Pos> positions() {return board.positions();}
            @Override
            public PieceModel<Species> get(Pos p) {return board.get(p);}
        };

        List<PieceModel<Species>> piecesList = new ArrayList<>();

        piecesList.add(whitePieces[0]);
        piecesList.add(blackPieces[0]);
        mechanics = new Mechanics<>(time, piecesList, board.positions(), 2, new Situation<>(board.getBoardMap(), 1), this::next);

    }

    private MyBreakthrough(MyBreakthrough gR){
        this.time = gR.time;
        this.playerNames = gR.playerNames;

        this.turn = gR.turn;
        this.res = gR.res;

        // TODO IMPORTANTE ricontrolla questo metodo
        this.board = new BoardOct<>(gR.board);

        unModifiableBoard = new Board<PieceModel<Species>>() {
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

        mechanics = gR.mechanics();

    }

    @Override
    public String name() {
        return "Scacchi";
    }

    // Per la conversione da intero a stringa del parametro time
    private static final List<String> TIMES = Arrays.asList("No limit","1s","2s","3s","5s","10s","20s","30s","1m","2m","5m");
    private static final List<Integer> TO_MS = Arrays.asList(-1,1000,2000,3000,5000,10000,20000,30000,60000,120000,300000);

    @Override
    public <T> T getParam(String name, Class<T> c) {
        if(name == null || c == null) throw new NullPointerException("Invalid name or class.");
        if(!name.equals("Time")) throw new IllegalArgumentException("Invalid parameter name.");
        long realtime = this.time > 0 ? this.time : -1;
        int index = TO_MS.indexOf((int) realtime);
        return (T) (TIMES.get(index));
    }

    @Override
    public List<String> players() {
        return playerNames;
    }

    @Override
    public String color(String name) {
        if(name == null) throw new NullPointerException("Inserire un nome non nullo");
        if(!playerNames.contains(name)) throw new IllegalArgumentException("Non è un giocatore della partita");
        return playerNames.indexOf(name) == 0 ? "nero" : "bianco";
    }

    @Override
    public Board<PieceModel<Species>> getBoard() {
        return unModifiableBoard;
    }

    @Override
    public int turn() {
        if(this.res != -1) return 0;
        return this.turn;
    }

    @Override
    public boolean move(Move<PieceModel<Species>> m) {
        // 1) Se la mossa non è valida lancia eccezione
        // 2) Altrimenti esegui la mossa e
        //      a) controlla se è scacco. Se sì controlla se ci sono mosse per contrastarlo altrimenti assegna la vittoria all'altro giocatore
        // 3) Todo: controlla patta per stallo
        if(m == null) throw new NullPointerException("Mossa nulla");
        if(this.res != -1 | this.turn() == 0) throw new IllegalStateException("Partita terminata");


        //Todo: ottimizza
        // Se la mossa non è valida vince l'altro giocatore
        if(!isValid(m)){
            this.res = 3 - this.turn;
            this.turn = 3 - this.turn;
            return false;
        }

        moveHistory.add(m);

        // Se la mossa è RESIGN assegna la vittoria all'altro giocatore
        if(m.kind == Move.Kind.RESIGN){
            this.res = 3 - this.turn;
            this.turn = 3 - this.turn;
            return true;
        }

        // MOVE, REMOVE (cattura), JUMP (cavallo), SWAP (promozione)
        // TODO ottimizza
        for(Action<PieceModel<Species>> action : m.actions){

            //Action MOVE: rimuovi il pezzo dalla sua casa base e aggiungilo nella casella di arrivo
            if(action.kind == Action.Kind.MOVE){
                Pos currentPos = action.pos.get(0);
                Pos finalPos = UsefulMethods.calcPos(currentPos, board::adjacent, action.dir, action.steps);
                PieceModel<Species> piece = board.remove(currentPos);
                board.put(piece, finalPos);
                if(this.isFinalRow(finalPos, piece)){
                    this.res = this.turn;
                    this.turn = 3 - this.turn;
                    System.out.println("Ha vinto il giocatore numero: " + this.res);
                    return true;
                }
            }

            // Action REMOVE: rimuovi il pezzo dalla sua posizione
            if(action.kind == Action.Kind.REMOVE) {
                board.remove(action.pos.get(0));
            }
        }
        turn = 3 - turn;
        return true;
    }



    @Override
    public boolean unMove() {
        return false;
    }

    @Override
    public boolean isPlaying(int i) {
        return false;
    }

    @Override
    public int result() {
        return this.res;
    }

    @Override
    public Set<Move<PieceModel<Species>>> validMoves() {
        if (res != -1) throw new IllegalStateException();

        Set<Move<PieceModel<Species>>> validMoves = validMoves(board::get, this.turn);
        Situation<PieceModel<Species>> currentSituation = map(board::get, this.turn);

        validMoves.add(new Move<>(Move.Kind.RESIGN));

        return Collections.unmodifiableSet(validMoves);
    }

    /** Ritorna l'insieme delle mosse valide (esclusa {@link Move.Kind#RESIGN}) per
     * il giocatore con indice di turnazione dato e la disposizione dei pezzi data
     * dalla funzione specificata.
     * @param f  funzione che per ogni posizione della board ritorna il pezzo in
     *           quella posizione o null se non c'è
     * @param turn  indice di turnazione del giocatore che deve muovere
     * @return l'insieme delle mosse valide (esclusa {@link Move.Kind#RESIGN}) per
     * il giocatore con indice di turnazione dato e la disposizione dei pezzi data
     * dalla funzione specificata. */
    private Set<Move<PieceModel<Species>>> validMoves(Function<Pos,PieceModel<Species>> f, int turn) {

        Set<Move<PieceModel<Species>>> validMoves = new HashSet<>();
        PieceModel<Species> currentPawn = turn == 1 ? whitePieces[0] : blackPieces[0];
        PieceModel<Species> oppositePawn = turn == 2 ? whitePieces[0] : blackPieces[0];

        Dir currentPawnDir = turn == 1 ? Dir.UP : Dir.DOWN; // Se è un pedone bianco va verso l'alto, se è un pedone nero va verso il basso
        List<Dir> captureDirs = new ArrayList<>(turn == 1 ? Arrays.asList(UP_L, UP_R) : Arrays.asList(DOWN_L, DOWN_R));


        for(Pos position : board.positions()){
            PieceModel<Species> currPiece = f.apply(position);
            if(currPiece != null && currPiece.equals(currentPawn)){
                Pos straight = board.adjacent(position, currentPawnDir);
                if(straight != null && f.apply(straight) == null) validMoves.add(new Move<>(new Action(currentPawnDir, 1, position)));
                for(Dir direction : captureDirs){
                    Pos capture = board.adjacent(position, direction);
                    if(capture != null && f.apply(capture) == null) validMoves.add(new Move<>(new Action(direction, 1, position)));
                    else if(capture != null && f.apply(capture).equals(oppositePawn)) validMoves.add(new Move<>(new Action(capture), new Action(direction, 1, position)));
                }
            }
        }
        return validMoves;

    }

    @Override
    public double score(int i) {
        // TODO CREARE SCACCHI BIN
        return 0;
    }

    @Override
    public GameRuler<PieceModel<Species>> copy() {
        return new MyBreakthrough(this);
    }

    @Override
    public Mechanics<PieceModel<Species>> mechanics() {
        return mechanics;
    }

    // TODO: fai next
    private Map<Move<PieceModel<Species>>,Situation<PieceModel<Species>>> next(Situation<PieceModel<Species>> s) {
        Objects.requireNonNull(s);
        Map<Move<PieceModel<Species>>, Situation<PieceModel<Species>>> sMap = new HashMap<>();
        if (s.turn <= 0) return sMap;
        for (Move<PieceModel<Species>> m : validMoves(s::get, s.turn)) {
            Map<Pos, PieceModel<Species>> cNext = s.newMap();

            // TODO: provare il cambio con situationMove(m, s);
            for(Action<PieceModel<Species>> action : m.actions){
                if(action.kind == Action.Kind.MOVE){
                    Pos currentPos = action.pos.get(0);
                    Pos finalPos = UsefulMethods.calcPos(currentPos, board::adjacent, action.dir, action.steps);
                    cNext.put(finalPos, cNext.remove(currentPos));
                }

                // Action REMOVE: rimuovi il pezzo dalla sua posizione
                if(action.kind == Action.Kind.REMOVE) {
                    cNext.remove(action.pos.get(0));
                }
            }
        }
        if (sMap.isEmpty()) return null;   // Situazione non valida
        return sMap;
    }

    private boolean isFinalRow(Pos piecePosition, PieceModel<Species> pawn){
        return (pawn.color.equals("bianco") && piecePosition.t == 7) || (pawn.color.equals("nero") && piecePosition.t == 0);
    }

    private Pos lastPosLastAction(Move<PieceModel<Species>> move){
        Action<PieceModel<Species>> lastAction = move.actions.get(move.actions.size()-1);
        return lastAction.pos.get(lastAction.pos.size()-1);
    }

    private Situation<PieceModel<Species>> map(Function<Pos, PieceModel<Species>> f, int turn){
        Map<Pos, PieceModel<Species>> situationMap = new HashMap<>();
        for(Pos position : board.positions()){
            PieceModel<Species> piece = f.apply(position);
            if( piece != null) situationMap.put(position, piece);
        }
        return new Situation<>(situationMap, turn);
    }

    public void printBoard(){
        Board<PieceModel<Species>> board = this.board;
        System.out.println(" -------------------------");
        for(int y = this.board.height()-1; y >= 0; y--){
            for(int x = 0; x < this.board.width(); x++){
                if(x == 0) System.out.print("| ");
                Pos position = new Pos(x, y);
                PieceModel<Species> piecem = this.board.get(position);
                String piece = piecem == null ? "-  " : (piecem.color.equals("bianco") ? (piecem.species.toString().substring(0, 1).toUpperCase() + "  ") : (piecem.species.toString().substring(0, 1).toLowerCase() + "  ") );
                System.out.print(piece);
                if(x == this.board.width()-1) System.out.print("|");
            }
            System.out.print("\n");
        }
        System.out.println(" -------------------------");
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
