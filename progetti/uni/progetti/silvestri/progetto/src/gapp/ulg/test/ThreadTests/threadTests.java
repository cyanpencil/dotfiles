package gapp.ulg.test.ThreadTests;

import gapp.ulg.game.GameFactory;
import gapp.ulg.game.PlayerFactory;
import gapp.ulg.game.board.GameRuler;
import gapp.ulg.game.board.Move;
import gapp.ulg.game.board.PieceModel;
import gapp.ulg.game.board.Player;
import gapp.ulg.games.MNKgameFactory;
import gapp.ulg.play.OptimalPlayerFactory;
import gapp.ulg.play.RandPlayer;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Edoardo on 16/07/2016.
 */
public class threadTests {

    private static <K,V> Map<K,V> getMap(K[] keys, V[] values) {
        Map<K,V> map = new HashMap<>();
        for (int i = 0 ; i < keys.length ; i++)
            map.put(keys[i], values[i]);
        return map;
    }

    public static void main(String[] args){
        //test thread
        test_OptimalThreads(new MNKgameFactory(), getMap(new String[] {"M","N","K"}, new Integer[] {5,3,5}), true, true, 10_000);
    }

    public static <P> void test_OptimalThreads(GameFactory<? extends GameRuler<P>> gF,
                                               Map<String,Object> pv,
                                               boolean parallel, boolean optFirst, int n){

    }
}
