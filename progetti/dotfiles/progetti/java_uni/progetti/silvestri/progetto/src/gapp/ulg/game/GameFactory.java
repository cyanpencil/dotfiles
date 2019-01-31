package gapp.ulg.game;

import java.util.List;

/** Un oggetto GameFactory crea giochi, cioè oggetti per giocare partite, di un
 * certo tipo che rispettano eventuali parametri.
 * @param <G>  tipo del gioco */
public interface GameFactory<G> {
    /** @return il nome del gioco */
    String name();

    /** @return numero minimo di giocatori */
    int minPlayers();

    /** @return numero massimo di giocatori */
    int maxPlayers();

    /** Ritorna la lista degli eventuali parametri del gioco. Se il gioco non ha
     * parametri, ritorna la lista vuota. La lista ritornata è immodificabile.
     * Ovviamente i parametri della lista sono invece oggetti modificabili.
     * @return la lista degli eventuali parametri del gioco */
    List<Param<?>> params();

    /** Imposta i nomi dei giocatori nell'ordine di turnazione. Sono ammessi nomi
     * ripetuti (ad es. un giocatore che gioca contro se stesso).
     * @param names  nomi dei giocatori
     * @throws NullPointerException se uno dei nomi è null
     * @throws IllegalArgumentException se il numero dei giocatori non è ammissibile
     * per questo gioco (ad es. un numero di nomi diverso da 2 per un gioco come gli
     * Scacchi) */
    void setPlayerNames(String... names);

    /** Ritorna un nuovo gioco che rispetta i parametri.
     * @return  un nuovo gioco
     * @throws IllegalStateException se i nomi dei giocatori non sono stati
     * impostati */
    G newGame();
}
