package gapp.ulg.games;

import gapp.ulg.game.GameFactory;
import gapp.ulg.game.Param;
import gapp.ulg.game.board.GameRuler;
import gapp.ulg.game.board.PieceModel;
import gapp.ulg.game.board.PieceModel.Species;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Edoardo on 07/08/2016.
 */
public class MyBreakthroughFactory implements GameFactory<GameRuler<PieceModel<Species>>> {
    @Override
    public String name() {
        return "MyBreakthrough";
    }

    @Override
    public int minPlayers() {
        return 2;
    }

    @Override
    public int maxPlayers() {
        return 2;
    }

    @Override
    public List<Param<?>> params() {
        return Collections.unmodifiableList(Collections.singletonList(timeParam));
    }

    Param<String> timeParam = new Param<String>() {

        String value = "No limit";

        @Override
        public String name() {
            return "Time";
        }

        @Override
        public String prompt() {
            return "Time limit for a Scacchi move";
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

    @Override
    public void setPlayerNames(String... names) {
        if(Arrays.asList(names).contains(null)) throw new NullPointerException("Inserire giocatori validi");
        if (names.length != 2) throw new IllegalArgumentException("Inserire esattamente due giocatori");
        playerNames = new ArrayList<String>(Arrays.asList(names));
    }

    @Override
    public GameRuler<PieceModel<Species>> newGame() {
        if (playerNames == null || playerNames.size() == 0) throw new IllegalStateException("Giocatori non impostati");
        long time = TO_MS.get(TIMES.indexOf(timeParam.get()));
        return new MyBreakthrough(time, playerNames.get(0), playerNames.get(1));
    }



    private static final List<String> TIMES = Arrays.asList("No limit","1s","2s","3s","5s","10s","20s","30s","1m","2m","5m");
    private static final List<Long> TO_MS = Arrays.asList(-1L,1000L,2000L,3000L,5000L,10000L,20000L,30000L,600000L,120000L,300000L);
    private List<String> playerNames;
}
