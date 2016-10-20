package gapp.ulg.game.util;

import gapp.ulg.game.board.Board;
import gapp.ulg.game.board.PieceModel;
import gapp.ulg.game.board.Pos;

import java.util.*;

/** <b>IMPLEMENTARE I METODI SECONDO LE SPECIFICHE DATE NEI JAVADOC. Non modificare
 * le intestazioni dei metodi.</b>
 * <br>
 * Gli oggetti BoardOct implementano l'interfaccia {@link Board} per rappresentare
 * board generali con sistema di coordinate {@link System#OCTAGONAL}
 * modificabili.
 * @param <P>  tipo del modello dei pezzi */
public class BoardOct<P> implements Board<P> {

    private final int width;
    private final int height;
    private final System system = System.OCTAGONAL;
    private HashMap<Pos, P> boardMap;
    private List<Pos> positionList;

    /** Crea una BoardOct con le dimensioni date (può quindi essere rettangolare).
     * Le posizioni della board sono tutte quelle comprese nel rettangolo dato e le
     * adiacenze sono tutte e otto, eccetto per le posizioni di bordo.
     * @param width  larghezza board
     * @param height  altezza board
     * @throws IllegalArgumentException se width <= 0 o height <= 0 */
    public BoardOct(int width, int height) {
        if(width <= 0 || height <= 0) throw new IllegalArgumentException("Inserire coordinate valide");
        this.width = width;
        this.height = height;

        boardMap = new HashMap<>();
        List<Pos> positionList = new ArrayList<>();
        for(int h = 0; h <= height-1; h++){
            for(int b = 0; b <= width-1; b++){
                Pos position = new Pos(b, h);
                positionList.add(position);
                boardMap.put(position, null);
            }
        }
        this.positionList = Collections.unmodifiableList(positionList);
    }

    /** Crea una BoardOct con le dimensioni date (può quindi essere rettangolare)
     * escludendo le posizioni in exc. Le adiacenze sono tutte e otto, eccetto per
     * le posizioni di bordo o adiacenti a posizioni escluse. Questo costruttore
     * permette di creare board per giochi come ad es.
     * <a href="https://en.wikipedia.org/wiki/Camelot_(board_game)">Camelot</a>
     * @param width  larghezza board
     * @param height  altezza board
     * @param exc  posizioni escluse dalla board
     * @throws NullPointerException se exc è null
     * @throws IllegalArgumentException se width <= 0 o height <= 0 */
    public BoardOct(int width, int height, Collection<? extends Pos> exc) {
        if(exc == null) throw new NullPointerException("Inserisci una lista non nulla");
        if(width <= 0 || height <= 0) throw new IllegalArgumentException("Inserire coordinate valide");
        this.width = width;
        this.height = height;

        List<Pos> positionList = new ArrayList<>();

        boardMap = new HashMap<>();

        for(int h = 0; h <= height-1; h++){
            for(int b = 0; b <= width-1; b++){
                Pos position = new Pos(b, h);
                if(!exc.contains(position)){
                    positionList.add(position);
                    boardMap.put(position, null);
                }
            }
        }
        this.positionList = Collections.unmodifiableList(positionList);
    }

    /** Crea una BoardOct a partire da una board di partenza
     * @param board board di partenza
     */
    public BoardOct(BoardOct board) {
        this.width = board.width();
        this.height = board.height();
        this.positionList = new ArrayList<>();
        positionList.addAll(board.positions());
        boardMap = new HashMap<>();
        boardMap.putAll(board.boardMap);
    }

    /**
     * BoardOct a partire da una mappa di partenza
     * @param boardMap
     * @param width
     * @param height
     * @param positionList
     */
    public BoardOct(HashMap<Pos, P> boardMap, int width, int height, List<Pos> positionList){
        this.width = width;
        this.height = height;
        this.positionList = positionList;
        this.boardMap = boardMap;
    }

    @Override
    public System system() {
        return this.system;
    }

    @Override
    public int width() {
        return this.width;
    }

    @Override
    public int height() {
        return this.height;
    }

    @Override
    public Pos adjacent(Pos p, Dir d) {
        if(p == null || d == null) throw new NullPointerException("Inserire una posizione e una direzione valide");
        if(!isPos(p)) return null;
        int x = p.b + (d == Dir.UP_L ? -1 : (d == Dir.UP_R ? 1 : (d == Dir.DOWN_L ? -1 : (d == Dir.DOWN_R ? 1 : (d == Dir.LEFT ? -1 : (d == Dir.RIGHT ? 1 : (d == Dir.UP_L ? -1 : 0)))))));
        int y = p.t + (d == Dir.UP_L ? 1 : (d == Dir.UP_R ? 1 : (d == Dir.DOWN_L ? -1 : (d == Dir.DOWN_R ? -1 : (d == Dir.DOWN ? -1 : (d == Dir.UP_L ? 1 : (d == Dir.UP ? 1 : 0)))))));

        if(x < 0 || y < 0 || !isPos(new Pos(x, y))){
            return null;
        }
        return new Pos(x, y);
    }

    @Override
    public List<Pos> positions() {
        return positionList;
    }

    @Override
    public P get(Pos p) {
        if(p == null) throw new NullPointerException("Inserire una posizione non nulla");
        return boardMap.get(p);
    }

    @Override
    public boolean isModifiable() { return true; }


    @Override
    public P put(P pm, Pos p) {
        if(!isModifiable()) throw new UnsupportedOperationException("Questa board è immodificabile");
        if(pm == null || p == null) throw new NullPointerException("Inserire un pezzo e una posizione validi");
        if(!isPos(p)) throw new IllegalArgumentException("Inserire una posizione della board");
        P oldPiece = boardMap.get(p);
        boardMap.put(p, pm);
        return oldPiece;
    }

    @Override
    public P remove(Pos p) {
        if(!isModifiable()) throw new UnsupportedOperationException("Questa board è immodificabile");
        if(p == null) throw new NullPointerException("Inserire una posizione non nulla");
        if(!isPos(p)) throw new IllegalArgumentException("Inserire una posizione della board");
        P oldPiece = boardMap.get(p);
        boardMap.put(p, null);
        return oldPiece;
    }

    public HashMap<Pos, P> getBoardMap(){
        return boardMap;
    }

    public HashMap<Pos, P> getBoardMapWithoutEmpties(){
        HashMap<Pos,P> c = new HashMap<>();
        for (Pos p : this.get())
            c.put(p, this.get(p));
        return c;
    }

    public void setBoardMap(HashMap<Pos, PieceModel<PieceModel.Species>> map){
        this.boardMap = (HashMap<Pos, P>) map;
    }

    public void addAll(Map<Pos, P> map){
        for(Pos p : map.keySet()){
            this.boardMap.put(p, (P) map.get(p));
        }
    }

    public List<Pos> getPositionList() { return positionList; }

    public void printBoard(){
        PieceModel<PieceModel.Species> whitedisc = new PieceModel<>(PieceModel.Species.DISC, "bianco");
        for(int y = this.height-1; y >= 0; y--){
            for(int x = 0; x < this.height; x++){
                Pos position = new Pos(x, y);
                String piece = (this.get(position) == null ? "- " : (this.get(position) == whitedisc ? "B " : "N "));
                java.lang.System.err.print(piece);
            }
            java.lang.System.err.print("\n");
        }
    }
}
