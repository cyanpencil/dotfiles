package gapp.ulg.test.Homework2;

import gapp.ulg.game.board.GameRuler;
import gapp.ulg.game.board.Move;
import gapp.ulg.game.board.PieceModel;
import gapp.ulg.game.board.Pos;
import gapp.ulg.game.util.Probe;
import gapp.ulg.games.MNKgame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static gapp.ulg.game.util.Probe.nextSituations;

/**
 * Created by Edoardo on 15/07/2016.
 */
public class nextSituations {
    private static PieceModel<PieceModel.Species> blackdisc = new PieceModel<>(PieceModel.Species.DISC, "nero");           // white disc
    private static PieceModel<PieceModel.Species> whitedisc = new PieceModel<>(PieceModel.Species.DISC, "bianco");         // black disc

    public static void main(String[] args){
        testNextSituation();
    }

    public static <P,S> void testNextSituation(){
        MNKgame gR = new MNKgame(-1, 3, 3, 3, "Edoardo", "Luca");
        gR.board.put(whitedisc, new Pos(2, 0));
        gR.board.put(whitedisc, new Pos(2, 1));
        gR.board.put(whitedisc, new Pos(1, 2));
        gR.board.put(blackdisc, new Pos(0, 2));
        gR.board.put(blackdisc, new Pos(1, 1));
        gR.board.put(blackdisc, new Pos(2, 2));
        List<Move<PieceModel<PieceModel.Species>>> validMoves = new ArrayList(gR.validMoves());
        gR.move(validMoves.get(3));

        List<Move<PieceModel<PieceModel.Species>>> validMoves2 = new ArrayList(gR.validMoves());
        gR.move(validMoves2.get(1));
        gR.printBoard();

    }
}
