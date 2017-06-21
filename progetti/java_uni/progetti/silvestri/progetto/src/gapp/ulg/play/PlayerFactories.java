package gapp.ulg.play;

import gapp.ulg.game.PlayerFactory;
import gapp.ulg.game.board.GameRuler;
import gapp.ulg.game.board.Player;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/** La classe permette di ottenere tutte le {@link PlayerFactory} disponibili e di
 * registrarne di nuove. */
public class PlayerFactories {
    /** @return i nomi di tutte le {@link PlayerFactory} disponibili per board game */
    public static String[] availableBoardFactories() {
        return boardFactories.keySet().toArray(new String[0]);
    }

    /** Ritorna la PlayerFactory per board game con il nome dato.
     * @param name  nome della PlayerFactory per board game
     * @param <P>  tipo del modello dei pezzi
     * @return la PlayerFactory per board game con il nome dato
     * @throws NullPointerException se {@code name} è null
     * @throws IllegalArgumentException se non esiste una PlayerFactory con quel nome
     * o la creazione di una PlayerFactory fallisce */
    public static <P> PlayerFactory<Player<P>,GameRuler<P>> getBoardFactory(String name) {
        Objects.requireNonNull(name);
        if (!boardFactories.containsKey(name)) throw new IllegalArgumentException();
        try {
            return (PlayerFactory<Player<P>,GameRuler<P>>)boardFactories.get(name).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException();
        }
    }

    /** Registra una nuova PlayerFactory per board game. È richiesto che la
     * PlayerFactory abbia un costruttore senza parametri.
     * @param pFClass  la classe dela nuova PlayerFactory
     * @param <P>  tipo del modello dei pezzi
     * @throws NullPointerException se {@code pFClass} è null
     * @throws IllegalArgumentException se esiste già una PlayerFactory per board
     * game con lo stesso nome o se la creazione della PlayerFactory fallisce, ad es.
     * perché non ha un costruttore senza parametri. */
    public static <P> void registerBoardFactory(Class<? extends PlayerFactory<Player<P>,GameRuler<P>>> pFClass) {
        Objects.requireNonNull(pFClass);
        try {
            PlayerFactory<Player<P>,GameRuler<P>> pF = pFClass.newInstance();
            String name = pF.name();
            if (name == null) throw new NullPointerException();
            if (boardFactories.containsKey(name)) throw new IllegalArgumentException();
            boardFactories.put(name, pFClass);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }



    private static final Map<String,Class<?>> boardFactories;

    static {
        boardFactories = new ConcurrentHashMap<>();
        boardFactories.put("Random Player", RandPlayerFactory.class);
        boardFactories.put("Monte-Carlo Tree Search Player", MCTSPlayerFactory.class);
        boardFactories.put("Optimal Player", OptimalPlayerFactory.class);
    }
}
