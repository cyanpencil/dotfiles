package gapp.ulg.game.util;

import gapp.ulg.game.board.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import static gapp.ulg.game.board.Move.Kind.RESIGN;

/** Un {@code PlayerGUI} rappresenta un giocatore che sceglie le mosse tramite GUI.
 * Però un oggetto {@code PlayerGUI} né controlla né interagisce direttamente con
 * alcuna GUI. Il suo scopo è facilitare la programmazione della parte di una GUI
 * che si occupa di permettere ad un utente di scegliere mosse valide durante una
 * partita ad un qualsiasi gioco del framework. L'interazione indiretta con la GUI
 * è mediata da {@link PlayerGUI.MoveChooser}. Ogni volta che è richiesta una mossa
 * {@link PlayerGUI.MoveChooser} è comunicato alla GUI (vedi parametro {@code master}
 * di {@link PlayerGUI#PlayerGUI(String, Consumer)}). Tale oggetto presenta le mosse
 * valide della situazione di gioco attuale sotto forma di albero navigabile che
 * facilita la scelta di una delle mosse tramite la GUI. Infatti la navigazione è
 * manovrabile tramite operazioni che sono direttamente collegabili a eventi di input,
 * come il click del mouse in una certa posizione o la pressione e il movimento del
 * mouse.
 * <br>
 * Uno dei vantaggi principali che la programmazione di una GUI ottiene dall'adozione
 * dell'approccio di {@code PlayerGUI}, e del sottostante meccanismo basato su
 * {@link PlayerGUI.MoveChooser}, è che la GUI non necessita di nessuna ulteriore
 * modifica o aggiustamento per giocare a nuovi giochi che possono venir aggiunti
 * in qualsiasi momento al framework. E i nuovi giochi non devono preoccuparsi di
 * gestire una GUI per giocare.
 * <br>
 * Un {@code PlayerGUI} non deve essere usato nel thread o i thread che gestiscono
 * la GUI. Primo perché non c'è necessità di farlo e secondo perché il metodo
 * {@link PlayerGUI#getMove()} blocca fino a che la mossa non è stata scelta.
 * @param <P>  tipo del modello dei pezzi */
public class PlayerGUI<P> implements Player<P> {

    private volatile GameRuler<P> gameRuler = null;
    private final String name;
    private final Consumer<MoveChooser<P>> master;
    private int playerTurn = 0; // TODO gestione del turno all'inizio della partita


    /** Un oggetto {@code MoveChooser} ha lo scopo di facilitare la scelta tramite
     * GUI di una mossa valida in una certa situazione di gioco. A questo fine
     * rappresenta l'insieme delle mosse valide di tipo {@link Move.Kind#ACTION} di
     * una situazione di gioco con una struttura ad albero navigabile chiamata
     * <i>albero delle mosse</i>.
     * <br>
     * Un oggetto {@code MoveChooser}, mantiene un nodo corrente che all'inizio è la
     * radice dell'albero. Il nodo corrente può essere spostato a uno dei suoi nodi
     * figli con i metodi {@link MoveChooser#doSelection(Object)},
     * {@link MoveChooser#moveSelection(Board.Dir, int)} e
     * {@link MoveChooser#jumpSelection(Pos)} o all'indietro al nodo genitore con
     * {@link MoveChooser#back()}. Per facilitare la selezione del nodo figlio,
     * un {@code MoveChooser} mantiene anche un insieme di posizioni, chiamato la
     * <i>selezione corrente</i>, che permette di selezionare un sotto-insieme dei
     * nodi figli del nodo corrente, tramite il metodo
     * {@link MoveChooser#select(Pos...)}.
     * <br>
     * Ogniqualvolta il nodo corrente è finale, cioè il metodo
     * {@link MoveChooser#isFinal()} ritorna true, si può scegliere la mossa
     * corrispondente con {@link MoveChooser#move()}. In ogni momento si può scegliere
     * la resa con {@link MoveChooser#resign()} e, se è una mossa valida (lo si può
     * accertare con {@link MoveChooser#mayPass()}), si può passare il turno con
     * {@link MoveChooser#pass()}. Per maggiori informazioni si può consultare
     * <a href="http://twiki.di.uniroma1.it/pub/Metod_prog/RS2_ESAMI/progetto.html">Progetto</a>.
     * @param <P>  tipo del modello dei pezzi */
    public interface MoveChooser<P> {
        /** @return un {@link Optional} con la sotto-mossa del nodo corrente o un
         * {@link Optional} vuoto se il nodo corrente è la radice con prefisso vuoto
         * o null se l'albero è vuoto
         * @throws IllegalStateException se è già stata scelta una mossa o il tempo
         * è scaduto */
        Optional<Move<P>> subMove();

