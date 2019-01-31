package gapp.ulg.game.board;

import java.util.*;

/** <b>IMPLEMENTARE I METODI DI DEFAULT CON L'INDICAZIONE "DA IMPLEMENTARE" SECONDO
 * LE SPECIFICHE DATE NEI JAVADOC. Non modificare le intestazioni dei metodi e non
 * aggiungere metodi.</b>
 * <br>
 * Un oggetto GameRuler rappresenta un gioco, cioè una partita in un particolare
 * tipo di gioco, ad es. una partita a Scacchi. Un (oggetto) GameRuler "conosce" le
 * regole del gioco e fornisce in ogni momento la situazione attuale delle
 * disposizioni dei pezzi sulla board, a quale giocatore tocca muovere e gli
 * eventuali punteggi. Permette di effettuare le mosse dei giocatori controllandone
 * la validità fino al termine della partita. Inoltre un GameRuler fornisce anche
 * alcuni metodi di utilità come l'insieme di tutte le mosse valide in un qualsiasi
 * turno di gioco e la possibilità di fare l'undo delle mosse eseguite.
 * <br>
 * Inoltre un GameRuler è clonabile tramite il metodo {@link GameRuler#copy()} per
 * fornire copie del GameRuler ai giocatori (vedi {@link Player}).
 * @param <P>  tipo del modello dei pezzi */
public interface GameRuler<P> {
    /** Ritorna il nome del gioco. Ritorna sempre la stessa stringa.
     * @return il nome del gioco */
    String name();

    /** Ritorna il valore del parametro di nome name.
     * @param name  il nome di un parametro del gioco
     * @param c  la classe del valore del parametro
     * @param <T>  il tipo del valore del parametro
     * @return il valore del parametro di nome name
     * @throws NullPointerException se name o c è null
     * @throws IllegalArgumentException se non c'è un parametro con nome name
     * @throws ClassCastException se il tipo del valore del parametro è
     * incompatibile con la classe c */
    <T> T getParam(String name, Class<T> c);

    /** Ritorna la lista con i nomi dei giocatori in ordine di turnazione. La lista
     * ritornata è immodificabile ed è sempre la stessa. Se un giocatore è in
     * posizione i della lista, allora il suo indice di turnazione è i+1.
     * @return la lista con i nomi dei giocatori in ordine di turnazione */
    List<String> players();

    /** Il colore assegnato al giocatore name o null se il gioco non assegna
     * un colore ai giocatori (ad es. in una variante del gioco Hex).
     * @param name  il nome di un giocatore
     * @return il colore assegnato al giocatore name o null
     * @throws NullPointerException se name è null
     * @throws IllegalArgumentException se name non è il nome di un giocatore */
    String color(String name);

    /** Ritorna la board del gioco con la disposizione attuale dei pezzi. L'oggetto
     * Board ritornato è immodificabile ed è una view della Board del gioco, cioè i
     * cambiamenti operati dalle mosse dei giocatori sono riportate nell'oggetto
     * Board ritornato. Il metodo dovrebbe ritornare sempre lo stesso oggetto.
     * @return la board del gioco con la disposizione attuale dei pezzi */
    Board<P> getBoard();

    /** Ritorna l'indice di turnazione del giocatore che deve fare la prossima mossa
     * o zero se il gioco è terminato.
     * @return l'indice di turnazione del giocatore che deve fare la prossima mossa
     * o zero */
    int turn();

    /** Se m è una mossa valida per il giocatore di turno, esegue la mossa m
     * aggiornando lo stato del gioco, passando il turno al prossimo giocatore e
     * ritorna true. Se invece la mossa non è valida, ritorna false ma il
     * comportamento dipende dal gioco, in alcuni giochi la mossa è semplicemente
     * ignorata e il turno o passa al prossimo giocatore o rimane all'attuale in
     * attesa che faccia una mossa valida in altri giochi il giocatore è eliminato.
     * @param m  una mossa
     * @return true se la mossa è valida e false altrimenti
     * @throws NullPointerException se m è null
     * @throws IllegalStateException se il gioco è terminato */
    boolean move(Move<P> m);

    /** Fa l'undo dell'ultima mossa eseguita, cioè riporta il gioco alla situazione
     * precedente a quella dell'ultima mossa eseguita, e ritorna true. Se il gioco è
     * all'inizio, cioè non è stata eseguita alcuna mossa, ritorna false.
     * @return true se c'è una mossa di cui fare l'undo */
    boolean unMove();

