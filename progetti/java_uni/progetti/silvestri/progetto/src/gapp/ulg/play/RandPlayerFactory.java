package gapp.ulg.play;

import gapp.ulg.game.GameFactory;
import gapp.ulg.game.Param;
import gapp.ulg.game.PlayerFactory;
import gapp.ulg.game.board.GameRuler;
import gapp.ulg.game.board.Player;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/** Una RandPlayerFactory fabbrica giocatori {@link RandPlayer}.
 * @param <P>  tipo del modello dei pezzi */
public class RandPlayerFactory<P> implements PlayerFactory<Player<P>,GameRuler<P>> {
    @Override
    public String name() { return "Random Player"; }

    @Override
    public void setDir(Path dir) { }

    @Override
    @SuppressWarnings("unchecked")
    public List<Param<?>> params() { return Collections.EMPTY_LIST; }

    @Override
    public Play canPlay(GameFactory<? extends GameRuler<P>> gF) {
        Objects.requireNonNull(gF);
        return Play.YES;
    }

    @Override
    public String tryCompute(GameFactory<? extends GameRuler<P>> gF, boolean parallel,
                             Supplier<Boolean> interrupt) {
        Objects.requireNonNull(gF);
        return null;
    }

    @Override
    public Player<P> newPlayer(GameFactory<? extends GameRuler<P>> gF, String name) {
        if (canPlay(gF) != Play.YES) throw new IllegalStateException();
        Objects.requireNonNull(name);
        return new RandPlayer<>(name);
    }
}