        /** Ritorna la lista con le sotto-mosse di tutti i nodi figli del nodo
         * corrente. Se il nodo corrente è una foglia, ritorna la lista vuota. La
         * lista ritornata è sempre creata ex novo. Se l'albero è vuoto, ritorna null.
         * @return la lista con le sotto-mosse di tutti i nodi figli del nodo
         * corrente o null
         * @throws IllegalStateException se è già stata scelta una mossa o il tempo
         * è scaduto */
        List<Move<P>> childrenSubMoves();

        /** Seleziona le posizioni specificate e ritorna le sotto-mosse dei nodi figli
         * del nodo corrente selezionati dalle posizioni. Se non ci sono nodi figli
         * selezionati dalle posizioni, ritorna la lista vuota. La selezione corrente
         * diventa sempre uguale alle posizioni specificate anche se non seleziona
         * alcun nodo. La lista ritornata è sempre creata ex novo. Se l'albero è
         * vuoto, ritorna null.
         * @param pp  un insieme di posizioni
         * @return le sotto-mosse dei nodi figli del nodo corrente selezionati dalle
         * posizioni o null
         * @throws NullPointerException se una delle posizioni date è null
         * @throws IllegalArgumentException se non c'è almeno una posizione o ci
         * sono posizioni duplicate o c'è qualche posizione che non è nella board
         * @throws IllegalStateException se è già stata scelta una mossa o il tempo
         * è scaduto */
        List<Move<P>> select(Pos... pp);

        /** Ritorna le sotto-mosse dei nodi figli del nodo corrente quasi-selezionati
         * dalla selezione corrente. Se non ci sono sotto-mosse quasi-selezionate,
         * ritorna la lista vuota. La lista ritornata è sempre creata ex novo. Se
         * l'albero è vuoto ritorna null.
         * @return le sotto-mosse dei nodi figli del nodo corrente quasi-selezionati
         * dalla selezione corrente o null
         * @throws IllegalStateException se è già stata scelta una mossa o il tempo
         * è scaduto */
        List<Move<P>> quasiSelected();

        /** Se l'insieme dei nodi figli del nodo corrente selezionati dalla selezione
         * corrente non è vuoto e le prime azioni delle loro sotto-mosse o sono tutte
         * {@link Action.Kind#ADD} o sono tutte {@link Action.Kind#SWAP}, allora
         * ritorna la lista dei pezzi di tali azioni. Se invece c'è un solo nodo
         * figlio selezionato e la prima azione della sua sotto-mossa è
         * {@link Action.Kind#REMOVE}, ritorna una lista con l'unico elemento null.
         * Altrimenti ritorna la lista vuota. La lista ritornata è sempre creata ex
         * novo. Se l'albero è vuoto, ritorna null.
         * @return la lista dei pezzi o che contiene solamente l'elemento null o la
         * lista vuota o null
         * @throws IllegalStateException se è già stata scelta una mossa o il tempo
         * è scaduto */
        List<P> selectionPieces();

        /** Annulla la selezione corrente, se presente.
         * @throws IllegalStateException se è già stata scelta una mossa o il tempo
         * è scaduto */
        void clearSelection();

        /** Se le prime azioni delle sotto-mosse dei nodi figli selezionati dalla
         * selezione corrente sono tutte {@link Action.Kind#ADD} o sono tutte
         * {@link Action.Kind#SWAP} e una di queste ha il pezzo {@code pm}, allora
         * sposta il nodo corrente al corrispondente nodo figlio e ritorna la relativa
         * sotto-mossa. Se invece c'è un solo nodo figlio selezionato, la prima azione
         * della sua sotto-mossa è {@link Action.Kind#REMOVE} e {@code pm} è null,
         * allora sposta il nodo corrente al corrispondente nodo figlio e ritorna la
         * relativa sotto-mossa. In entrambi i casi la selezione corrente è annullata.
         * Se nessuno dei due casi è soddisfatto, non fa nulla e ritorna null.
         * @param pm  un pezzo o null
         * @return la sotto-mossa del nuovo nodo corrente o null
         * @throws IllegalStateException se è già stata scelta una mossa o il tempo
         * è scaduto */
        Move<P> doSelection(P pm);

