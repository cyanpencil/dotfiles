package gapp.ulg.games;

import gapp.ulg.Utilities.UsefulMethods;
import gapp.ulg.game.board.*;
import gapp.ulg.game.board.Board.Dir;
import gapp.ulg.game.board.PieceModel.Species;
import gapp.ulg.game.util.BoardOct;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static gapp.ulg.game.board.Board.Dir.*;
import static gapp.ulg.game.board.Move.Kind.RESIGN;

/**
 * Created by Edoardo & Luca on 02/08/2016.
 *
 * Da fare:
 *      1) Aggiungere patta per stallo
 *      2) Ottimizzare i punti segnati
 */
public class MyCamelot implements GameRuler<PieceModel<Species>> {

    private PieceModel[] whitePieces = {
            new PieceModel(Species.PAWN, "bianco"),
            new PieceModel(Species.KNIGHT, "bianco")
    };

    private PieceModel<Species>[] blackPieces = new PieceModel[]{
            new PieceModel(Species.PAWN, "nero"),
            new PieceModel(Species.KNIGHT, "nero")
    };

    private BoardOct<PieceModel<Species>> board;
    private final Board<PieceModel<Species>> unModifiableBoard;
    private final List<String> playerNames;
    private int res = -1, turn = 1;
    private final long time;

    int[] count = {16, 16};

    private final Mechanics<PieceModel<Species>> mechanics;

    List<Move<PieceModel<Species>>> moveHistory = new ArrayList<>();

