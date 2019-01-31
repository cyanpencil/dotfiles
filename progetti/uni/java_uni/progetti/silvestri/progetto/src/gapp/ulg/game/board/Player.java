package gapp.ulg.game.board;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/** <b>Non modificare le intestazioni dei metodi e non aggiungere metodi.</b>
 * <br>
 * Un oggetto Player rappresenta un giocatore. Può essere un giocatore capace di
 * giocare un solo tipo di gioco (ad es. solamente Scacchi) oppure capace di giocare
 * molti giochi. Un (oggetto) Player può giocare un solo gioco, cioè partita, o più
 * partite. Una partita inizia invocando il metodo {@link Player#setGame(GameRuler)}.
 * Può essere una nuova partita o una partita già iniziata che si chiede di
 * proseguire. Dopo di ciò la partita è giocata tramite i metodi
 * {@link Player#moved(int, Move)} e {@link Player#getMove()} invocati
 * alternativamente fino a quando la partita termina.
 * @param <P>  tipo del modello dei pezzi */
public interface Player<P> {
    /** Ritorna il nome del giocatore. Dovrebbe ritornare sempre lo stesso nome.
     * @return il nome del giocatore */
    String name();

    /** Imposta il gioco a cui il giocatore dovrebbe giocare. Il gioco g può essere
     * nello stato iniziale ma può anche essere in uno stato diverso, ad es. se la
     * partita era stata interrotta ed ora viene ripresa. L'oggetto g è una copia
     * del GameRuler ufficiale del gioco. Il giocatore può usare e modificare g a
     * piacimento senza che questo abbia effetto sul GameRuler ufficiale. In
     * particolare il giocatore può usare g per mantenersi sincronizzato con lo
     * stato del gioco riportando in g le sue mosse e quelle degli altri giocatori,
     * vedi {@link Player#moved(int, Move)}.
     * <br>
     * <b>Il metodo non blocca, non usa altri thread e ritorna velocemente.</b>
     * @param g  un gioco, cioè una partita
     * @throws NullPointerException se g è null
     * @throws IllegalArgumentException se il giocatore, per qualsiasi ragione, non
     * può (o non vuole) giocare il gioco g */
    void setGame(GameRuler<P> g);

    /** Comunica al giocatore la mossa eseguita da un giocatore, compreso il
     * giocatore stesso. Il giocatore dovrebbe usare tale informazione per
     * aggiornare la sua copia del GameRuler.
     * <br>
     * <b>Il metodo non blocca, non usa altri thread e ritorna velocemente.</b>
     * @param i  indice di turnazione di un giocatore
     * @param m  la mossa eseguita dal giocatore
     * @throws IllegalStateException se non c'è un gioco impostato o c'è ma è
     * terminato.
     * @throws NullPointerException se m è null
     * @throws IllegalArgumentException se i non è l'indice di turnazione di un
     * giocatore o m non è una mossa valida nell'attuale situazione di gioco */
    void moved(int i, Move<P> m);

    /** Ritorna la mossa del giocatore, relativamente all'attuale stato di gioco. Il
     * giocatore dovrebbe attendere che sia invocato il metodo
     * {@link Player#moved(int, Move)} prima di aggiornare lo stato di gioco della
     * sua copia.
     * <br>
     * <b>Se il gioco mette un limite sul tempo per effettuare una mossa
     * {@link GameRuler.Mechanics#time}, il metodo fa di tutto per rispettarlo. Può
     * usare thread addizionali per calcolare la mossa, però quando ritorna tutti
     * i thread usati (esclusi quelli permessi, vedi {@link Player#threads}) sono
     * stati liberati o terminati, cioè se non sono terminati non ci sono computazioni
     * residue in esecuzione dovute a questo metodo su tali thread.
     * <br>
     * Se il thread in cui è invocato il metodo è interrotto, il metodo termina
     * immediatamente tutte le computazioni, termina i thread addizionali, o se non
     * può interrompe le computazioni dovute a questo metodo, e ritorna null.
     * <br>
     * Il metodo non può usare {@link ForkJoinPool#commonPool()}, può eventualmente
     * usare un pool del framework {@link ForkJoinTask ForkJoin} solo se
     * esplicitamente autorizzato da {@link Player#threads}.</b>
     * @return la mossa del giocatore
     * @throws IllegalStateException se non c'è un gioco impostato, c'è ma è terminato
     * oppure non è il turno di questo giocatore (l'indice di turnazione di questo
     * giocatore è determinato alla prima mossa e quindi il controllo sul turno è
     * dalla seconda mossa in poi). */
    Move<P> getMove();

    /** Comunica al giocatore il massimo numero di thread addizionali, l'eventuale
     * pool per il framework {@link ForkJoinTask ForkJoin} e l'eventuale esecutore
     * per l'esecuzione in background. I thread addizionali sono i thread che
     * {@link Player#getMove} può creare ed usare, esclusi quelli dell'eventuale pool
     * {@code fjp} e l'eventuale esecutore {@code bgExec}. Se {@code fpj} non è null,
     * può usare tale pool con il framework {@link ForkJoinTask ForkJoin}. Ma non può
     * in nessun caso usare il {@link ForkJoinPool#commonPool() Common Pool}. Se
     * {@code bgExec} non è null, {@link Player#getMove} può usare l'esecutore per
     * eseguire task in background, cioè anche dopo che l'invocazione del metodo
     * {@link Player#getMove} ritorna per elaborare durante le mosse degli avversari.
     * Più precisamente se T è il valore di {@code maxTh} allora durante
     * un'invocazione di {@link Player#getMove} può creare ed usare al massimo T
     * threads, oltre a quelli di {@code fjp}, se non è null, e l'esecutore
     * {@code bgExec}, se non null. Quando l'invocazione ritorna tutti i thread
     * addizionali devono essere terminati e non ci devono essere computazioni attive
     * nel {@code fjp}. Se {@code bgExec} non è null, possono rimanere task attivi
     * in tale esecutore.
     * <br>
     * Se {@code maxTh} è < 0, significa che non ci sono limiti sul numero di thread
     * addizionali.
     * <br>
     * L'implementazione di default non fa nulla. Se un {@link Player} ha bisogno di
     * usare thread ulteriori, <b>deve ridefinire tale metodo</b>, altrimenti la
     * comunicazione circa i limiti imposti andrà persa però i limiti saranno fatti
     * valere comunque.
     * <br>
     * In assenza di comunicazione, cioè questo metodo non è mai invocato, si presume
     * che non ci sia alcun limite e il giocatore può usare tutti i thread che vuole,
     * come vuole compreso il {@link ForkJoinPool#commonPool() Common Pool}.
     * @param maxTh  massimo numero di thread addizionali
     * @param fjp  pool per il framework {@link ForkJoinTask ForkJoin}, o null
     * @param bgExec  per l'esecuzione in background, può essere null */
    default void threads(int maxTh, ForkJoinPool fjp, ExecutorService bgExec) { }
}
