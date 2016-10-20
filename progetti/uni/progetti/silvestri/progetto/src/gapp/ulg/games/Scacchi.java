package gapp.ulg.games;
import gapp.ulg.Utilities.MyCouple;
import gapp.ulg.game.board.*;
import gapp.ulg.game.board.Board.Dir;
import gapp.ulg.game.util.BoardOct;
import gapp.ulg.game.board.PieceModel.*;
import gapp.ulg.Utilities.UsefulMethods;

import java.lang.System;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static gapp.ulg.game.board.Board.Dir.*;
import static gapp.ulg.game.board.Move.Kind.RESIGN;

 /* Da fare:
 *      1) Aggiungere patta per stallo
 *      2) Ottimizzare i punti segnati
 */
public class Scacchi implements GameRuler<PieceModel<Species>> {

    private PieceModel[] whitePieces = {
            new PieceModel<Species>(Species.KING, "bianco"),
            new PieceModel<Species>(Species.PAWN, "bianco"),
            new PieceModel<Species>(Species.ROOK, "bianco"),
            new PieceModel<Species>(Species.KNIGHT, "bianco"),
            new PieceModel<Species>(Species.BISHOP, "bianco"),
            new PieceModel<Species>(Species.QUEEN, "bianco")
    };

    private PieceModel<Species>[] blackPieces = new PieceModel[]{
            new PieceModel<Species>(Species.KING, "nero"),
            new PieceModel<Species>(Species.PAWN, "nero"),
            new PieceModel<Species>(Species.ROOK, "nero"),
            new PieceModel<Species>(Species.KNIGHT, "nero"),
            new PieceModel<Species>(Species.BISHOP, "nero"),
            new PieceModel<Species>(Species.QUEEN, "nero")
    };

    private BoardOct<PieceModel<Species>> board;
    private final Board<PieceModel<Species>> unModifiableBoard;
    private final List<String> playerNames;
    private int res = -1, turn = 1;
    private final long time;

    private final Mechanics<PieceModel<Species>> mechanics;

    private boolean[] kingSideCastle = {true, true};
    private boolean[] queenSideCastle = {true, true};

    List<Move<PieceModel<Species>>> moveHistory = new ArrayList<>();