        /** Se la prima azione della sotto-mossa di uno dei nodi figli selezionati
         * dalla selezione corrente è {@link Action.Kind#JUMP} con la posizione
         * d'arrivo {@code p}, allora sposta il nodo corrente al corrispondente nodo
         * figlio, annulla la selezione corrente e ritorna la relativa sotto-mossa.
         * Altrimenti non fa nulla e ritorna null.
         * @param p  una posizione
         * @return la sotto-mossa del nuovo nodo corrente o null
         * @throws IllegalStateException se è già stata scelta una mossa o il tempo
         * è scaduto */
        Move<P> jumpSelection(Pos p);

        /** Se la prima azione della sotto-mossa di uno dei nodi figli selezionati
         * dalla selezione corrente è {@link Action.Kind#MOVE} con parametri
         * {@code d} e {@code ns}, allora sposta il nodo corrente al corrispondente
         * nodo figlio, annulla la selezione corrente e ritorna la relativa
         * sotto-mossa. Altrimenti non fa nulla e ritorna null.
         * @param d  direzione di movimento
         * @param ns  numero passi
         * @return la sotto-mossa del nuovo nodo corrente o null
         * @throws IllegalStateException se è già stata scelta una mossa o il tempo
         * è scaduto */
        Move<P> moveSelection(Board.Dir d, int ns);

        /** Sposta il nodo corrente al nodo genitore e ritorna la sotto-mossa inversa
         * della sotto-mossa del precedente nodo corrente. Per la definizione di
         * sotto-mossa inversa si veda
         * <a href="http://twiki.di.uniroma1.it/pub/Metod_prog/RS2_ESAMI/progetto.html">Progetto</a>.
         * Se il nodo corrente è la radice (o l'albero è vuoto), non fa nulla e
         * ritorna null.
         * @return la sotto-mossa inversa della sotto-mossa del precedente nodo
         * corrente o null
         * @throws IllegalStateException se è già stata scelta una mossa o il tempo
         * è scaduto */
        Move<P> back();

        /** @return true se il nodo corrente è finale, false altrimenti
         * @throws IllegalStateException se è già stata scelta una mossa o il tempo
         * è scaduto */
        boolean isFinal();

        /** Se il nodo corrente è finale, sceglie la mossa corrispondente. Dopo che
         * il metodo è stato invocato con successo, questo oggetto non è più
         * utilizzabile e tutti i suoi metodi lanciano {@link IllegalStateException}.
         * @throws IllegalStateException se è già stata scelta una mossa o il tempo
         * è scaduto o l'albero è vuoto o il nodo corrente non è finale */
        void move();

        /** @return true se {@link Move.Kind#PASS} è una mossa valida, false
         * altrimenti.
         * @throws IllegalStateException se è già stata scelta una mossa o il tempo
         * è scaduto */
        boolean mayPass();

        /** Se {@link Move.Kind#PASS} è una mossa valida, sceglie la mossa
         * {@link Move.Kind#PASS}. Dopo che il metodo è stato invocato con successo,
         * questo oggetto non è più utilizzabile e tutti i suoi metodi lanciano
         * {@link IllegalStateException}.
         * @throws IllegalStateException se è già stata scelta una mossa o il tempo
         * è scaduto o se {@link Move.Kind#PASS} non è una mossa valida */
        void pass();

        /** Sceglie la mossa {@link Move.Kind#RESIGN}. Dopo che il metodo è stato
         * invocato con successo, questo oggetto non è più utilizzabile e tutti i
         * suoi metodi lanciano {@link IllegalStateException}.
         * @throws IllegalStateException se è già stata scelta una mossa o il tempo
         * è scaduto */
        void resign();
    }


