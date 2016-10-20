package gapp.ulg.Utilities;

import gapp.ulg.game.board.*;
import gapp.ulg.game.board.Action;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.BiFunction;

import static gapp.ulg.game.board.Action.Kind.*;
import static gapp.ulg.game.board.Board.Dir;
import static gapp.ulg.game.board.Board.Dir.*;

/**
 * Created by Edoardo on 19/07/2016.
 */
public class UsefulMethods {
    /**
     * Data in input una serie di liste ritorna la più più grande sottolista tra esse a partire dall'indice startIndex
     * @param <T> tipo delle liste
     * @param startIndex indice di partenza per lo scorrimento delle liste
     * @param allLists liste in input
     * @return la più grande sub list a partire dall'indice startIndex
     */
    public static <T> ArrayList<T> biggestSubList(int startIndex, ArrayList<ArrayList<T>> allLists){
        int minSize = Integer.MAX_VALUE;
        ArrayList<T> biggestSubList = new ArrayList<>();

        for (ArrayList<T> currentList : allLists) {
            minSize = Math.min(minSize, currentList.size());
        }

        while(startIndex < minSize) {
            Set<T> setForIndex = new HashSet<>();
            for(ArrayList<T> list : allLists){
                setForIndex.add(list.get(startIndex));
            }
            if(setForIndex.size() > 1) return biggestSubList;
            startIndex++;
            biggestSubList.add(setForIndex.iterator().next());
        }
        return biggestSubList;
    }


    public static <P> ArrayList<ArrayList<Action<P>>> moveListToActionList(List<Move<P>> movesList){
        ArrayList<ArrayList<Action<P>>> actionList = new ArrayList<>();
        movesList.forEach((move) -> {
            ArrayList<Action<P>> moveActions = new ArrayList<>();
            moveActions.addAll(move.actions);
            actionList.add(moveActions);
        });
        return actionList;
    }

    public static Dir oppositeDir(Dir dir){
        if(dir == UP) return DOWN;
        if(dir == DOWN) return UP;
        if(dir == LEFT) return RIGHT;
        if(dir == RIGHT) return LEFT;
        if(dir == DOWN_L) return UP_R;
        if(dir == DOWN_R) return UP_L;
        if(dir == UP_R) return DOWN_L;
        return DOWN_R;
    }

    // TODO: da riguardare
    public static <T> T[] revertListToArray(ArrayList<T> startList){
        List<T> copyList = (List<T>) startList.clone();
        Collections.reverse(copyList);
        T[] revertedArray =  (T[]) Array.newInstance(startList.get(0).getClass(), startList.size());
        for(int i = 0; i < startList.size(); i++) revertedArray[i] = copyList.get(i);
        return revertedArray;
    }

    public static MyCouple<Dir, Integer> calcDir(Pos f, Pos s){
        if(f.b - s.b == 0){
            if(f.t > s.t) return new MyCouple<>(DOWN, Math.abs(f.t-s.t));
            else if(f.t < s.t) return new MyCouple<>(UP, Math.abs(f.t-s.t));
            return null;
        }
        else if(f.t - s.t == 0){
            if(f.b > s.b) return new MyCouple<>(LEFT, Math.abs(f.b-s.b));
            else if(f.b < s.b) return new MyCouple<>(RIGHT, Math.abs(f.b-s.b));
            return null;
        }
        else if(Math.abs(f.b - s.b) == Math.abs(f.t - s.t)){
            if(f.b > s.b && f.t > s.t) return new MyCouple<>(DOWN_L, Math.abs(f.t-s.t));
            if(f.b > s.b && f.t < s.t) return new MyCouple<>(UP_L, Math.abs(f.t-s.t));
            if(f.b < s.b && f.t > s.t) return new MyCouple<>(DOWN_R, Math.abs(f.t-s.t));
            if(f.b < s.b && f.t < s.t) return new MyCouple<>(UP_R, Math.abs(f.t-s.t));
        }
        return null;
    }

