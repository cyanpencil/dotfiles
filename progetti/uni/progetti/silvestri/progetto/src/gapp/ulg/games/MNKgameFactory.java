package gapp.ulg.games;

import gapp.ulg.game.Param;
import gapp.ulg.game.board.GameRuler;
import gapp.ulg.game.board.PieceModel;
import gapp.ulg.game.GameFactory;

import static gapp.ulg.game.board.PieceModel.Species;

import java.util.*;

/** <b>IMPLEMENTARE I METODI SECONDO LE SPECIFICHE DATE NEI JAVADOC. Non modificare
 * le intestazioni dei metodi.</b>
 * <br>
 * Una {@code MNKgameFactory} è una fabbrica di {@link GameRuler} per giocare a
 * (m,n,k)-game. I {@link GameRuler} fabbricati dovrebbero essere oggetti
 * {@link MNKgame}. */
public class MNKgameFactory implements GameFactory<GameRuler<PieceModel<Species>>> {
    @Override
    public String name() { return "m,n,k-game"; }

    @Override
    public int minPlayers() { return 2; }

    @Override
    public int maxPlayers() { return 2; }



    Param<String> timeParam = new Param<String>() {

        String value = "No limit";

        @Override
        public String name() {
            return "Time";
        }

        @Override
        // TODO correggi errori
        public String prompt() {
            return "Time limit for a OthelloMoveSituation";
        }

        @Override
        public List<String> values() {
            return Collections.unmodifiableList(new ArrayList<>(Arrays.asList("No limit","1s","2s","3s","5s","10s","20s","30s","1m","2m","5m")));
        }

        @Override
        public void set(Object v) {
            if(!this.values().contains(v)) throw new IllegalArgumentException("Valore non valido");
            value = (String) v;
        }

        @Override
        public String get() {
            return value;
        }
    };
    Param<Integer> mParam = new Param<Integer>() {

        int value = 3;

        @Override
        public String name() {
            return "M";
        }

        @Override
        public String prompt() {
            return "Board width";
        }

        @Override
        public List<Integer> values() {
            List<Integer> values = new ArrayList<>();
            int minM = 1;
            if(nParam.get() < kParam.get()) minM = kParam.get();
            for(int i = minM; i <= 20; i++){
                values.add(i);
            }
            return Collections.unmodifiableList(values);
        }

        @Override
        public void set(Object v) {
            if(!this.values().contains(v)) throw new IllegalArgumentException("Valore non valido");
            this.value = (int) v;
        }

        @Override
        public Integer get() {
            return this.value;
        }
    };
    Param<Integer> nParam = new Param<Integer>() {
        int value = 3;

        @Override
        public String name() {
            return "N";
        }

        @Override
        public String prompt() {
            return "Board height";
        }

        @Override
        public List<Integer> values() {
            List<Integer> values = new ArrayList<>();
            int minM = 1;
            if(mParam.get() < kParam.get()) minM = kParam.get();
            for(int i = minM; i <= 20; i++){
                values.add(i);
            }
            return Collections.unmodifiableList(values);
        }

        @Override
        public void set(Object v) {
            if(!this.values().contains(v)) throw new IllegalArgumentException("Valore non valido");
            this.value = (int) v;
        }

        @Override
        public Integer get() {
            return this.value;
        }
    };
    Param<Integer> kParam = new Param<Integer>() {

        int value = 3;

        @Override
        public String name() {
            return "K";
        }

        @Override
        public String prompt() {
            return "Length of line";
        }

        @Override
        public List<Integer> values() {
            List<Integer> values = new ArrayList<>();
            int minM = 1;
            for(int i = 1; i <= Math.max(mParam.get(), nParam.get()); i++)
                values.add(i);
            return Collections.unmodifiableList(values);
        }

        @Override
        public void set(Object v) {
            if(!this.values().contains(v)) throw new IllegalArgumentException("Valore non valido");
            this.value = (int) v;
        }

        @Override
        public Integer get() {
            return this.value;
        }
    };


    /** Ritorna una lista con i seguenti quattro parametri:
     * <pre>
     * Primo parametro, valori di tipo String
     *     - name: "Time"
     *     - prompt: "Time limit for a OthelloMoveSituation"
     *     - values: ["No limit","1s","2s","3s","5s","10s","20s","30s","1m","2m","5m"]
     *     - default: "No limit"
     * Secondo parametro, valori di tipo Integer
     *     - name: "M"
     *     - prompt: "Board width"
     *     - values: [1,2,3,...,20]
     *     - default: 3
     * Terzo parametro, valori di tipo Integer
     *     - name: "N"
     *     - prompt: "Board height"
     *     - values: [1,2,3,...,20]
     *     - default: 3
     * Quarto parametro, valori di tipo Integer
     *     - name: "K"
     *     - prompt: "Length of line"
     *     - values: [1,2,3]
     *     - default: 3
     * </pre>
     * Per i parametri "M","N" e "K" i valori ammissibili possono cambiare a seconda
     * dei valori impostati. Più precisamente occorre che i valori ammissibili
     * garantiscano sempre le seguenti condizioni
     * <pre>
     *     1 <= K <= max{M,N} <= 20   AND   1 <= min{M,N}
     * </pre>
     * dove M,N,K sono i valori impostati. Indicando con minX, maxX il minimo e il
     * massimo valore per il parametro X le condizioni da rispettare sono:
     * <pre>
     *     minM <= M <= maxM
     *     minN <= N <= maxN
     *     minK <= K <= maxK
     *     minK = 1  AND  maxK = max{M,N}  AND  maxN = 20  AND  maxN = 20
     *     N >= K  IMPLICA  minM = 1
     *     N < K   IMPLICA  minM = K
     *     M >= K  IMPLICA  minN = 1
     *     M < K   IMPLICA  minN = K
     * </pre>
     * @return la lista con i quattro parametri */
    @Override
    public List<Param<?>> params() { return Collections.unmodifiableList(new ArrayList<Param<?>>(Arrays.asList(timeParam, mParam, nParam, kParam))); }

    @Override
    public void setPlayerNames(String... names) {
        if(Arrays.asList(names).contains(null)) throw new NullPointerException("Inserire giocatori validi");
        if (names.length != 2) throw new IllegalArgumentException("Inserire esattamente due giocatori");
        playerNames = new ArrayList<String>(Arrays.asList(names));
    }

    @Override
    public GameRuler<PieceModel<PieceModel.Species>> newGame() {
        if (playerNames == null || playerNames.size() == 0) throw new IllegalStateException("Giocatori non impostati");
        long time = TO_MS.get(TIMES.indexOf(timeParam.get()));
        return new MNKgame(time, mParam.get(), nParam.get(), kParam.get(), playerNames.get(0), playerNames.get(1));
    }

    private static final List<String> TIMES = Arrays.asList("No limit","1s","2s","3s","5s","10s","20s","30s","1m","2m","5m");
    private static final List<Long> TO_MS = Arrays.asList(-1L,1000L,2000L,3000L,5000L,10000L,20000L,30000L,600000L,120000L,300000L);
    private List<String> playerNames;
}
