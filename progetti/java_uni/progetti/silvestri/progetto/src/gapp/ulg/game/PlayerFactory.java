package gapp.ulg.game;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Supplier;


/** Un oggetto PlayerFactory fabbrica giocatori di un certo tipo che dipendono da
 * eventuali parametri che possono essere impostati.
 * @param <Y>  tipo dei giocatori
 * @param <G>  tipo dei giochi */
public interface PlayerFactory<Y,G> {
    /** Possibili valori ritornati dal metodo {@link PlayerFactory#canPlay(GameFactory)}
     * che forniscono informazioni circa le capacità dei giocatori di questa fabbrica
     * nel giocare a un certo gioco */
    enum Play {
        /** I giocatori della fabbrica sanno giocare al gioco */
        YES,
        /** I giocatori della fabbrica non sanno giocare al gioco */
        NO,
        /** Al momento i giocatori della fabbrica non sanno giocare ma potrebbero
         * imparare invocando il metodo
         * {@link PlayerFactory#tryCompute(GameFactory, boolean, Supplier)} */
        TRY_COMPUTE
    }
    /** @return il nome del tipo di giocatore */
    String name();

    /** Imposta il percorso di una directory nella quale possono essere salvati e
     * letti file utili ai giocatori prodotti da questa fabbrica. Se non è impostato
     * o impostato a null, questa fabbrica non leggerà né salverà alcun file.
     * @param dir  percorso di una directory o null */
    void setDir(Path dir);

    /** Ritorna la lista degli eventuali parametri del giocatore. Se il giocatore
     * non ha parametri, ritorna la lista vuota. La lista ritornata è immodificabile.
     * Ovviamente i parametri della lista sono invece oggetti modificabili.
     * @return la lista degli eventuali parametri del giocatore */
    List<Param<?>> params();

    /** Ritorna un valore (vedi {@link Play}) che informa sulle capacità dei giocatori
     * di questa fabbrica di giocare al gioco specificato. Per determinare questa
     * informazione il metodo può ottenere dalla GameFactory data un gioco e le
     * capacità sono valutate rispetto al gioco ottenuto. Il metodo non blocca e
     * risponde velocemente.
     * @param gF  una fabbrica di giochi
     * @return un valore (vedi {@link Play}) che informa sulle capacità dei giocatori
     * di questa fabbrica di giocare al gioco specificato.
     * @throws NullPointerException se {@code gF} è null
     * @throws IllegalStateException se deve ottenere il gioco da {@code gF} e il
     * metodo {@link GameFactory#newGame()} lancia l'eccezione oppure se la directory
     * {@link PlayerFactory#setDir(Path)} è impostata, deve accedere ad essa e
     * durante l'accesso è lanciata un'eccezione, quest'ultima è wrapped in una
     * {@link IllegalStateException}. */
    Play canPlay(GameFactory<? extends G> gF);

    /** Tenta di imparare a giocare al gioco specificato. Il calcolo può richiedere
     * tempi lunghi e può fallire. Però durante il calcolo invoca frequentemente
     * {@code interrupt} (se non è null) e se ritorna true interrompe immediatamente
     * il calcolo ritornando la stringa "INTERRUPTED". Ritorna null se riesce ad
     * imparare a giocare, altrimenti ritorna una stringa con la spiegazione del
     * perché ha fallito. In particolare ritorna immediatamente null se il valore
     * ritornato da {@link PlayerFactory#canPlay(GameFactory)} per lo stesso gioco è
     * {@link Play#YES}, ritorna immediatamente "CANNOT PLAY" se invece è
     * {@link Play#NO}, ritorna "INTERRUPTED" se il calcolo è interrotto. Se invece
     * il calcolo fallisce per altri motivi ritorna una stringa opportuna (ad es.
     * "OUT OF MEMORY").
     * @param gF  una fabbrica di giochi
     * @param parallel  se true il calcolo è eseguito sfruttando l'eventuale
     *                  parallelismo offerto dalla macchina
     * @param interrupt  un oggetto che decide se il calcolo va interrotto o null
     * @return null o una stringa a seconda che riesca o meno ad imparare a giocare
     * @throws NullPointerException se {@code gF} è null
     * @throws IllegalStateException se deve ottenere il gioco da {@code gF} e il
     * metodo {@link GameFactory#newGame()} lancia l'eccezione. */
    String tryCompute(GameFactory<? extends G> gF, boolean parallel, Supplier<Boolean> interrupt);

    /** Ritorna un nuovo giocatore con il nome e i parametri impostati per il gioco
     * specificato.
     * @param gF  una fabbrica di giochi
     * @param name  il nome del nuovo giocatore
     * @return un nuovo giocatore con il nome e i parametri impostati per il gioco
     * specificato
     * @throws NullPointerException se {@code gF} o {@code name} è null
     * @throws IllegalStateException se il metodo
     * {@link PlayerFactory#canPlay(GameFactory)} per lo stesso gioco non ritorna
     * {@link Play#YES} oppure se non riesce a recuperare la strategia (ad es. si
     * verifica un errore durante la lettura da file). */
    Y newPlayer(GameFactory<? extends G> gF, String name);
}