    /** Ritorna true se il giocatore con indice di turnazione i è ancora in gioco.
     * Se il gioco è terminato, ritorna sempre false.
     * @param i  indice di turnazione di un giocatore
     * @return true se il giocatore con indice di turnazione i è ancora in gioco
     * @throws IllegalArgumentException se i non è l'indice di turnazione di alcun
     * giocatore */
    boolean isPlaying(int i);

    /** Ritorna l'esito del gioco, se non è ancora terminato ritorna -1, se è
     * terminato con una patta ritorna 0, altrimenti ritorna l'indice di turnazione
     * del giocatore che ha vinto.
     * @return l'esito del gioco */
    int result();

    /** Ritorna l'insieme delle mosse valide nell'attuale situazione di gioco. In
     * altre parole ritorna tutte le mosse che può fare il giocatore attualmente
     * di turno. L'insieme ritornato è immodificabile e quindi può essere lo stesso
     * ad ogni invocazione nella stessa situazione di gioco.
     * @return l'insieme delle mosse valide, mai null
     * @throws IllegalStateException se il gioco è terminato */
    Set<Move<P>> validMoves();

    /** Ritorna true se m è una mossa valida nell'attuale situazione di gioco.
     * L'implementazione di default usa {@link GameRuler#result()} e
     * {@link GameRuler#validMoves()}.
     * @param m  una mossa
     * @return true se m è una mossa valida
     * @throws NullPointerException se m è null
     * @throws IllegalStateException se il gioco è terminato */
    default boolean isValid(Move<P> m) {
        if(result() != -1) throw new IllegalStateException("La partita è conclusa");
        if(m == null) throw new NullPointerException("Inserire una mossa non nulla");
        if(!validMoves().contains(m)) return false;
        return true;
    }

    /** Ritorna l'insieme delle mosse valide relative alla posizione p. Se nella
     * posizione p c'è un (modello di) pezzo, ritorna tutte le mosse valide che
     * iniziano con un'azione di tipo {@link Action.Kind#MOVE} o
     * {@link Action.Kind#JUMP} che muove il pezzo che è in posizione p o che
     * iniziano con un'azione di tipo {@link Action.Kind#SWAP} che sostituisce il
     * pezzo in posizione p. Se invece nella posizione p non c'è un pezzo, ritorna
     * le mosse valide che iniziano con una azione di tipo {@link Action.Kind#ADD}
     * che aggiunge un pezzo nella posizione p. L'insieme ritornato è immodificabile.
     * L'implementazione di default usa {@link GameRuler#getBoard()} e
     * {@link GameRuler#validMoves()}.
     * @param p  una posizione
     * @return l'insieme delle mosse valide relative alla posizione p
     * @throws NullPointerException se p è null
     * @throws IllegalArgumentException se p non è una posizione della board
     * @throws IllegalStateException se il gioco è terminato */
    default Set<Move<P>> validMoves(Pos p) {
        if(p == null) throw new NullPointerException("Inserire una posizione valida");
        if(!getBoard().isPos(p)) throw new IllegalArgumentException("Inserire una posizione valida");
        if(result() != -1) throw new IllegalStateException("La partita è conclusa");

        Board<P> currBoard = getBoard();

        P piece = currBoard.get(p);
        Set<Move<P>> validMoves = validMoves();
        Set<Move<P>> posMoves = new HashSet<>();
        for(Move<P> move : validMoves){
            Action<P> firstAction = move.actions.get(0);
            if(move.kind == Move.Kind.ACTION && firstAction.pos.get(0) == p){
                if(piece == null && firstAction.kind == Action.Kind.ADD) posMoves.add(move);
                if(piece != null && firstAction.kind != Action.Kind.ADD) posMoves.add(move);
            }
        }
        return Collections.unmodifiableSet(posMoves);
    }

    /** Ritorna il punteggio attuale del giocatore con indice di turnazione i.
     * Questo metodo è implementato solamente se il gioco prevede dei punteggi come
     * ad es. Go e Othello.
     * @param i  indice di turnazione di un giocatore
     * @return il punteggio attuale del giocatore con indice di turnazione i
     * @throws IllegalArgumentException se i non è l'indice di turnazione di alcun
     * giocatore
     * @throws UnsupportedOperationException se questo gioco non prevede punteggi */
    default double score(int i) {
        throw new UnsupportedOperationException("Questo gioco non ha punteggi");
    }

    /** Ritorna una copia profonda (una deep copy) di questo GameRuler. Questo
     * significa che tutti i valori mutabili dei campi del GameRuler devono essere
     * clonati in modo profondo mentre i valori immutabili possono essere condivisi.
     * @return una copia profonda di questo GameRuler */
    GameRuler<P> copy();




    /*-------------------------  N E W    M E M B E R S  -------------------------*/