    public Scacchi(long time, String p1, String p2){
        if(p1 == null || p2 == null) throw new NullPointerException("Almeno uno dei due giocatori non è un giocatore valido");
        this.time = time; playerNames = Collections.unmodifiableList(Arrays.asList(p1, p2)); this.board = new BoardOct<>(8, 8);

        // TODO DA RIGUARDARE PERCHè SCORRE TUTTA LA BOARD
        board.positions().stream().filter(pos -> pos.t == 1 || pos.t == 6).map(pos -> board.put(pos.t == 1 ? whitePieces[0] : blackPieces[0], pos));
        board.put(whitePieces[2], new Pos(0, 0)); board.put(whitePieces[2], new Pos(7, 0)); // WHITE ROOKS
        board.put(blackPieces[2], new Pos(0, 7)); board.put(blackPieces[2], new Pos(7, 7)); // BLACK ROOKS

        board.put(whitePieces[3], new Pos(1, 0)); board.put(whitePieces[3], new Pos(6, 0)); // WHITE KNIGHTS
        board.put(blackPieces[3], new Pos(1, 7)); board.put(blackPieces[3], new Pos(6, 7)); // BLACK KNIGHTS

        board.put(whitePieces[4], new Pos(2, 0)); board.put(whitePieces[4], new Pos(5, 0)); // WHITE BISHOP
        board.put(blackPieces[4], new Pos(2, 7)); board.put(blackPieces[4], new Pos(5, 7)); // BLACK BIHSOP

        board.put(whitePieces[5], new Pos(3, 0)); // WHITE QUEEN
        board.put(blackPieces[5], new Pos(3, 7)); // BLACK QUEEN

        board.put(whitePieces[0], new Pos(4, 0)); // WHITE KING
        board.put(blackPieces[0], new Pos(4, 7)); // BLACK KING

        for(int i = 0; i <= 7; i++){
            board.put(whitePieces[1], new Pos(i, 1));
            board.put(blackPieces[1], new Pos(i, 6));
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
        for(int i = 0; i <= 5; i++){
            piecesList.add(whitePieces[i]);
            piecesList.add(blackPieces[i]);
        }
        mechanics = new Mechanics<>(time, piecesList, board.positions(), 2, new Situation<>(board.getBoardMap(), 1), this::next);

    }

    private Scacchi(Scacchi gR){
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
        return playerNames.indexOf(name) == 0 ? "bianco" : "nero";
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
    public boolean move(Move<PieceModel<PieceModel.Species>> m) {
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

                // PER GLI ARROCCHI
                Pos kingFirstPosition = this.turn == 1 ? new Pos(4, 0) : new Pos(4, 7);
                Pos KingSideRookPosition = this.turn == 1 ? new Pos(7, 0) : new Pos(7, 7);
                Pos QueenSideRookPosition = this.turn == 1 ? new Pos(0, 0) : new Pos(0, 7);

                if((queenSideCastle[this.turn-1] || kingSideCastle[this.turn-1]) && action.pos.get(0).equals(kingFirstPosition)) {
                    queenSideCastle[this.turn-1] = false;
                    kingSideCastle[this.turn-1] = false;
                }
                else if(queenSideCastle[this.turn-1] && action.pos.get(0).equals(QueenSideRookPosition))
                    queenSideCastle[this.turn-1] = false;
                else if(kingSideCastle[this.turn-1] && action.pos.get(0).equals(KingSideRookPosition))
                    kingSideCastle[this.turn-1] = false;

                Pos currentPos = action.pos.get(0);
                Pos finalPos = UsefulMethods.calcPos(currentPos, board::adjacent, action.dir, action.steps);

                PieceModel<Species> removedPiece = board.remove(currentPos);
                board.put(removedPiece, finalPos);
            }

            // Action REMOVE: rimuovi il pezzo dalla sua posizione
            if(action.kind == Action.Kind.REMOVE) {
                board.remove(action.pos.get(0));
            }

            // Action JUMP: rimuovi il pezzo dalla sua casella di partenza e aggiungilo alla casella di arrivo
            if(action.kind == Action.Kind.JUMP){
                Pos currentPos = action.pos.get(0);
                Pos finalPos = action.pos.get(1);
                board.put(board.remove(currentPos), finalPos);
            }

            // Action SWAP: per la promozione del pezzo. Cambia il pezzo con un altro specificato
            if(action.kind == Action.Kind.SWAP)
                board.put(action.piece, action.pos.get(0));
        }

        MyCouple<Species, List<Pos>> checkInfo = findCheck(board::get, 3-turn);
        if(checkInfo != null){
            if(movesAgainstCheck(board::get, 3-turn, checkInfo).size() == 0){
                this.res = turn;
                turn = 3 - turn;
                return true;
            }

            turn = 3 - turn;
            return true;
        }

        ArrayList<PieceModel<Species>> remainingWhitePieces = new ArrayList<>();
        ArrayList<PieceModel<Species>> remainingBlackPieces = new ArrayList<>();

        for(Pos position : board.positions()){
            PieceModel<Species> currentPiece = board.get(position);
            if(currentPiece != null){
                if(currentPiece.color.equals("bianco")) remainingWhitePieces.add(currentPiece);
                else if(currentPiece.color.equals("nero")) remainingBlackPieces.add(currentPiece);
            }
        }
        boolean conditionWhite = remainingWhitePieces.size() == 1 ? true : (remainingWhitePieces.size() == 2 && (remainingWhitePieces.contains(whitePieces[3]) || remainingWhitePieces.contains(whitePieces[4])));
        boolean conditionBlack = remainingWhitePieces.size() == 1 ? true : (remainingBlackPieces.size() == 2 && (remainingBlackPieces.contains(blackPieces[3]) || remainingBlackPieces.contains(blackPieces[4])));
        if(conditionWhite && conditionBlack){
            this.res = 0;
            turn = 3 - turn;
            return true;
        }
        turn = 3 - turn;
        if(validMoves().size() == 1){
            this.res = 0;
            this.turn = 3 - turn;
            return true;
        }
        return true;
    }