    /** Crea un {@link PlayerGUI} con il nome e il master specificati. L'oggetto
     * {@link Consumer} master ad ogni richiesta di una mossa (vedi
     * {@link PlayerGUI#getMove()}) accetta, tramite il metodo
     * {@link Consumer#accept(Object)}, un {@link MoveChooser} e lo usa per scegliere
     * una mossa valida tramite una GUI. Il metodo {@link Consumer#accept(Object)} di
     * master deve ritornare imediatamente, non deve quindi attendere che la mossa
     * sia scelta. L'oggetto master deve essere thread-safe perché il suo metodo
     * {@link Consumer#accept(Object)} sarà invocato in più thread.
     * @param name  nome del giocatore
     * @param master  il master
     * @throws NullPointerException se {@code name} o {@code master} è null */
    public PlayerGUI(String name, Consumer<MoveChooser<P>> master) {
        if(name == null || master == null) throw new NullPointerException("Il nome è nullo o il master è nullo");
        this.name = name;
        this.master = master;
    }

    @Override
    public String name() {
        return this.name;
    }

    /** Il gioco può essere impostato una sola volta. In altre parole questo oggetto
     * può essere usato solamente per una partita. Nelle invocazioni succesive alla
     * prima lancia {@link IllegalArgumentException}.
     * @throws IllegalArgumentException se un gioco è stato già impostato */
    @Override
    public void setGame(GameRuler<P> g) {
        if(this.gameRuler != null) throw new IllegalArgumentException("E' gia stato impostato un GameRuler");
        if(g == null) throw new NullPointerException("GameRuler nullo");
        this.gameRuler = g;
    }

    @Override
    public void moved(int i, Move<P> m) {
        if(this.gameRuler == null || this.gameRuler.result() != -1) throw new IllegalStateException("Nessun gioco impostato o partita conclusa");
        if(m == null) throw new NullPointerException("Mossa nulla");
        if(!this.gameRuler.isValid(m)) throw new IllegalArgumentException("Mossa non valida nell'attuale situazione di gioco");
        this.gameRuler.isPlaying(i);
        this.gameRuler.move(m);
    }

    /** Crea un {@link MoveChooser} per l'attuale situazione di gioco e lo passa
     * al {@link Consumer} {@code master} che è stato impostato nel costruttore di
     * questo oggetto. Dopo di che si mette in attesa che il {@code master} scelga
     * una mossa valida tramite l'oggetto {@link MoveChooser} che gli è stato passato.
     * Non appena la mossa è scelta, si risveglia dall'attesa e ritorna la mossa
     * scelta. Più precisamente, se il gioco non pone limiti di tempo sulle mosse,
     * questo metodo attende in uno stato {@link Thread.State#WAITING} se invece il
     * gioco pone un limite di tempo, attende in uno stato
     * {@link Thread.State#TIMED_WAITING} e se non viene scelta una mossa entro la
     * scadenza, allora esce dall'attesa, rende inutilizzabile il {@link MoveChooser}
     * e ritorna la mossa {@link Move.Kind#RESIGN}. Se il thread in cui è invocato
     * il metodo è interrotto durante l'attesa, il metodo esce immediatamente
     * dall'attesa, rende inutilizzabile il {@link MoveChooser} e ritorna null.
     * <br>
     * <b>Importante: il metodo non usa thread addizionali creati dal metodo stesso
     * e il {@link MoveChooser} creato è thread-safe perchè è usato in almeno un
     * thread differente, quello che gestisce la GUI.</b> */
    @Override
    public Move<P> getMove() {
        MyChooser<P> myChooser = new MyChooser<>(this.gameRuler);

        long start = System.currentTimeMillis();

        synchronized (myChooser){
            master.accept(myChooser);
            while(myChooser.getMove() == null){
                try {

                    long waitTime = (start - System.currentTimeMillis()) + gameRuler.mechanics().time;

                    if(gameRuler.mechanics().time >= 0) {
                        myChooser.wait(waitTime > 0 ? waitTime : 1);
                        if (System.currentTimeMillis() - start > gameRuler.mechanics().time) {
                            if (myChooser.getMove() == null  && !myChooser.tempoScaduto()) myChooser.resign();
                            myChooser.disable();
                            return new Move<>(RESIGN);
                        }
                    }
                    else {
                        myChooser.wait();
                    }
                } catch (InterruptedException e){
                    myChooser.disable();
                    return null;
                }
            }
            return myChooser.getMove();
        }
    }
}
