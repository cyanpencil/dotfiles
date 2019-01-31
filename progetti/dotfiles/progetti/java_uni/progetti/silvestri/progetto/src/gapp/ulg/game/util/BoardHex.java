package gapp.ulg.game.util;

import gapp.ulg.game.board.Board;
import gapp.ulg.game.board.Pos;

import java.util.Collection;
import java.util.List;

/** <b>L'IMPLEMENTAZIONE DI QUESTA CLASSE È LASCIATA COME FALCOLTATIVA.</b>
 * <br>
 * Gli oggetti BoardHex implementano l'interfaccia {@link Board} per rappresentare
 * board generali con sistema di coordinate {@link System#HEXAGONAL}
 * modificabili.
 * @param <P>  tipo del modello dei pezzi */
public class BoardHex<P> implements Board<P> {
    /** Crea una BoardHex con le dimensioni date (può quindi essere rettangolare).
     * Le posizioni della board sono tutte quelle comprese nel rettangolo dato e le
     * adiacenze sono le sei per il sistema {@link System#HEXAGONAL}, eccetto
     * per le posizioni di bordo.
     * @param width  larghezza board
     * @param height  altezza board
     * @throws IllegalArgumentException se width <= 0 o height <= 0 */
    public BoardHex(int width, int height) {
        throw new UnsupportedOperationException("OPZIONALE");
    }

    /** Crea una BoardHex con le dimensioni date (può quindi essere rettangolare)
     * escludendo le posizioni in exc. Le adiacenze sono le sei per il sistema
     * {@link System#HEXAGONAL}, eccetto per le posizioni di bordo o adiacenti
     * a posizioni escluse. Questo costruttore permette di creare board per giochi
     * come ad es.
     * <a href="https://it.wikipedia.org/wiki/Abalone">Abalone</a>
     * @param width  larghezza board
     * @param height  altezza board
     * @param exc  posizioni escluse dalla board
     * @throws NullPointerException se exc è null
     * @throws IllegalArgumentException se width <= 0 o height <= 0 */
    public BoardHex(int width, int height, Collection<? extends Pos> exc) {
        throw new UnsupportedOperationException("OPZIONALE");
    }

    @Override
    public System system() {
        throw new UnsupportedOperationException("OPZIONALE");
    }

    @Override
    public int width() {
        throw new UnsupportedOperationException("OPZIONALE");
    }

    @Override
    public int height() {
        throw new UnsupportedOperationException("OPZIONALE");
    }

    @Override
    public Pos adjacent(Pos p, Dir d) {
        throw new UnsupportedOperationException("OPZIONALE");
    }

    @Override
    public List<Pos> positions() {
        throw new UnsupportedOperationException("OPZIONALE");
    }

    @Override
    public P get(Pos p) {
        throw new UnsupportedOperationException("OPZIONALE");
    }

    @Override
    public boolean isModifiable() { return true; }

    @Override
    public P put(P pm, Pos p) {
        throw new UnsupportedOperationException("OPZIONALE");
    }

    @Override
    public P remove(Pos p) {
        throw new UnsupportedOperationException("OPZIONALE");
    }
}
