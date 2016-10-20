package gapp.ulg.play;

import gapp.ulg.Utilities.SituationUtilities;
import gapp.ulg.game.board.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;

import static gapp.ulg.game.board.GameRuler.Mechanics;
import static gapp.ulg.game.board.GameRuler.Situation;


/** <b>IMPLEMENTARE I METODI SECONDO LE SPECIFICHE DATE NEI JAVADOC. Non modificare
 * le intestazioni dei metodi.</b>
 * <br>
 * Un oggetto {@code MCTSPlayer} è un giocatore che gioca seguendo una strategia
 * basata su Monte-Carlo Tree Search e può giocare a un qualsiasi gioco.
 * <br>
 * La strategia che usa è una MCTS (Monte-Carlo Tree Search) piuttosto semplificata.
 * Tale strategia si basa sul concetto di <i>rollout</i> (srotolamento). Un
 * <i>rollout</i> a partire da una situazione di gioco <i>S</i> è l'esecuzione di
 * una partita fino all'esito finale a partire da <i>S</i> facendo compiere ai
 * giocatori mosse random.
 * <br>
 * La strategia adottata da un {@code MCTSPlayer}, è la seguente. In ogni situazione
 * di gioco <i>S</i> in cui deve muovere, prima di tutto ottiene la mappa delle
 * possibili mosse valide da <i>S</i> con le corrispondenti prossime situazioni. Per
 * ogni prossima situazione <i>NS</i> esegue <i>R</i> rollouts e calcola un punteggio
 * di <i>NS</i> dato dalla somma degli esiti dei rollouts. L'esito di un rollout è
 * rappresentato da un intero che è 0 se finisce in una patta, 1 se finisce con la
 * vittoria del giocatore e -1 altrimenti. Infine sceglie la mossa che porta nella
 * prossima situazione con punteggio massimo. Il numero <i>R</i> di rollouts da
 * compiere è calcolato così <i>R = ceil(RPM/M)</i>, cioè la parte intera superiore
 * della divisione decimale del numero di rollout per mossa <i>RPM</i> diviso il
 * numero <i>M</i> di mosse possibili (è sempre esclusa {@link Move.Kind#RESIGN}).
 * @param <P>  tipo del modello dei pezzi */
public class MCTSPlayer<P> implements Player<P> {



    private final String name;
    private final int rolloutsPerMove;
    private final ExecutorService executorService;
    private final Function<Situation<P>, Move<P>> getMove;
    private volatile GameRuler<P> gameRuler;
    private volatile Mechanics<P> gameMechanics;
    private int turn;



    /** Crea un {@code MCTSPlayer} con un limite dato sul numero di rollouts per
     * mossa.
     *
     * @param name  il nome del giocatore
     * @param rpm   limite sul numero di rollouts per mossa, se < 1 è inteso 1
     * @param parallel  se true la ricerca della mossa da fare è eseguita cercando
     *                  di sfruttare il parallelismo della macchina
     * @throws NullPointerException se {@code name} è null */
    public MCTSPlayer(String name, int rpm, boolean parallel) {
        if(name == null) throw new NullPointerException("Nome non valido.");
        this.name = name;
        rolloutsPerMove = (rpm < 1 ? 1 : rpm);
        if (parallel) {
            executorService = Executors.newCachedThreadPool(r -> {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                return thread;
            });
            getMove = this::getParallelMove;

        } else {
            executorService = null;
            getMove = this::getNormalMove;
        }
    }

    @Override
    public String name() { return name; }

    @Override
    public void setGame(GameRuler<P> g) {
        if(g == null) throw new NullPointerException("GameRuler non valido");
        gameRuler = g;
        gameMechanics = gameRuler.mechanics();
        turn = -1;
    }

    @Override
    public void moved(int i, Move<P> m) {
        if (gameRuler == null || gameRuler.result() != -1) throw new IllegalStateException("GameRuler non impostato o partita conclusa");
        if(m == null || !gameRuler.isValid(m)) throw new NullPointerException("Mossa non valida");
        gameRuler.isPlaying(i); // LANCIA ECCEZIONE SUI TURNI
        gameRuler.move(m);
    }