    private Situation<PieceModel<Species>> situationMove(Move<PieceModel<Species>> move, Situation<PieceModel<Species>> situation){

        Map<Pos, PieceModel<Species>> situationMap = situation.newMap();
        int turn = situation.turn;

        for(Action<PieceModel<Species>> action : move.actions){

            //Action MOVE: rimuovi il pezzo dalla sua casella di partenza e aggiungilo nella casella di arrivo
            if(action.kind == Action.Kind.MOVE){
                Pos currentPos = action.pos.get(0);
                PieceModel<Species> currentPiece = situation.get(currentPos);
                Pos finalPos = UsefulMethods.calcPos(currentPos, board::adjacent, action.dir, action.steps);
                situationMap.put(finalPos, situationMap.remove(currentPos));
            }

            // Action REMOVE: rimuovi il pezzo dalla sua posizione
            // TODO: riguardare
            if(action.kind == Action.Kind.REMOVE) {
                situationMap.remove(action.pos.get(0));
            }

            // Action JUMP: rimuovi il pezzo dalla sua casa base e aggiungilo alla casella di arrivo
            // TODO: riguardare
            if(action.kind == Action.Kind.JUMP){
                Pos currentPos = action.pos.get(0);
                Pos finalPos = action.pos.get(1);
                situationMap.put(finalPos, situationMap.remove(currentPos));
            }

            // Action SWAP: per la promozione del pezzo. Cambia il pezzo con un altro specificato
            // TODO: riguardare
            if(action.kind == Action.Kind.SWAP)
                situationMap.put(action.pos.get(0), action.piece);
        }

        turn = 3 - turn;
        return new Situation<>(situationMap, turn);
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

        Pos kingPosition = this.turn == 1 ? new Pos(4, 0) : new Pos(4, 7);
        if(kingSideCastle[this.turn-1]){
            Pos rookPosition = this.turn == 1 ? new Pos(7, 0) : new Pos(7, 7);
            boolean validCastle = true;
            if(board.get(new Pos(kingPosition.b+1, kingPosition.t)) == null && board.get(new Pos(kingPosition.b+2, kingPosition.t)) == null){
                List<Move<PieceModel<Species>>> kingMoves = new ArrayList<>(Arrays.asList(new Move<>(new Action(RIGHT, 1, kingPosition)), new Move<>(new Action(RIGHT, 2, kingPosition))));
                for(Move<PieceModel<Species>> move : kingMoves){
                    Situation<PieceModel<Species>> currentSituationCopy = new Situation<>(currentSituation.newMap(), turn);
                    Situation<PieceModel<Species>> movedSituation = situationMove(move, currentSituationCopy);
                    if(findCheck(movedSituation::get, 3 - movedSituation.turn) != null)
                        validCastle = false;
                }
                if(validCastle) validMoves.add(new Move<>(new Action(RIGHT, 2, kingPosition), new Action(rookPosition, new Pos(rookPosition.b-2, rookPosition.t))));
            }
        }

        if(queenSideCastle[this.turn-1]){
            Pos rookPosition = this.turn == 1 ? new Pos(0, 0) : new Pos(0, 7);
            boolean validCastle = true;
            if(board.get(new Pos(kingPosition.b-1, kingPosition.t)) == null && board.get(new Pos(kingPosition.b-2, kingPosition.t)) == null) {
                List<Move<PieceModel<Species>>> kingMoves = new ArrayList<>(Arrays.asList(new Move<>(new Action(RIGHT, 1, kingPosition)), new Move<>(new Action(RIGHT, 2, kingPosition))));
                for(Move<PieceModel<Species>> move : kingMoves){
                    Situation<PieceModel<Species>> currentSituationCopy = new Situation<>(currentSituation.newMap(), turn);
                    Situation<PieceModel<Species>> movedSituation = situationMove(move, currentSituationCopy);
                    if(findCheck(movedSituation::get, 3 - movedSituation.turn) != null)
                        validCastle = false;
                }
                if(validCastle) validMoves.add(new Move<>(new Action(LEFT, 2, kingPosition), new Action(rookPosition, new Pos(rookPosition.b+3, rookPosition.t))));
            }
        }

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

        MyCouple<Species, List<Pos>> checkInfo = findCheck(f, turn);
        if(checkInfo != null) return new HashSet<>(movesAgainstCheck(f, turn, checkInfo));
        Set<Move<PieceModel<Species>>> validMoves = new HashSet<>();

        for(Pos position : board.positions()){
            PieceModel<Species> currPiece = f.apply(position);
            Situation<PieceModel<Species>> currentSituation = map(f, turn);
            if(currPiece != null){

                if((turn == 1 && currPiece.equals(whitePieces[0])) || (turn == 2 && currPiece.equals(blackPieces[0]))){
                    for(Move<PieceModel<Species>> move : kingValidMoves(f, position)){
                        Situation<PieceModel<Species>> currentSituationCopy = new Situation<>(currentSituation.newMap(), turn);
                        Situation<PieceModel<Species>> movedSituation = situationMove(move, currentSituationCopy);

                        if(findCheck(movedSituation::get, 3 - movedSituation.turn) == null) {
                            validMoves.add(move); // 3 - this.turn perchè il turno deve rimanere quello di partenza;
                        }
                    }
                }

                if((turn == 1 && currPiece.equals(whitePieces[1])) || (turn == 2 && currPiece.equals(blackPieces[1]))){
                    for(Move<PieceModel<Species>> move : pawnValidMoves(f, position)){
                        Situation<PieceModel<Species>> currentSituationCopy = new Situation<>(currentSituation.newMap(), turn);
                        Situation<PieceModel<Species>> movedSituation = situationMove(move, currentSituationCopy);

                        if(findCheck(movedSituation::get, 3 - movedSituation.turn) == null) {
                            validMoves.add(move); // 3 - this.turn perchè il turno deve rimanere quello di partenza;
                        }
                    }
                }

                if( (turn == 1 && (currPiece.equals(whitePieces[2]) || currPiece.equals(whitePieces[4]) || currPiece.equals(whitePieces[5]))) ||
                        (turn == 2 && (currPiece.equals(blackPieces[2]) || currPiece.equals(blackPieces[4]) || currPiece.equals(blackPieces[5])))){
                    for(Move<PieceModel<Species>> move : pieceValidMoves(f, position)){
                        Situation<PieceModel<Species>> currentSituationCopy = new Situation<>(currentSituation.newMap(), turn);
                        Situation<PieceModel<Species>> movedSituation = situationMove(move, currentSituationCopy);

                        if(findCheck(movedSituation::get, 3 - movedSituation.turn) == null) {
                            validMoves.add(move); // 3 - this.turn perchè il turno deve rimanere quello di partenza;
                        }
                    }
                }

                if((turn == 1 && currPiece.equals(whitePieces[3])) || (turn == 2 && currPiece.equals(blackPieces[3]))){
                    for(Move<PieceModel<Species>> move : knightValidMoves(f, position)){
                        Situation<PieceModel<Species>> currentSituationCopy = new Situation<>(currentSituation.newMap(), turn);
                        Situation<PieceModel<Species>> movedSituation = situationMove(move, currentSituationCopy);

                        if(findCheck(movedSituation::get, 3 - movedSituation.turn) == null) {
                            validMoves.add(move); // 3 - this.turn perchè il turno deve rimanere quello di partenza;
                        }
                    }
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
    public GameRuler<PieceModel<PieceModel.Species>> copy() {
        return new Scacchi(this);
    }

    @Override
    public Mechanics<PieceModel<PieceModel.Species>> mechanics() {
        return mechanics;
    }

    // INUTILE PERCHè INUTILIZZABILE NEGLI SCACCHI PER COME ABBIAMO CODIFICATO LA SITUAZIONE NOI
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

                // Action JUMP: rimuovi il pezzo dalla sua casa base e aggiungilo alla casella di arrivo
                if(action.kind == Action.Kind.JUMP){
                    Pos currentPos = action.pos.get(0);
                    Pos finalPos = action.pos.get(1);
                    cNext.put(finalPos, cNext.remove(currentPos));
                }

                // Action SWAP: per la promozione del pezzo. Cambia il pezzo con un altro specificato
                if(action.kind == Action.Kind.SWAP)
                    cNext.put(action.pos.get(0), action.piece);
            }


            int tNext;
            //TODO Aggiungi per stallo
            if(findCheck(cNext::get, 3-this.turn) != null && movesAgainstCheck(board::get, 3, null).size() == 0)
                tNext = -(3 - turn);
            else tNext = (3 - turn);

            sMap.put(m, new Situation<>(cNext, tNext));


        }
        if (sMap.isEmpty()) return null;   // Situazione non valida
        return sMap;
    }

    // -----------------------> VALID MOVES < -------------------------
    private List<Move<PieceModel<Species>>> pieceValidMoves(Function<Pos, PieceModel<Species>> f, Pos piecePosition){

        // ValidMoves per: torri, alfieri e donna
        PieceModel<Species> piece = f.apply(piecePosition);
        String currColor = piece.color;

        List<Dir> pieceDirections = new ArrayList<>();  // Direzioni di spostamento valide del pezzo
        List<Move<PieceModel<Species>>> pieceValidMoves = new ArrayList<>(); // Lista delle mosse valide restituite

        if(piece.species == Species.ROOK) pieceDirections.addAll(Arrays.asList(DOWN, UP, LEFT, RIGHT));
        if(piece.species == Species.BISHOP) pieceDirections.addAll(Arrays.asList(DOWN_L, DOWN_R, UP_L, UP_R));
        if(piece.species == Species.QUEEN) pieceDirections.addAll(Arrays.asList(DOWN, UP, LEFT, RIGHT, DOWN_L, DOWN_R, UP_L, UP_R));

        for (Dir direction : pieceDirections){
            Pos currentPos = board.adjacent(piecePosition, direction);
            int steps = 1;
            while (currentPos != null){
                PieceModel<Species> currPiece = f.apply(currentPos);
                if(currPiece != null){                          // Se c'è un pezzo nella casella considerata
                    if(currPiece.color.equals(currColor)) break; // Se i due pezzi sono dello stesso colore esci dal ciclo e non fare nulla
                    else{
                        pieceValidMoves.add(new Move<>(new Action(currentPos), new Action(direction, steps, piecePosition)));   // Altrimenti aggiungi la mossa cattura ed esci dal ciclo
                        break;
                    }
                }
                pieceValidMoves.add(new Move<>(new Action(direction, steps, piecePosition))); // Mossa di spostamento intermedia
                currentPos = board.adjacent(currentPos, direction);
                steps += 1;
            }
        }
        return pieceValidMoves;
    }

    private List<Move<PieceModel<Species>>> pawnValidMoves(Function<Pos, PieceModel<Species>> f, Pos pawnPosition){


        List<Move<PieceModel<Species>>> validMoves = new ArrayList<>();
        PieceModel<Species> pawn = f.apply(pawnPosition);
        PieceModel<Species> opponentPawn = f.apply(pawnPosition).color.equals("bianco") ? blackPieces[1] : whitePieces[1];

        Dir straightDir = pawn.equals(whitePieces[1]) ? Dir.UP : Dir.DOWN; // Se è un pedone bianco va verso l'alto, se è un pedone nero va verso il basso
        // Mosse di cattura
        List<Dir> pawnDirs = new ArrayList<>(pawn.color.equals("bianco") ? Arrays.asList(UP_L, UP_R) : Arrays.asList(DOWN_L, DOWN_R));
        Dir opponentPawnDir = straightDir.equals(Dir.UP) ? Dir.DOWN : Dir.UP;

        if(pawnPosition.b - 1 >= 0 && isEnPassantRow(pawnPosition, pawn) && f.apply(new Pos(pawnPosition.b - 1, pawnPosition.t)) != null &&
                f.apply(new Pos(pawnPosition.b - 1, pawnPosition.t)).equals(opponentPawn) &&
                moveHistory.get(moveHistory.size() - 1).equals(new Move<>(new Action(opponentPawnDir, 2, new Pos(pawnPosition.b - 1, 6)))))
            validMoves.add(new Move<>(new Action<>(new Pos(pawnPosition.b -1, pawnPosition.t)), new Action<>(pawnDirs.get(0), 1, pawnPosition)));


        if(pawnPosition.b <= 7 && isEnPassantRow(pawnPosition, pawn) && f.apply(new Pos(pawnPosition.b + 1, pawnPosition.t)) != null &&
                f.apply(new Pos(pawnPosition.b + 1, pawnPosition.t)).equals(opponentPawn) &&
                moveHistory.get(moveHistory.size() - 1).equals(new Move<>(new Action(opponentPawnDir, 2, new Pos(pawnPosition.b + 1, 6)))))
            validMoves.add(new Move<>(new Action<>(new Pos(pawnPosition.b + 1, pawnPosition.t)), new Action<>(pawnDirs.get(1), 1, pawnPosition)));

        // TODO da ottimizzare
        Pos currUpPosition = board.adjacent(pawnPosition, straightDir);
        int steps = isHomeRow(pawnPosition, pawn) ? 2 : 1;    // Se il pedone si trova nella sua casa base può muoversi anche di 2 posizioni in avanti
        int index = 1;

        // Mosse di spostamento in avanti
        while(currUpPosition != null && f.apply(currUpPosition) == null && index <= steps){
            if(isPromotionRow(pawn.color, currUpPosition)) {
                for(int i = 2; i <= 5; i++)
                        validMoves.add(new Move<>(new Action(straightDir, index, pawnPosition), new Action(pawn.color.equals("bianco") ? whitePieces[i] : blackPieces[i], currUpPosition)));
            }
            else validMoves.add(new Move<>(new Action(straightDir, index, pawnPosition)));
            currUpPosition = board.adjacent(currUpPosition, straightDir);
            index++;
        }

        for(Dir direction : pawnDirs){
            Pos currDiagPosition = board.adjacent(pawnPosition, direction);
            if (currDiagPosition != null && f.apply(currDiagPosition) != null && !f.apply(currDiagPosition).color.equals(pawn.color)) {
                if(isPromotionRow(pawn.color, currDiagPosition)) {
                    for(int i = 2; i <= 5; i++)
                        validMoves.add(new Move<>(new Action<>(currDiagPosition), new Action(direction, 1, pawnPosition), new Action(pawn.color.equals("bianco") ? whitePieces[i] : blackPieces[i], currDiagPosition)));
                }
                else validMoves.add(new Move<>(new Action<>(currDiagPosition), new Action(direction, 1, pawnPosition)));
            }
        }

        return validMoves;
    }

    private List<Move<PieceModel<Species>>> knightValidMoves(Function<Pos, PieceModel<Species>> f, Pos knightPosition) {
        List<Move<PieceModel<Species>>> validMoves = new ArrayList<>();
        String oppositeColor = f.apply(knightPosition).color.equals("nero") ? "bianco" : "nero";

        List<Pos> maybePositions = L_POSITIONS(knightPosition);
        List<Pos> validPosition = maybePositions.stream().filter(pos -> this.board.isPos(pos)).collect(Collectors.toCollection(ArrayList<Pos>::new));

        for(Pos position : validPosition){
            if(f.apply(position) == null) validMoves.add(new Move<>(new Action(knightPosition, position)));
            if(f.apply(position) != null && f.apply(position).color.equals(oppositeColor)) validMoves.add(new Move<>(new Action(position), new Action(knightPosition, position)));
        }
        return validMoves;
    }

    private List<Move<PieceModel<Species>>> kingValidMoves(Function<Pos, PieceModel<Species>> f, Pos kingPosition){
        List<Move<PieceModel<Species>>> validMoves = new ArrayList<>();

        String opponentColor = f.apply(kingPosition).color.equals("bianco") ? "nero" : "bianco";
        List<Dir> kingDirections = new ArrayList<>(Arrays.asList(UP, DOWN, LEFT, RIGHT, UP_R, UP_L, DOWN_R, DOWN_L));
        for(Dir direction : kingDirections){
            Pos finalPos = UsefulMethods.calcPos(kingPosition, board::adjacent, direction, 1);
            if(finalPos != null){
                if (f.apply(finalPos) != null){
                    if (f.apply(finalPos).color.equals(opponentColor)) {
                        validMoves.add(new Move<>(new Action<>(finalPos), new Action(direction, 1, kingPosition))); // Mosse di cattura
                    }
                }
                else if(f.apply(finalPos) == null) {
                    validMoves.add(new Move<>(new Action(direction, 1, kingPosition))); // Mosse di spostamento
                }
            }
        }
        return validMoves;
    }

    private List<Move<PieceModel<Species>>> allPossibleMovesWK(Function<Pos, PieceModel<Species>> f, int turn) {
        List<Move<PieceModel<Species>>> validMoves = new ArrayList<>();

        for(Pos position : board.positions()){
            PieceModel<Species> currPiece = f.apply(position);
            if(currPiece != null){
                if((turn == 1 && currPiece.equals(whitePieces[1])) || (turn == 2 && currPiece.equals(blackPieces[1]))){
                    validMoves.addAll(pawnValidMoves(f, position));
                }

                if( (turn == 1 && (currPiece.equals(whitePieces[2]) || currPiece.equals(whitePieces[4]) || currPiece.equals(whitePieces[5]))) ||
                        (turn == 2 && (currPiece.equals(blackPieces[2]) || currPiece.equals(blackPieces[4]) || currPiece.equals(blackPieces[5])))){
                    validMoves.addAll(pieceValidMoves(f, position));
                }

                if((turn == 1 && currPiece.equals(whitePieces[3])) || (turn == 2 && currPiece.equals(blackPieces[3]))){
                    validMoves.addAll(knightValidMoves(f, position));
                }
            }
        }
        return validMoves;
    }

    // ------------------------> FINE VALID MOVES <---------------------------
    private boolean isHomeRow(Pos piecePosition, PieceModel<Species> pawn){
        return (pawn.color.equals("bianco") && piecePosition.t == 1) || (pawn.color.equals("nero") && piecePosition.t == 6);
    }

    private boolean isEnPassantRow(Pos piecePosition, PieceModel<Species> pawn){
        return (pawn.color.equals("bianco") && piecePosition.t == 4) || (pawn.color.equals("nero") && piecePosition.t == 5);
    }

    private MyCouple<Species, List<Pos>> findCheck(Function<Pos, PieceModel<Species>> f, int turn){


        // Colore e posizione del re
        // TODO OTTIMIZZARE
        String kingColor = turn == 1 ? "bianco" : "nero";

        Optional<Pos> kingPositionOptional = board.positions().stream().filter(pos -> {
            PieceModel<Species> piece = f.apply(pos);
            return piece != null && piece.species.equals(Species.KING) && piece.color.equals(kingColor);
        }).findFirst();
        Pos kingPosition = kingPositionOptional.get();

        // Pedone avversario e relative direzioni. In alto a sinistra/destra se bianco, in basso a sinistr/destra se nero
        PieceModel<Species> opponentPawn = turn == 1 ? blackPieces[1] :  whitePieces[1];
        PieceModel<Species> opponentKing = turn == 1 ? blackPieces[0] :  whitePieces[0];
        List<Dir> opponentPawnDirs = new ArrayList<>(turn == 2 ? Arrays.asList(DOWN_L, DOWN_R) : Arrays.asList(UP_L, UP_R));

        //Lista delle posizioni dello scacco. Se non c'è scacco null.
            // 1) Donna, alfiere, torre: tutte le posizioni che vanno dal pezzo al re
            // 2) Pedone o cavallo: posizione del pezzo e del re
        List<Pos> checkingPositions = null;

        // Pezzi che possono dare scacco in direzione verticale e orizontale: Donna, Torre
        List<Dir> straightDirs = new ArrayList<>(Arrays.asList(DOWN, UP, LEFT, RIGHT));
        List<PieceModel<Species>> straightCheckers = new ArrayList<>((List<PieceModel<Species>>) (turn == 2 ? Arrays.asList(whitePieces[2], whitePieces[5]) : Arrays.asList(blackPieces[2], blackPieces[5])));

        // Pezzi che possono dare scacco in direzione obliqua: Donna, Torre
        List<Dir> diagonalDirs = new ArrayList<>(Arrays.asList(DOWN_L, DOWN_R, UP_L, UP_R));
        List<PieceModel<Species>> diagonalCheckers = new ArrayList<>((List<PieceModel<Species>>) (turn == 2 ? Arrays.asList(whitePieces[4], whitePieces[5]) : Arrays.asList(blackPieces[4], blackPieces[5])));

        // Controllo scacco da parte del cavallo
        List<Pos> knightAvailablePositions = L_POSITIONS(kingPosition);
        for(Pos position : knightAvailablePositions){
            // TODO RICONTROLLA QUESTO IF
            if(f.apply(position) != null && ( (turn == 1 && f.apply(position).equals(blackPieces[3])) || (turn == 2 && f.apply(position).equals(whitePieces[3])) )){
                if(checkingPositions != null)
                    return new MyCouple<>(null, null); // Controllo per doppio scacco

                checkingPositions = new ArrayList<>(Arrays.asList(kingPosition, position));
            }
        }

        // Controllo scacco da parte degli altri pezzi
        for(Dir d : Dir.values()){
            Pos currentPosition = board.adjacent(kingPosition, d);
            List<Pos> candidateCheck = new ArrayList<>(Arrays.asList(kingPosition)); // Con la posizione del re
            int steps = 0;

            // Considera le posizioni adiacenti a quella di partenza nella direzione considerata
            // ... e continua ad andare avanti fino a quando non trova un pezzo
            while(currentPosition != null && f.apply(currentPosition) == null){
                candidateCheck.add(currentPosition);
                currentPosition = board.adjacent(currentPosition, d); // La prima posizione con un pezzo
                steps++;
            }

            // Aggiungo la posizione del pezzo candidato allo scacco
            PieceModel<Species> currentPiece = null;
            if(currentPosition != null){
                candidateCheck.add(currentPosition);
                currentPiece = f.apply(currentPosition);
            }

            // Controllo scacco da parte di un pedone
            if(steps == 0 && currentPiece != null && currentPiece.equals(opponentPawn) && opponentPawnDirs.contains(d)){
                if(checkingPositions == null)
                    checkingPositions = new ArrayList<>(Arrays.asList(kingPosition, currentPosition)); // controllo doppio scacco
                else
                    return new MyCouple<>(null, null);
            }

            // TODO: è un test
            if(steps == 0 && currentPiece != null && currentPiece.equals(opponentKing) && (straightDirs.contains(d) || diagonalDirs.contains(d))){
                if(checkingPositions == null)
                    checkingPositions = new ArrayList<>(Arrays.asList(kingPosition, currentPosition)); // controllo doppio scacco
                else {
                    return new MyCouple<>(null, null);
                }
            }

            if(currentPiece != null && ((straightCheckers.contains(currentPiece) && straightDirs.contains(d)) || (diagonalCheckers.contains(currentPiece) && diagonalDirs.contains(d)))){
                // CONTROLLO PER DOPPIO SCACCO
                if(checkingPositions == null) {
                    checkingPositions = candidateCheck;
                }
                else
                    return new MyCouple<>(null, null);

            }
        }
        return checkingPositions != null ? new MyCouple<>(f.apply(checkingPositions.get(checkingPositions.size()-1)).species, checkingPositions) : null;
    }

    private List<Move<PieceModel<Species>>> movesAgainstCheck(Function<Pos, PieceModel<Species>> f, int turn, MyCouple<Species, List<Pos>> checkInfo){

        // Se lo scacco proviene da:
        //      1) Cavallo o pedone: spostamenti consentiti del re, cattura del pezzo avversario.
        //      2) Donna, alfiere, torre: spostamenti consentiti del re, cattura del pezzo avversario, intterruzione della linea di collegamento

        Situation<PieceModel<Species>> currentSituation = map(f, turn);

        List<Pos> checkingPositions = checkInfo.getSecond(); // Lista delle posizioni dello scacco
        Pos kingPosition = checkingPositions.remove(0); // Rimuovo la posizione del re

        List<Move<PieceModel<Species>>> validMoves = allPossibleMovesWK(f, turn);

        List<Move<PieceModel<Species>>> counterMoves = new ArrayList<>();
        List<Move<PieceModel<Species>>> possibleCounterMoves = validMoves.stream().filter(move -> checkingPositions.contains(lastPosLastAction(move)) || (move.actions.get(move.actions.size()-1).kind.equals(Action.Kind.MOVE) && checkingPositions.contains(UsefulMethods.calcPos(lastPosLastAction(move), board::adjacent, move.actions.get(move.actions.size()-1).dir, move.actions.get(move.actions.size()-1).steps)))).collect(Collectors.toCollection(ArrayList<Move<PieceModel<Species>>>::new));

        for(Move<PieceModel<Species>> move : possibleCounterMoves){
            Situation<PieceModel<Species>> currentSituationCopy = new Situation<>(currentSituation.newMap(), turn);
            Situation<PieceModel<Species>> movedSituation = situationMove(move, currentSituationCopy);
            if(findCheck(movedSituation::get, 3 - movedSituation.turn) == null)
                counterMoves.add(move); // 3 - this.turn perchè il turno deve rimanere quello di partenza;
        }

        for(Move<PieceModel<Species>> move : kingValidMoves(f, kingPosition)){
            Situation<PieceModel<Species>> currentSituationCopy = new Situation<>(currentSituation.newMap(), turn);
            Situation<PieceModel<Species>> movedSituation = situationMove(move, currentSituationCopy);
            if(findCheck(movedSituation::get, 3 - movedSituation.turn) == null)
                counterMoves.add(move); // 3 - this.turn perchè il turno deve rimanere quello di partenza;
            else this.printBoardFromFunction(movedSituation::get);
        }

        return counterMoves;
    }

    private boolean isPromotionRow(String pawnColor, Pos pawnPosition){
        return (pawnColor.equals("bianco") && pawnPosition.t == 7) || (pawnColor.equals("nero") && pawnPosition.t == 0);
    }

    private Pos lastPosLastAction(Move<PieceModel<Species>> move){
        Action<PieceModel<Species>> lastAction = move.actions.get(move.actions.size()-1);
        return lastAction.pos.get(lastAction.pos.size()-1);
    }

    private List<Pos> L_POSITIONS(Pos startPosition){
        int b = startPosition.b;
        int t = startPosition.t;
        List<Pos> maybePositions = new ArrayList<>(Arrays.asList(

                new Pos(b + 1, t + 2, true),
                new Pos(b + 2, t + 1, true),

                new Pos(b + 1, t - 2, true),
                new Pos(b + 2, t - 1, true),

                new Pos(b - 1, t + 2, true),
                new Pos(b - 2, t + 1, true),

                new Pos(b - 1, t - 2, true),
                new Pos(b - 2, t - 1, true)

        ));
        return maybePositions.stream().filter(pos -> board.isPos(pos)).collect(Collectors.toCollection(ArrayList<Pos>::new));
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


    public void printBoardFromFunction(Function<Pos, PieceModel<Species>> f){
        System.out.println(" -------------------------");
        for(int y = this.board.height()-1; y >= 0; y--){
            for(int x = 0; x < this.board.width(); x++){
                if(x == 0) System.out.print("| ");
                Pos position = new Pos(x, y);
                PieceModel<Species> piecem = f.apply(position);
                String piece = piecem == null ? "-  " : (piecem.color.equals("bianco") ? (piecem.species.toString().substring(0, 1).toUpperCase() + "  ") : (piecem.species.toString().substring(0, 1).toLowerCase() + "  ") );
                System.out.print(piece);
                if(x == this.board.width()-1) System.out.print("|");
            }
            System.out.print("\n");
        }
        System.out.println(" -------------------------");
    }

}
