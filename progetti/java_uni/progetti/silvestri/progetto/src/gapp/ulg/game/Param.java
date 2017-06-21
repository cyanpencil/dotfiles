package gapp.ulg.game;

import java.util.List;

/** Un Param gestisce il valore di un parametro di un gioco. Ad esempio le
 * dimensioni della board, le varianti del gioco, l'handicap, ecc.
 * @param <T>  il tipo dei valori del parametro */
public interface Param<T> {
    /** @return il nome del parametro che lo identifica */
    String name();

    /** @return la stringa di prompt del parametro, cioè una stringa che spiega il
     * significato del parametro e può essere usata in una UI */
    String prompt();

    /** Ritorna la lista immodificabile dei valori ammissibili del parametro. Però i
     * valori possono cambiare in dipendenza dei valori impostati di altri parametri.
     * @return la lista dei valori ammissibili del parametro */
    List<T> values();

    /** Imposta il valore del parametro. Deve essere un valore presente nella lista
     * ritornata dal metodo {@link Param#values()}.
     * @param v  un valore
     * @throws IllegalArgumentException se il valore non è nella lista ritornata da
     * {@link Param#values()} */
    void set(Object v);

    /** @return il valore del parametro */
    T get();
}