    /** Una {@code Situation} rappresenta una situazione di gioco. Gli oggetti
     * {@code Situation} sono immutabili.
     * @param <P>  tipo del modello dei pezzi */
    class Situation<P> {
        /** L'indice di turnazione del giocatore di turno della situazione, se
         * la situazione è finale, è 0 per la patta o -w, dove w è l'indice di
         * turnazione del giocatore che ha vinto */
        public final int turn;

        /** Relativamente alla situazione di gioco rappresentata da questo
         * oggetto, ritorna il pezzo nella posizione p o null, se non c'è un
         * pezzo in p.
         * @param p  una posizione (può essere null)
         * @return il pezzo nella posizione p o null, se non c'è un pezzo in p */
        public P get(Pos p) { return conf.get(p); }

        public Map<Pos,P> newMap() { return new HashMap<>(conf); }

        /** Crea una situazione data la mappa delle disposizioni dei pezzi e il
         * turno di gioco. L'oggetto Situation creato non fa una copia della
         * mappa c ma la usa internamente, quindi è un wrap immodificabile
         * (dall'esterno) della mappa c.
         * @param c  una mappa dalle posizioni ai pezzi
         * @param t  un indice di turnazione o un intero <= 0 se la situazione è
         *           finale (vedi {@link Situation#turn}). */
        public Situation(Map<Pos,P> c, int t) {
            conf = c;
            turn = t;
        }

        @Override
        public boolean equals(Object x) {
            if (x == null || x.getClass() != getClass()) return false;
            Situation s = (Situation)x;
            return s.turn == turn && Objects.equals(s.conf, conf);
        }

        @Override
        public int hashCode() { return Objects.hash(conf, turn); }


        private final Map<Pos,P> conf;
    }

    /** Una funzione che data una situazione di gioco ritorna le mosse valide e le
     * corrispondenti prossime situazioni.
     * @param <P> tipo del modello dei pezzi */
    @FunctionalInterface
    interface Next<P> {
        /** Ritorna la mappa con chiavi le mosse valide, esclusa
         * {@link Move.Kind#RESIGN}, della situazione di gioco data e ad ognuna
         * associa la situazione che si ottiene facendo la mossa. Ritorna la
         * mappa vuota se la situazione data è finale.
         * <br>
         * Se la situazione data non è valida, il comportamento è indefinito e
         * può ritornare anche null. Una situazione non è valida se non può mai
         * verificarsi in una qualsiasi partita.
         * @param s  una situazione di gioco
         * @return la mappa con chiavi le mosse valide, esclusa
         * {@link Move.Kind#RESIGN}, della situazione di gioco data e ad ognuna
         * associa la situazione che si ottiene facendo la mossa. Ritorna la
         * mappa vuota se la situazione data è finale.
         * @throws NullPointerException se s è null */
        Map<Move<P>, Situation<P>> get(Situation<P> s);
    }

    /** Un oggetto {@code Mechanics} rappresenta la meccanica di un gioco. Ovvero
     * tutte quelle caratteristiche che sono indipendenti dalla particolare partita
     * giocata ma sono comuni a tutte le partite che si possono giocare con un certo
     * {@link GameRuler}. Gli oggetti {@code Mechanics} sono immutabili.
     * @param <P> tipo del modello dei pezzi */
    class Mechanics<P> {
        /** Il tempo in millisecondi per fare una mossa o -1 se non c'è limite */
        public final long time;

        /** La lista immodificabile dei modelli dei pezzi usati nel gioco, senza
         * ripetizioni e in ordine arbitrario */
        public final List<P> pieces;

        /** La lista immodificabile di tutte le posizioni della board ordinate
         * prima rispetto alla coordinata di base crescente e poi rispetto alla
         * coordinata trasversale crescente */
        public final List<Pos> positions;

        /** Il numero di giocatori */
        public final int np;

        /** La situazione iniziale */
        public final Situation<P> start;

        /** La funzione che data una situazione di gioco ritorna le mosse valide e
         * le corrispondenti prossime situazioni. */
        public final Next<P> next;

        public Mechanics(long t, List<P> pcs, List<Pos> pp, int np, Situation<P> s, Next<P> nx) {
            time = t;
            pieces = pcs;
            positions = pp;
            this.np = np;
            start = s;
            next = nx;
        }
    }

    /** Ritorna la meccanica di questo gioco. L'oggetto ritornato è immutabile e
     * dovrebbe essere sempre lo stesso. L'implementazione di default lancia una
     * eccezione. Il metodo deve essere ridefinito dalle classi che implementano
     * l'interfaccia {@link GameRuler}.
     * @return la meccanica di questo gioco */
    default Mechanics<P> mechanics() {
        throw new UnsupportedOperationException();
    }
}
