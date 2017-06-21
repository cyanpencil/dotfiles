package gapp.ulg.play;

import gapp.ulg.game.GameFactory;
import gapp.ulg.game.Param;
import gapp.ulg.game.PlayerFactory;
import gapp.ulg.game.board.GameRuler;
import gapp.ulg.game.board.Player;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/** <b>IMPLEMENTARE I METODI SECONDO LE SPECIFICHE DATE NEI JAVADOC. Non modificare
 * le intestazioni dei metodi.</b>
 * <br>
 * Una MCTSPlayerFactory è una fabbrica di {@link MCTSPlayer}.
 * @param <P>  tipo del modello dei pezzi */
public class MCTSPlayerFactory<P> implements PlayerFactory<Player<P>,GameRuler<P>> {
    @Override
    public String name() { return "Monte-Carlo Tree Search Player"; }

    @Override
    public void setDir(Path dir) { }        // NON FA NIENTE

    /** Ritorna una lista con i seguenti due parametri:
     * <pre>
     * Primo parametro
     *     - name: "Rollouts"
     *     - prompt: "Number of rolloutsParam per move"
     *     - values: [1,10,50,100,200,500,1000]
     *     - default: 50
     * Secondo parametro
     *     - name: "Execution"
     *     - prompt: "Threaded executionParam"
     *     - values: ["Sequential","Parallel"]
     *     - default: "Sequential"
     * </pre>
     * @return la lista con i due parametri */
    @Override
    public List<Param<?>> params() {
        return params;
    }

    private final Param<Integer> rolloutsParam = new Param<Integer>() {
        Integer value = 50;

        @Override
        public String name() {
            return "Rollouts";
        }

        @Override
        public String prompt() {
            return "Number of rollouts per move";
        }

        @Override
        public List<Integer> values() {
            return Collections.unmodifiableList(Arrays.asList(1, 10, 50, 100, 200, 500, 1000));
        }

        @Override
        public void set(Object v) {
            if(!values().contains(v)) throw new IllegalArgumentException("Valore non consentito o non valido");
            this.value = (int) v;
        }

        @Override
        public Integer get() {
            return value;
        }
    };
    private final Param<String> executionParam = new Param<String>() {

        public String value = "Sequential";

        @Override
        public String name() {
            return "Execution";
        }

        @Override
        public String prompt() {
            return "Threaded execution";
        }

        @Override
        public List<String> values() {
            return Collections.unmodifiableList(Arrays.asList("Sequential", "Parallel"));
        }

        @Override
        public void set(Object v) {
            if(!values().contains(v)) throw new IllegalArgumentException("Valore non consentito o non valido");
            this.value = (String) v;
        }

        @Override
        public String get() {
            return this.value;
        }
    };
    private final List<Param<?>> params = Collections.unmodifiableList(Arrays.asList(rolloutsParam, executionParam));

    @Override
    public Play canPlay(GameFactory<? extends GameRuler<P>> gF) {
        if(gF == null) throw new NullPointerException("GameFactory non valido.");
        return Play.YES;
    }

    @Override
    public String tryCompute(GameFactory<? extends GameRuler<P>> gF, boolean parallel,
                             Supplier<Boolean> interrupt) {
        if(gF == null) throw new NullPointerException("GameFactory non valido.");
        return null;
    }

    /** Ritorna un {@link MCTSPlayer} che rispetta i parametri impostati
     * {@link MCTSPlayerFactory#params()} e il nome specificato. */
    @Override
    public Player<P> newPlayer(GameFactory<? extends GameRuler<P>> gF, String name) {
        if(name == null) throw new NullPointerException("Nome non valido.");
        if(gF == null) throw new NullPointerException("GameFactory non valido");
        if (canPlay(gF) != Play.YES) throw new IllegalStateException("Giocatore non disponibile a giocare a questo gioco");
        boolean parallel = executionParam.get().equals("Parallel");
        return new MCTSPlayer<>(name, rolloutsParam.get(), parallel);
    }
}