    public static Pos lastPosLastAction(Move<PieceModel<PieceModel.Species>> move){
        Action<PieceModel<PieceModel.Species>> lastAction = move.actions.get(move.actions.size()-1);
        return lastAction.pos.get(lastAction.pos.size()-1);
    }

    // TODO Per ottimizzare si può cambiare adjacentFunction con una funzione più veloce
    public static Pos calcPos(Pos start, BiFunction<Pos, Dir, Pos> adjacentFunction, Dir dir, int steps){
        Pos st = new Pos(start.b, start.t); 
        for(int i = 0; i < steps; i++){
            st = adjacentFunction.apply(st, dir);    
        }
        return st;
    }

    public static <P> List<P> doAction(Map<Pos, P> boardMap, Action<P> action, BiFunction<Pos, Dir, Pos> adjacentFunction) {
        List<P> pieces = new ArrayList<>();
        switch (action.kind) {
            case ADD:
                boardMap.put(action.pos.get(0), action.piece);
                break;
            case JUMP:
                pieces.add(boardMap.put(action.pos.get(1), boardMap.remove(action.pos.get(0))));
                break;
            case REMOVE:
                action.pos.forEach(p -> pieces.add(boardMap.remove(p)));
                break;
            case MOVE:
                action.pos.forEach(p -> {pieces.add(boardMap.put(calcPos(p,adjacentFunction,action.dir,action.steps), boardMap.get(p))); boardMap.remove(p);});
                break;
            case SWAP:
                action.pos.forEach(p -> pieces.add(boardMap.put(p, action.piece)));
                break;
        }
        return pieces;
    }

    public static <P> Map<Pos, P> createMap(Board<P> board) {
        Map<Pos, P> board_Map = new HashMap<>();
        board.positions().forEach(p -> {
            board_Map.put(p, board.get(p));
        });
        return new HashMap<>(board_Map);
    }

    //TODO sposta da un'altra parte e rimetti tutto apposto compreso gli include
    public static <P> ArrayList<Action<P>> revertedAction(Action<P> action, BiFunction<Pos, Board.Dir, Pos> adjacentFunction, List<P> affectedPieces){

        ArrayList<Action<P>> reversedAction = new ArrayList<>();

        // TODO da cambiare
        ArrayList<Pos> posList = new ArrayList<>();
        for(Pos p : action.pos){
            posList.add(p);
        }
        Pos[] posArray = posList.toArray(new Pos[posList.size()]);

        // 1) CASO Remove
        if(action.kind == ADD) reversedAction.add(new Action<P>(posArray));

        //2) CASO ADD
        if(action.kind == REMOVE){
            for(int i = 0; i < posList.size(); i++){
                Pos position = posList.get(i);
                P originalPiece = affectedPieces.get(i);
                reversedAction.add(new Action<P>(position, originalPiece));
            }
        }

        //3 CASO MOVE
        ArrayList<Pos> finalPositions = new ArrayList<>();
        //posList.forEach(pos -> finalPositions.add(UsefulMethods.calcPos(pos, adjacentFunction, UsefulMethods.oppositeDir(action.dir), action.steps)));
        posList.forEach(pos -> finalPositions.add(UsefulMethods.calcPos(pos, adjacentFunction, action.dir, action.steps)));
        if(action.kind == MOVE) reversedAction.add(new Action<>(UsefulMethods.oppositeDir(action.dir), action.steps, (Pos[]) finalPositions.toArray(new Pos[finalPositions.size()])));

        //4 CASO JUMP
        if(action.kind == JUMP){
            reversedAction.add(new Action<>(UsefulMethods.revertListToArray(posList)[0], UsefulMethods.revertListToArray(posList)[1]));
        }

        // 5 CASO SWAP
        if(action.kind == SWAP){
            for(int i = 0; i < posList.size(); i++){
                Pos p = action.pos.get(i);
                P originalPiece = affectedPieces.get(i);
                reversedAction.add(new Action<P>(originalPiece, p));
            }
        }
        return reversedAction;
    }

}
