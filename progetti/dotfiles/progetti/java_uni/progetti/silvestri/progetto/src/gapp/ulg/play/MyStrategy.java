package gapp.ulg.play;
import gapp.ulg.game.board.GameRuler;
import gapp.ulg.game.board.GameRuler.*;
import gapp.ulg.game.board.*;
import gapp.ulg.Utilities.serializedEncoding;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class MyStrategy<P> implements OptimalPlayerFactory.Strategy<P>, Serializable {

    private final String gameName;
    private transient volatile Supplier<Boolean> interrupted;

    private transient volatile boolean bgComputingStarted = false;
    private transient volatile ExecutorService backgroundExecutor = null;
    private transient List<Situation<P>> unchartedList = Collections.synchronizedList(new ArrayList<Situation<P>>());

    private transient volatile ForkJoinPool fJPool = null;

    private transient final GameRuler.Mechanics<P> gameMechanics; // TODO CONTROLLARE SE NAME SI RIFERISCE A GAME MECHANICS //che cazzo è name???
    private transient final GameRuler.Next<P> next; // transient perchè non va scritta nel file
    private final boolean parallel;

    // TODO 1) Da risistemare tutta questa parte. In particolare bisogna verificare se le due mappe sono veramente Serializable
    private final ConcurrentHashMap<BigInteger, Integer> computedMapParallel;


    public MyStrategy(String gameName, boolean parallel, GameRuler.Mechanics<P> gameMechanics, Supplier<Boolean> interrupted) {
        this.gameName = gameName;
        this.interrupted = interrupted;
        this.gameMechanics = gameMechanics;
        this.next = gameMechanics.next;
        this.parallel = parallel;


        this.computedMapParallel =  new ConcurrentHashMap<>();
    }

    public String gName() {
        return gameName;
    }

    public Integer computeStrategy(Situation<P> start){
        if(parallel != false && fJPool != null) return computeStrategyForkJoin(start);
        return computeStrategyNoThreads(start);
    }

    public Integer computeStrategyNoThreads(Situation<P> start) {
        boolean won = false;
        BigInteger encoded = new serializedEncoding().apply(this.gameMechanics, start);
        if (computedMapParallel.get(encoded) == null) {
            Map<Move<P>,Situation<P>> nextSituations = next.get(start);
            Integer situationValue = 0;
            for (Situation<P> nextSituation : nextSituations.values()) {
                if (nextSituation.turn <= 0) {
                    if (-nextSituation.turn == start.turn) {
                        situationValue = start.turn+1;
                        won = true;
                        if (unchartedList != null) {
                            if (unchartedList.size() == 0)
                                unchartedList.add(start);
                            else if (!unchartedList.get(0).equals(start))
                                unchartedList.add(start);
                        }
                        break;
                    } else if (nextSituation.turn == 0 && situationValue == 0)
                        situationValue = 1;
                }
            }
            if (this.interrupted != null && this.interrupted.get()) throw new UnsupportedOperationException("Esecuzione interrotta");
            if(won == false){
                for (Situation<P> nextSituation : nextSituations.values()) {
                    if (this.interrupted != null && this.interrupted.get()) throw new UnsupportedOperationException("Esecuzione interrotta");
                    Integer recursiveValue = computeStrategyNoThreads(nextSituation);
                    if (recursiveValue == start.turn+1) {
                        situationValue = recursiveValue;
                        if (unchartedList != null) {
                            if (unchartedList.size() == 0)
                                unchartedList.add(start);
                            else if (!unchartedList.get(0).equals(start))
                                unchartedList.add(start);
                        }
                        break;
                    }
                    else if (recursiveValue == 1 && situationValue == 0)
                        situationValue = 1;
                }
            }
            computedMapParallel.put(encoded, situationValue == 0 ? (3 - start.turn)+1 : situationValue);
        }
        return computedMapParallel.get(encoded);
    }

    public Integer computeStrategyForkJoin(Situation<P> start){
        return new ComputeTask(start, this.next).compute();
    }

    @Override
    public Move<P> move(GameRuler.Situation<P> s, GameRuler.Next<P> next) {
        if (backgroundExecutor != null && !bgComputingStarted && unchartedList != null) {
            bgComputingStarted = true;
            startComputingBackground();
        }
        Map<Move<P>,Situation<P>> nextMap = next.get(s);
        Move<P> bestMove = null;
        boolean won = false, draw = false, loss = true;
        Integer situationValue = 0;
        List<Move<P>> nonFinalMoves = new ArrayList<>();
        for (Map.Entry<Move<P>, Situation<P>> entry: nextMap.entrySet()) {
            Move<P> nextMove = entry.getKey();
            Situation<P> nextSituation = entry.getValue();
            if (nextSituation.turn <= 0) {
                if (-nextSituation.turn == s.turn) {
                    bestMove = nextMove;
                    won = true; loss = false;
                    break;
                }
                else if (nextSituation.turn == 0 && won == false) {
                    bestMove = nextMove;
                    draw = true; loss = false;
                }
            } else
                nonFinalMoves.add(nextMove);
        }
        if (won == false) {
            for (Move<P> move : nonFinalMoves) {
                Situation<P> nextSituation = nextMap.get(move);
                Integer recursiveValue = computeStrategy(nextSituation);
                if (recursiveValue == s.turn+1) {
                    bestMove = move;
                    won = true; loss = false; draw = false;
                    break;
                } else if (recursiveValue == 1 && situationValue == 0) {
                    bestMove = move;
                    situationValue = 1;
                    loss = false; draw = true;
                }
            }
        }
        return bestMove;
    }

    //Levare la P qui perchè fa in conflitto con quella di MyStrategy<P>
    private class ComputeTask <P> extends RecursiveTask<Integer> {

        private final Situation<P> situation;
        private final Next<P> recNext;

        public ComputeTask(Situation<P> situation, Next<P> recNext){
            this.situation = situation;
            this.recNext = recNext;
        }

        @Override
        // TODO BISOGNA PER FORZA FARE DUE FUNZIONI DISTINTE PER IL CUSTOM E PER IL FORK & JOIN PERCHE' IL CONTROLLO IF AUMENTA TROPPO IL NUMERO DEI THREAD
        protected Integer compute() {
            BigInteger encoded = new serializedEncoding().apply(gameMechanics, situation);
            List<Situation<P>> nonFinalSituations = new ArrayList<>();
            if(computedMapParallel.get(encoded) == null){
                Map<Move<P>,GameRuler.Situation<P>> nextSituations = recNext.get(situation);
                Integer situationValue = 0;
                for (Situation<P> nextSituation : nextSituations.values()) {
                    if (nextSituation.turn <= 0) {
                        if (-nextSituation.turn == situation.turn) {
                            situationValue = situation.turn+1;
//                             if (unchartedList != null) unchartedList.add(situation);  <-- TODO Rimettere questo quando è aggiustato P
                            break;
                        } else if (nextSituation.turn == 0 && situationValue == 0)
                        {
                            situationValue = 1;
                        }
                    }
                    else nonFinalSituations.add(nextSituation);
                }
                if (interrupted != null && interrupted.get()) {
                    throw new UnsupportedOperationException("Compiutazione interrotta");
                }
                if(situationValue <= 1){
                    List<RecursiveTask<Integer>> forks = new LinkedList<>();
                    for(Situation<P> nextSituation : nonFinalSituations){
                        ComputeTask<P> task = new ComputeTask<>(nextSituation, recNext);
                        forks.add(task);
                        if(fJPool == null) task.fork();
                        else fJPool.submit(task);
                    }
                    if (interrupted != null && interrupted.get()) throw new UnsupportedOperationException("Compiutazione interrotta");
                    for (RecursiveTask<Integer> task : forks) {
                        Integer recursiveValue = fJPool == null ? task.join() : task.invoke();
                        if (recursiveValue == situation.turn+1) {
                            situationValue = recursiveValue;
//                            if (unchartedList != null) unchartedList.add(situation);   <--- TODO Rimettere questo quando è aggiustata la P
                            break;
                        }
                        else if (recursiveValue == 1 && situationValue == 0)
                            situationValue = 1;
                    }
                }
                computedMapParallel.put(encoded, situationValue == 0 ? (3 - situation.turn)+1 : situationValue);
            }
            return computedMapParallel.get(encoded);
        }
    }

    public void startComputingBackground() {
        backgroundExecutor.submit(() -> {
            while (unchartedList.size() > 0) {
                computeStrategyNoThreads(unchartedList.get(0));
                unchartedList.remove(0);
            }
        });
    }

    public void endComputingBackground() {
        backgroundExecutor.shutdownNow();
    }


    // Per impostare il fixedThread, il forkJoin e il background executor
    public void setService(Integer nThreads, ForkJoinPool fjp, ExecutorService bgExec){
        fJPool = fjp;
        backgroundExecutor = bgExec;
    }

    //
    private static final long serialVersionUID = 6529685098267757690L;
}
