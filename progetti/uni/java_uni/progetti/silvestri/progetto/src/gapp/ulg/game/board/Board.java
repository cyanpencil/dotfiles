package gapp.ulg.game.board;

import java.util.*;

/** <b>IMPLEMENTARE I METODI DI DEFAULT CON L'INDICAZIONE "DA IMPLEMENTARE" SECONDO
 * LE SPECIFICHE DATE NEI JAVADOC. Non modificare le intestazioni dei metodi e non
 * aggiungere metodi.</b>
 * <br>
 * Un oggetto Board rappresenta la board (tavoliere, tavola, scacchiera) usata da
 * un gioco. Giochi diversi possono usare lo stesso tipo di board, ad es. Scacchi,
 * Dama (italiana) e Breakthrough possono essere giocati sulla stessa Board.
 * <br>
 * Una Board determina l'insieme delle posizioni ammissibili per i pezzi, il sistema
 * di coordinate per identificare le posizioni e le adiacenze tra posizioni. Inoltre
 * una Board mantiene le disposizioni dei pezzi e se è modificabile permette anche
 * di cambiare le disposizioni dei pezzi. Però una Board non rappresenta un gioco né
 * le regole per giocare. Un gioco e le sue regole sono rappresentate da un
 * {@link GameRuler}.
 * @param <P>  tipo del modello dei pezzi */
public interface Board<P> {
    /** Sistemi di coordinate per rappresentare le posizioni di una board */
    enum System {
        /** Sistema ottagonale con asse di base orizzontale diretto verso destra
         * ({@link Dir#RIGHT}) e asse trasversale verticale diretto verso l'alto
         * ({@link Dir#UP}). Usato per board con celle quadrate come quelle di
         * giochi come Scacchi, Dama, Go e Othello. Le adiacenze per lato sono nelle
         * direzioni {@link Dir#LEFT}-{@link Dir#RIGHT} per le righe,
         * {@link Dir#DOWN}-{@link Dir#UP} per le colonne. Le adiacenze in diagonale
         * sono nelle direzioni {@link Dir#DOWN_L}-{@link Dir#UP_R} e
         * {@link Dir#UP_L}-{@link Dir#DOWN_R}. */
        OCTAGONAL,
        /** Sistema esagonale con asse di base orizzontale diretto verso destra
         * ({@link Dir#RIGHT}) e asse trasversale ottenuto ruotando l'asse di base di
         * 120 gradi in senso antiorario. La direzione dell'asse trasversale è
         * {@link Dir#UP_L}. Usato per board con celle esagonali come quelle di
         * giochi come Hex e Abalone. Le adiacenze sono tipicamente solamente per
         * lato e sono nelle direzioni {@link Dir#LEFT}-{@link Dir#RIGHT},
         * {@link Dir#DOWN_L}-{@link Dir#UP_R} e {@link Dir#UP_L}-{@link Dir#DOWN_R}.
         * Non c'è adiacenza nelle direzioni {@link Dir#DOWN}-{@link Dir#UP}. */
        HEXAGONAL
    }

    /** Direzioni di adiacenza, il significato è determinato dal sistema di
     * coordinate */
    enum Dir {
        UP, DOWN, LEFT, RIGHT, UP_L, UP_R, DOWN_L, DOWN_R
    }

    /** @return il sistema di coordinate della board */
    System system();

    /** @return larghezza della board */
    int width();

    /** @return altezza della board */
    int height();

    /** Ritorna la posizione adiacente alla posizione p nella direzione d. Se p
     * non è una posizione della board o se p non ha una posizione adiacente
     * nella direzione d, ritorna null.
     * @param p  una posizione
     * @param d  una direzione
     * @return la posizione adiacente alla posizione p nella direzione d o null
     * @throws NullPointerException se p o d è null */
    Pos adjacent(Pos p, Dir d);

    /** Ritorna la lista di tutte le posizioni della board ordinate prima
     * rispetto alla coordinata di base crescente e poi rispetto alla coordinata
     * trasversale crescente. La lista ritornata è immodificabile ed è sempre la
     * stessa.
     * @return la lista delle posizioni della board */
    List<Pos> positions();

    /** Ritorna true se p è una posizione della board. L'implementazione di default
     * usa {@link Board#positions()}.
     * @param p  una posizione
     * @return true se p è una posizione della board
     * @throws NullPointerException se p è null */
    default boolean isPos(Pos p) {
        if(p == null) throw new NullPointerException("Inserire una posizione non nulla");
        if(!this.positions().contains(p)) return false;
        return true;
    }

    /** Ritorna il (modello di) pezzo nella posizione p della board o null se la
     * posizione è vuota o non è una posizione della board.
     * @param p  una posizione
     * @return il (modello di) pezzo nella posizione p o null
     * @throws NullPointerException se p è null */
    P get(Pos p);

