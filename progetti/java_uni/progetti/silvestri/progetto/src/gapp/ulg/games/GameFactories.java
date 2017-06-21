package gapp.ulg.games;

import gapp.ulg.game.GameFactory;
import gapp.ulg.game.board.GameRuler;
import gapp.ulg.game.board.PieceModel;

import static gapp.ulg.game.board.PieceModel.Species;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/** La classe permette di ottenere tutte le {@link GameFactory} disponibili e di
 * registrarne di nuove. */
public class GameFactories {
    /** @return i nomi di tutte le {@link GameFactory} disponibili per board game */
    public static String[] availableBoardFactories() {
        return boardFactories.keySet().toArray(new String[0]);
    }

    /** Ritorna la GameFactory per un board game con il nome dato.
     * @param name  nome di una GameFactory per board game
     * @return la GameFactory per un board game con il nome dato
     * @throws NullPointerException se {@code name} è null
     * @throws IllegalArgumentException se non c'è una GameFactory con nome
     * {@code name} o la creazione della GameFactory fallisce */
    public static GameFactory<GameRuler<PieceModel<Species>>> getBoardFactory(String name) {
        Objects.requireNonNull(name);
        if (!boardFactories.containsKey(name)) throw new IllegalArgumentException();
        try {
            return boardFactories.get(name).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException();
        }
    }

    /** Registra una nuova GameFactory per board game. È richiesto che la GameFactory
     * abbia un costruttore senza parametri.
     * @param gFClass  la classe di una GameFactory per board game
     * @throws NullPointerException se {@code gFClass} è null
     * @throws IllegalArgumentException se esiste già una GameFactory per board game
     * con lo stesso nome o se la creazione della GameFactory fallisce, ad es. perché
     * non ha un costruttore senza parametri. */
    public static void registerBoardFactory(Class<? extends GameFactory<GameRuler<PieceModel<Species>>>> gFClass) {
        Objects.requireNonNull(gFClass);
        try {
            GameFactory<GameRuler<PieceModel<Species>>> gF = gFClass.newInstance();
            String name = gF.name();
            if (name == null) throw new NullPointerException();
            if (boardFactories.containsKey(name)) throw new IllegalArgumentException();
            boardFactories.put(name, gFClass);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }


    private static final Map<String,Class<? extends GameFactory<GameRuler<PieceModel<Species>>>>> boardFactories;

    static {
        boardFactories = new ConcurrentHashMap<>();
        boardFactories.put("Othello", OthelloFactory.class);
        boardFactories.put("m,n,k-game", MNKgameFactory.class);
        boardFactories.put("Scacchi", ScacchiFactory.class);
        boardFactories.put("MyBreakthrough", MyBreakthroughFactory.class);
        boardFactories.put("MyCamelot", MyCamelotFactory.class);
    }
}
