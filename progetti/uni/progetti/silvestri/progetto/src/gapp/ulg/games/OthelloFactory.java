package gapp.ulg.games;

import gapp.ulg.game.GameFactory;
import gapp.ulg.game.Param;
import gapp.ulg.game.board.GameRuler;
import gapp.ulg.game.board.PieceModel;

import static gapp.ulg.game.board.PieceModel.Species;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** <b>IMPLEMENTARE I METODI SECONDO LE SPECIFICHE DATE NEI JAVADOC. Non modificare
 * le intestazioni dei metodi.</b>
 * <br>
 * Una OthelloFactory è una fabbrica di {@link GameRuler} per giocare a Othello.
 * I {@link GameRuler} fabbricati dovrebbero essere oggetti {@link Othello}. */
public class OthelloFactory implements GameFactory<GameRuler<PieceModel<Species>>> {

    private Param<String> TimeParam = new Param<String>() {

        private String value = "No limit";

        @Override
        public String name() {
            return "Time";
        }

        @Override
        public String prompt() {
            return "Time limit for a OthelloMoveSituation";
        }

        @Override
        public List<String> values() {
            return Collections.unmodifiableList(new ArrayList<String>(Arrays.asList("No limit","1s","2s","3s","5s","10s","20s","30s","1m","2m","5m")));
        }

        @Override
        public void set(Object v) {
            if(!this.values().contains(v)) throw new IllegalArgumentException("Valore non accettabile");
            this.value = (String) v;
        }

        @Override
        public String get() {
            return value;
        }
    };

    private Param<String> BoardParam = new Param<String>() {

        private String value = "8x8";

        @Override
        public String name() {
            return "Board";
        }

        @Override
        public String prompt() {
            return "Board size";
        }

        @Override
        public List<String> values() {
            return Collections.unmodifiableList(new ArrayList<String>(Arrays.asList("6x6","8x8","10x10","12x12")));
        }

        @Override
        public void set(Object v) {
            if(!this.values().contains(v)) throw new IllegalArgumentException("Valore non accettabile");
            this.value = (String) v;
        }

        @Override
        public String get() {
            return value;
        }
    };

    private List<Param<String>> paramList = new ArrayList<>(Arrays.asList(TimeParam, BoardParam));

    /** Crea una fattoria di {@link GameRuler} per giocare a Othello */
    public OthelloFactory() { }

    @Override
    public String name() { return "Othello"; }

    @Override
    public int minPlayers() { return 2; }

    @Override
    public int maxPlayers() { return 2; }

    /** Ritorna una lista con i seguenti due parametri:
     * <pre>
     * Primo parametro, valori di tipo String
     *     - name: "Time"
     *     - prompt: "Time limit for a OthelloMoveSituation"
     *     - values: ["No limit","1s","2s","3s","5s","10s","20s","30s","1m","2m","5m"]
     *     - default: "No limit"
     * Secondo parametro, valori di tipo String
     *     - name: "Board"
     *     - prompt: "Board size"
     *     - values: ["6x6","8x8","10x10","12x12"]
     *     - default: "8x8"
     * </pre>
     * @return la lista con i due parametri */
    @Override
    @SuppressWarnings("unchecked")
    public List<Param<?>> params() {
        return Collections.unmodifiableList(paramList);
    }
    @Override
    public void setPlayerNames(String... names) {
        if(Arrays.asList(names).contains(null)) throw new NullPointerException("Inserire giocatori validi");
        if (names.length != 2) throw new IllegalArgumentException("Inserire esattamente due giocatori");
        playerNames = new ArrayList<String>(Arrays.asList(names));
    }

    @Override
    public GameRuler<PieceModel<PieceModel.Species>> newGame() {
        if (playerNames == null || playerNames.size() == 0) throw new IllegalStateException("Giocatori non impostati");
        int size = Integer.valueOf(paramList.get(1).get().split("x")[0]);
        long time = TO_MS.get(TIMES.indexOf(paramList.get(0).get()));
        return new Othello(time, size, playerNames.get(0), playerNames.get(1));
    }

    private static final List<String> TIMES = Arrays.asList("No limit","1s","2s","3s","5s","10s","20s","30s","1m","2m","5m");
    private static final List<Long> TO_MS = Arrays.asList(-1L,1000L,2000L,3000L,5000L,10000L,20000L,30000L,60000L,120000L,300000L);
    private List<String> playerNames;
}