    public MyCamelot(long time, String p1, String p2){
        if(p1 == null || p2 == null) throw new NullPointerException("Almeno uno dei due giocatori non è un giocatore valido");
        this.time = time; playerNames = Collections.unmodifiableList(Arrays.asList(p1, p2));

        List<Pos> excludedList = new ArrayList<>();
        for(int y = 0; y < 16; y++){
            for(int x = 0; x < 13; x++){
                if((y == 0 || y == 15) && ((x >= 0 && x <= 4) || (x >= 7 && x <= 11))){
                    excludedList.add(new Pos(x, y));
                }
                else if((y == 1 || y == 14) && (x == 0 || x == 1 || x >= 10)){
                    excludedList.add(new Pos(x, y));
                }
                else if((y == 2 || y == 13) && (x == 0 || x >= 11)) excludedList.add(new Pos(x, y));
            }
        }
        this.board = new BoardOct<PieceModel<Species>>(12, 16, excludedList);

        for(int i = 2; i <= 9; i++){
            if(i == 2 || i == 9){
                board.put(whitePieces[1], new Pos(i, 5));
                board.put(blackPieces[1], new Pos(i, 10));
            }
            else if(i == 3 || i == 8){
                board.put(whitePieces[0], new Pos(i, 5));
                board.put(whitePieces[1], new Pos(i, 6));

                board.put(blackPieces[0], new Pos(i, 10));
                board.put(blackPieces[1], new Pos(i, 9));
            }

            else {
                board.put(whitePieces[0], new Pos(i, 5));
                board.put(whitePieces[0], new Pos(i, 6));

                board.put(blackPieces[0], new Pos(i, 10));
                board.put(blackPieces[0], new Pos(i, 9));
            }

        }


        unModifiableBoard = new Board<PieceModel<Species>>() {
            @Override
            public System system() {return board.system();}
            @Override
            public int width() {return 12;}
            @Override
            public int height() {return 16;}
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

    private MyCamelot(MyCamelot gR){
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
        return "MyCamelot";
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
        return playerNames.indexOf(name) == 0 ? "bianco" : "nero"; // TODO: da cambiare
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

            if(action.kind == Action.Kind.JUMP){
                Pos currentPos = action.pos.get(0);
                Pos finalPos = action.pos.get(1);
                board.put(board.remove(currentPos), finalPos);
            }

            //Action MOVE: rimuovi il pezzo dalla sua casa base e aggiungilo nella casella di arrivo
            if(action.kind == Action.Kind.MOVE){
                Pos currentPos = action.pos.get(0);
                Pos finalPos = UsefulMethods.calcPos(currentPos, board::adjacent, action.dir, action.steps);
                PieceModel<Species> piece = board.remove(currentPos);
                board.put(piece, finalPos);
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

        Set<Move<PieceModel<Species>>> allValidMoves = validMoves(board::get, this.turn);

        Set<Move<PieceModel<Species>>> captureMoves = allValidMoves.stream().filter(move -> {
            if(move.actions.size() > 1 && move.actions.get(1).kind.equals(Action.Kind.REMOVE)) return true;
            return false;
        }).collect(Collectors.toCollection(HashSet::new));

        captureMoves.add(new Move<>(Move.Kind.RESIGN));
        allValidMoves.add(new Move<>(Move.Kind.RESIGN));

        return captureMoves.size() == 1 ? Collections.unmodifiableSet(allValidMoves) : Collections.unmodifiableSet(captureMoves);
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
        PieceModel<Species> currentKnight = turn == 1 ? whitePieces[1] : blackPieces[1];

        for(Pos position : board.positions()){
            PieceModel<Species> currPiece = f.apply(position);
            haveEated = false;
            friends = false;
            if(currPiece != null && currPiece.equals(currentPawn)) {
                validMoves.addAll(pawnValidMoves(f, new ArrayList<>(), position, new ArrayList<>()));
            }
            if(currPiece != null && currPiece.equals(currentKnight)) {
                validMoves.addAll(knightValidMoves(f, new ArrayList<>(), position, new ArrayList<>()));
            }
        }
        haveEated = false; friends = false;
        return validMoves;

    }

    boolean haveEated = false, captured = false;
    List<Move<PieceModel<Species>>> knightValidMoves(Function<Pos, PieceModel<Species>> f,
                                                     List<Action<PieceModel<Species>>> currentMove, Pos currentPosition, ArrayList<Pos> eated){
        List<Move<PieceModel<Species>>> validMoves = new ArrayList<>();
        List<Move<PieceModel<Species>>> childCaptureMoves = new ArrayList<>();
        List<Move<PieceModel<Species>>> childJumpMoves = new ArrayList<>();


        PieceModel<Species> currentKnight = turn == 1 ? whitePieces[1] : blackPieces[1];

        String currentColor = turn == 1 ? "bianco" : "nero";
        String opponentColor = turn == 1 ? "nero" : "bianco";

        for(Dir direction : Dir.values()){
            Pos firstAdjPosition = board.adjacent(currentPosition, direction);
            if(firstAdjPosition != null){
                Pos secondAdjPosition = board.adjacent(firstAdjPosition, direction);
                if(secondAdjPosition != null && f.apply(firstAdjPosition) != null){
                    if(f.apply(firstAdjPosition).color.equals(opponentColor) && f.apply(secondAdjPosition) == null && !(eated.contains(firstAdjPosition))){
                        captured = true;
                        ArrayList<Pos> childEaten = new ArrayList<>(eated);
                        childEaten.add(firstAdjPosition);
                        // Controllare se funziona sta cosa altrimenti aggiungere il metodo .copy()
                        List<Action<PieceModel<Species>>> newMove = new ArrayList<>(currentMove);
                        newMove.addAll(Arrays.asList(new Action<>(currentPosition, secondAdjPosition), new Action<>(firstAdjPosition)));
                        haveEated = true;
                        List<Move<PieceModel<Species>>> childSubMoves = knightValidMoves(f, newMove, secondAdjPosition, childEaten);
                        haveEated = false;
                        childCaptureMoves.addAll(childSubMoves);
                        if(childSubMoves.size() == 0) validMoves.add(new Move<>(newMove));
                    }
                    if(f.apply(firstAdjPosition).color.equals(currentColor) && f.apply(secondAdjPosition) == null && !(eated.contains(firstAdjPosition)) && haveEated == false){
                        ArrayList<Pos> childEaten = new ArrayList<>(eated);
                        childEaten.add(firstAdjPosition);
                        // Controllare se funziona sta cosa altrimenti aggiungere il metodo .copy()
                        List<Action<PieceModel<Species>>> newMove = new ArrayList<>(currentMove);
                        newMove.addAll(Arrays.asList(new Action<>(currentPosition, secondAdjPosition)));
                        captured = false;
                        List<Move<PieceModel<Species>>> childSubMoves = knightValidMoves(f, newMove, secondAdjPosition, childEaten);
                        //if (captured) childJumpMoves.addAll(childSubMoves);
                        childJumpMoves.addAll(childSubMoves);
                        boolean nextLevelCapture = childSubMoves.stream().filter(move -> {
                            List<Action<PieceModel<Species>>> actionList = move.actions;
                            int nextIndex = newMove.size()+1;
                            if(actionList.size() <= nextIndex) return false;
                            return actionList.get(nextIndex).kind.equals(Action.Kind.REMOVE);
                        }).findAny().isPresent();
                        if(!nextLevelCapture){
                            childJumpMoves.add(new Move<>(newMove));
                        }
                    }
                }
            }
        }
        // TODO:  da rimettere
        if (childCaptureMoves.size() != 0) validMoves.addAll(childCaptureMoves);
        else {
            validMoves.addAll(childJumpMoves);
            if (currentMove.size() == 0) validMoves.addAll(kingValidMoves(f, currentPosition));
        }
        return validMoves;
    }


    boolean friends = false;
    List<Move<PieceModel<Species>>> pawnValidMoves(Function<Pos, PieceModel<Species>> f,
                                                     ArrayList<Action<PieceModel<Species>>> currentMove, Pos currentPosition, ArrayList<Pos> eated){
        List<Move<PieceModel<Species>>> validMoves = new ArrayList<>();
        List<Move<PieceModel<Species>>> childCaptureMoves = new ArrayList<>();
        List<Move<PieceModel<Species>>> childJumpMoves = new ArrayList<>();


        PieceModel<Species> currentKnight = turn == 1 ? whitePieces[0] : blackPieces[0];

        String currentColor = turn == 1 ? "bianco" : "nero";
        String opponentColor = turn == 1 ? "nero" : "bianco";

        for(Dir direction : Dir.values()){
            Pos firstAdjPosition = board.adjacent(currentPosition, direction);
            if(firstAdjPosition != null){
                Pos secondAdjPosition = board.adjacent(firstAdjPosition, direction);
                if(secondAdjPosition != null && f.apply(firstAdjPosition) != null){
                    if(f.apply(firstAdjPosition).color.equals(opponentColor) && f.apply(secondAdjPosition) == null && !(eated.contains(firstAdjPosition)) && (!friends)){
                        ArrayList<Pos> childEaten = new ArrayList<>(eated);
                        childEaten.add(firstAdjPosition);
                        // Controllare se funziona sta cosa altrimenti aggiungere il metodo .copy()
                        List<Action<PieceModel<Species>>> newMove = new ArrayList<>(currentMove);
                        newMove.addAll(Arrays.asList(new Action<>(currentPosition, secondAdjPosition), new Action<>(firstAdjPosition)));
                        List<Move<PieceModel<Species>>> childSubMoves = knightValidMoves(f, newMove, secondAdjPosition, childEaten);
                        childCaptureMoves.addAll(childSubMoves);
                        if(childSubMoves.size() == 0) validMoves.add(new Move<>(newMove));
                    }
                    if(f.apply(firstAdjPosition).color.equals(currentColor) && f.apply(secondAdjPosition) == null && !(eated.contains(firstAdjPosition)) && (friends || currentMove.size() == 0)){
                        ArrayList<Pos> childEaten = new ArrayList<>(eated);
                        childEaten.add(firstAdjPosition);
                        // Controllare se funziona sta cosa altrimenti aggiungere il metodo .copy()
                        List<Action<PieceModel<Species>>> newMove = new ArrayList<>(currentMove);
                        newMove.addAll(Arrays.asList(new Action<>(currentPosition, secondAdjPosition)));
                        friends = true;
                        List<Move<PieceModel<Species>>> childSubMoves = knightValidMoves(f, newMove, secondAdjPosition, childEaten);
                        if (currentMove.size() == 0) friends = false;

                        //friends = false;
                        childJumpMoves.addAll(childSubMoves);
                        childJumpMoves.add(new Move<>(newMove));
                    }
                }
            }
        }
        if (childCaptureMoves.size() != 0) validMoves.addAll(childCaptureMoves);
        else {
            validMoves.addAll(childJumpMoves);
            if (currentMove.size() == 0) validMoves.addAll(kingValidMoves(f, currentPosition));
        }

        return validMoves;
    }

    private List<Move<PieceModel<Species>>> kingValidMoves(Function<Pos, PieceModel<Species>> f, Pos kingPosition){
        List<Move<PieceModel<Species>>> validMoves = new ArrayList<>();

     //   String opponentColor = f.apply(kingPosition).color.equals("bianco") ? "nero" : "bianco";
        List<Dir> kingDirections = new ArrayList<>(Arrays.asList(UP, DOWN, LEFT, RIGHT, UP_R, UP_L, DOWN_R, DOWN_L));
        for(Dir direction : kingDirections){
            Pos finalPos = UsefulMethods.calcPos(kingPosition, board::adjacent, direction, 1);
            if(finalPos != null && f.apply(finalPos) == null){
                validMoves.add(new Move<>(new Action(direction, 1, kingPosition))); // Mosse di spostamento
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
        return new MyCamelot(this);
    }

    @Override
    public Mechanics<PieceModel<Species>> mechanics() {
        return mechanics;
    }

    // Non funziona per lo stesso motivo di scacchi + è sbagliato
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

    // --------------------------> Metodi di utilità <--------------------------
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
    // --------------------------> Metodi di utilità <--------------------------

    public void printBoard(){
        Board<PieceModel<Species>> board = this.board;
        System.out.println("\n");
        for(int y = this.board.height()-1; y >= 0; y--){
            for(int x = 0; x < this.board.width(); x++){
                if(x == 0){
                    if(((Integer)y).toString().length() == 2) System.out.print(y + " ");
                    else System.out.print(y + "  ");
                }
                Pos position = new Pos(x, y);
                if(!board.isPos(position)){
                    System.out.print("#  ");
                }
                else {
                    PieceModel<Species> piecem = this.board.get(position);
                    String piece = piecem == null ? "-  " : (piecem.color.equals("bianco") ? (piecem.species.toString().substring(0, 1).toUpperCase() + "  ") : (piecem.species.toString().substring(0, 1).toLowerCase() + "  ") );
                    System.out.print(piece);
                }
            }
            System.out.print("\n");
        }
        System.out.println("   0  1  2  3  4  5  6  7  8  9  10 11");
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