    /** Ritorna l'insieme delle posizioni occupate da pezzi. L'insieme ritornato è
     * immodificabile. L'implementazione di default usa {@link Board#positions()} e
     * {@link Board#get(Pos)}.
     * @return l'insieme delle posizioni occupate da pezzi, non ritorna mai null */
    default Set<Pos> get() {
        Set<Pos> filledPositions = new HashSet<Pos>();
        List<Pos> boardPositions = positions();
        for(Pos position : boardPositions){
            if(get(position) != null){
                filledPositions.add(position);
            }
        }
        return Collections.unmodifiableSet(filledPositions);
    }

    /** Ritorna l'insieme delle posizioni occupate dal modello di pezzo pm.
     * L'insieme ritornato è immodificabile. L'implementazione di default usa
     * {@link Board#positions()} e {@link Board#get(Pos)}.
     * @param pm  un modello di pezzo
     * @return l'insieme delle posizioni occupate dal modello di pezzo pm, non
     * ritorna mai null
     * @throws NullPointerException se pm è null */
    default Set<Pos> get(P pm) {
        if(pm == null) throw new NullPointerException("Il pezzo non può essere null");
        Set<Pos> piecePositions = new HashSet<Pos>();
        for(Pos posizione : this.positions()){
            if(this.get(posizione) != null && this.get(posizione).equals(pm)){
                piecePositions.add(posizione);
            }
        }
        return Collections.unmodifiableSet(piecePositions);
    }

    /** Ritorna true se la board è modificabile. L'implemntazione  di default
     * ritorna false. Se la board è modificabile questo metodo è ridefinito insieme
     * ai metodi {@link Board#put(Object, Pos)} e {@link Board#remove(Pos)}.
     * @return true se la board è modificabile */
    default boolean isModifiable() {
        return false;
    }

    /** Mette il (modello di) pezzo pm nella posizione p della board e ritorna il
     * (modello di) pezzo che occupava quella posizione o null se era vuota. Questo
     * metodo è ridefinito solamente da board modificabili.
     * @param pm  un (modello di) pezzo
     * @param p  una posizione
     * @return il (modello di) pezzo che occupava la posizione o null se era vuota
     * @throws UnsupportedOperationException se questa board è immodificabile
     * @throws NullPointerException se pm o p è null
     * @throws IllegalArgumentException se p non è una posizione della board */
    default P put(P pm, Pos p) {
        throw new UnsupportedOperationException("Questa board è immodificabile");
    }

    /** Rimuove il (modello di) pezzo dalla posizione p e lo ritorna, se era vuota
     * ritorna null. Questo metodo è ridefinito solamente da board modificabili.
     * @param p  una posizione
     * @return il (modello di) pezzo che era nella posizione p o null se era vuota
     * @throws UnsupportedOperationException se questa board è immodificabile
     * @throws NullPointerException se p è null
     * @throws IllegalArgumentException se p non è una posizione della board */
    default P remove(Pos p) {
        throw new UnsupportedOperationException("Questa board è immodificabile");
    }

    /** Mette il (modello di) pezzo pm nelle posizioni della linea che parte nella
     * posizione p e va nella direzione d per n posizioni. Questo metodo ha
     * significato solamente per board modificabili. L'implementazione di default
     * usa {@link Board#isModifiable()}, {@link Board#isPos(Pos)},
     * {@link Board#adjacent(Pos, Dir)} e {@link Board#put(Object, Pos)}.
     * @param pm  un (modello di) pezzo
     * @param p  una posizione
     * @param d  una direzione
     * @param n  numero di posizioni, inclusa la posizione p
     * @throws UnsupportedOperationException se questa board è immodificabile
     * @throws NullPointerException se pm, p o d è null
     * @throws IllegalArgumentException se n <= 0 o una delle posizioni della linea
     * specificata non è nella board */
    default void put(P pm, Pos p, Dir d, int n) {
        if(pm == null || p == null || d == null) throw new NullPointerException("Dati scorretti.");
        if(!isPos(p)) throw new IllegalArgumentException("Posizione non consentita");
        if(!isModifiable()) throw new UnsupportedOperationException("Board immodificabile.");
        if(n <= 0) throw new IllegalArgumentException("Argomento n non può essere negativo");
        Pos currPosition = p;
        List<Pos> positionList = new ArrayList<Pos>(Arrays.asList(currPosition));


        while(n > 1){
            currPosition = this.adjacent(currPosition, d);
            //if(currPosition.b >= this.width() || currPosition.t >= this.height()) throw new IllegalArgumentException();
            if(currPosition == null || !isPos(currPosition)) throw new IllegalArgumentException("A partire dalla posizione: " + p.toString() + " la posizione: " + n + " non appartenente alla board. Direzione: " + d);
            positionList.add(currPosition);
            n--;
        }

        for(Pos piecePosition : positionList){
            this.put(pm, piecePosition);
        }
    }
}