    @Override
    public Move<P> getMove() {
        if (gameRuler == null || gameRuler.result() != -1) throw new IllegalStateException("gameRuler non impostato o partita conclusa");
        if(turn == -1) turn = gameRuler.turn();                                                                         // se è la prima mossa imposta il turno del gameRuler
        if(turn != -1 && gameRuler.turn() != turn) throw new IllegalStateException("Turno non corrispondente");         // se non è la prima mossa e i turni non corrispondono
        Board<P> board = gameRuler.getBoard();

        Move<P> bestmove = null;
        if(gameRuler.mechanics().time != -1){
            FutureTask<Move<P>> future = new FutureTask<>(() -> getMove.apply(SituationUtilities.situationOfBoard(board, turn)));
            Thread t = new Thread(future);
            t.setDaemon(true);
            t.start();

            try {
                bestmove = future.get(gameRuler.mechanics().time, TimeUnit.MILLISECONDS);
            } catch (CancellationException | InterruptedException | TimeoutException | ExecutionException e) {}
            future.cancel(true);
            if(bestmove == null){
                List<Move<P>> movesList = new ArrayList<Move<P>>(gameRuler.validMoves());
                movesList.remove(new Move<P>(Move.Kind.RESIGN));
                bestmove = movesList.get(new Random().nextInt(movesList.size()));
                return bestmove;
            }
        }
        else {
            bestmove = getMove.apply(SituationUtilities.situationOfBoard(board, turn));
        }
        return bestmove;
    }

    private Move<P> getParallelMove(Situation<P> start) {
        Map<Move<P>, Situation<P>> nextMoveSituationMap = gameMechanics.next.get(start);
        List<Move<P>> movesList = new ArrayList<>(nextMoveSituationMap.keySet());
        int value = 0;
        Move<P> best = null;
        int nr = Math.max(1, rolloutsPerMove/(movesList.size()));
        List<Future<Map<Move<P>, Integer>>> tasks = new ArrayList<>();
        for (int i = 0 ; i < movesList.size() ; i++) {
            int mi = i;
            Situation<P> si = nextMoveSituationMap.get(movesList.get(i));
            tasks.add(executorService.submit(() -> {
                int result = moveScore(si, nr);
                Map<Move<P>, Integer> moveToScore = new HashMap<>();
                moveToScore.put(movesList.get(mi), result);
                return moveToScore;
            }));
        }
        try {
            for (Future<Map<Move<P>, Integer>> task : tasks) {
                Map<Move<P>, Integer> result = task.get();
                Integer moveValue = new ArrayList<Integer>(result.values()).get(0);
                Move<P> move = new ArrayList<Move<P>>(result.keySet()).get(0);
                if (moveValue > value || best == null) {
                    value = moveValue;
                    best = move;
                }
            }
        } catch (InterruptedException | ExecutionException e) { }
        return best;
    }

    private int situationRollout(Situation<P> startingSituation){
        Situation<P> currentSituation = startingSituation;
        while(currentSituation.turn > 0){
            currentSituation = SituationUtilities.randomNextSituation(currentSituation, gameMechanics);
        }
        int result = (currentSituation.turn == -turn ? 1 : (currentSituation.turn == 0 ? 0 : -1));
        return result;
    }

    private int moveScore(Situation<P> startingSituation, int rolloutsNumber){
        int wins = 0, loss = 0;
        Situation<P> start = startingSituation;
        for (int i = 0 ; i < rolloutsNumber; i++) {
            int rolloutResult = situationRollout(start);
            if (rolloutResult == 1) wins++;
            else if (rolloutResult == -1) loss++;
        }
        return wins - loss;
    }


    /** DA AGGIUSTARE **/
    private Move<P> getNormalMove(Situation<P> startingSituation){
        Map<Move<P>, Situation<P>> map = gameMechanics.next.get(startingSituation);
        int value = 0;
        Move<P> best = null;
        int nr = Math.max(1, rolloutsPerMove/(map.keySet().size()));
        for (Move<P> move : map.keySet()) {
            int currentValue = moveScore(map.get(move), nr);
            if (currentValue > value || best == null) {
                value = currentValue;
                best = move;
            }
        }
        return best;
    }
}
