package gapp.ulg.play;


//  TODO
//  - Vanno fatti ulteriori test sulla potenza di calcolo di move()
//  - flash_forkjoin è lento! No dai forse non moltissimo, va comunque
//    molto più veloce di quelli del gruppo
//  
//  daje però non siamo messi troppo male. Speriamo di riuscire a consegnare in tempo
    




import gapp.ulg.game.util.*;
import gapp.ulg.game.GameFactory;
import gapp.ulg.game.Param;
import gapp.ulg.game.PlayerFactory;
import gapp.ulg.game.board.GameRuler;
import gapp.ulg.game.board.Move;
import gapp.ulg.game.board.Player;

import static gapp.ulg.game.board.GameRuler.Situation;
import static gapp.ulg.game.board.GameRuler.Next;
import static gapp.ulg.game.board.GameRuler.Mechanics;

import java.nio.file.Path;
import java.nio.file.*;
import java.io.*;
import java.util.*;
import java.util.function.Supplier;
import java.util.concurrent.*;
import gapp.ulg.game.util.Probe.EncS;
import java.math.*;

/** <b>IMPLEMENTARE I METODI SECONDO LE SPECIFICHE DATE NEI JAVADOC. Non modificare
 * le intestazioni dei metodi.</b>
 * <br>
 * Una OptimalPlayerFactory è una fabbrica di {@link OptimalPlayer}
 * @param <P>  tipo del modello dei pezzi */
public class OptimalPlayerFactory<P> implements PlayerFactory<Player<P>,GameRuler<P>> {
    
    public List<String> nomi;
    public List<Param<?>> rers;
    public List<Param<?>> surs;
    public Path m_dir = null;
    public Map<String, Strategy<?>> mappa_strategie;
    
    public OptimalPlayerFactory() {
        rers = Arrays.asList(new Param<?> [] {uno});
        surs = Collections.unmodifiableList(rers);
        nomi = new ArrayList<>();
        mappa_strategie = new HashMap<>();
    }
    /** Una {@code Strategy} rappresenta una strategia ottimale per uno specifico
     * gioco.
     * @param <P>  tipo del modello dei pezzi */
    interface Strategy<P> {
        /** @return il nome del gioco di cui questa è una strategia ottimale */
        String gName();

        /** Ritorna la mossa (ottimale) nella situazione di gioco specificata. Se
         * {@code s} o {@code next} non sono compatibili con il gioco di questa
         * strategia, il comportamento del metodo è indefinito.
         * @param s  una situazione di gioco
         * @param next  la funzione delle mosse valide e prossime situazioni del
         *              gioco, cioè quella di {@link Mechanics#next}.
         * @return la mossa (ottimale) nella situazione di gioco specificata */
        Move<P> move(Situation<P> s, Next<P> next);
    }
    
    
    public class MahStrategy<P> implements Strategy<P>, Serializable {
        String nome;
        boolean parallel = false, parallel_move = false;
        public ConcurrentHashMap<Codez, Integer> precalc;
        public volatile Supplier<Boolean> interrotto;
        public volatile Mechanics<P> mec;
        public final int WIN = 2, DRAW = 1, LOSE = 0;
        
        
        
        
        
        public MahStrategy(String nome, boolean parallel, Mechanics<P> mec, Supplier<Boolean> interrotto) {
            this.nome = nome;
            this.parallel = parallel;
            this.interrotto = interrotto;
            this.mec = mec;
            precalc = new ConcurrentHashMap<>();
        }
        
        public int flash_forkjoin(Situation<P> inizio, Next<P> next) {
            Codez code = new Codez(new EncS<P>(mec, inizio).encod);
            if (precalc.containsKey(code)) return precalc.get(code);
            List<ForkJoinTask<Integer>> tasks = new ArrayList<>();
            Map<Move<P>, Situation<P>> mappa = next.get(inizio);
            List<Situation<P>> daflashare = new ArrayList<>();
            
            int res = LOSE;
            for (Situation<P> s : mappa.values()) {
                if (s.turn <= 0) {
                    if (s.turn == inizio.turn * -1) {
                        precalc.put(code, WIN);
                        return WIN;
                    }
                    else if (s.turn == 0) {
                        res = DRAW;
                    }
                }
                else {
                    daflashare.add(s);
                }
            }
            
            
            
            int n = 0;
            for (Situation<P> s : daflashare) {
                if (s.turn > 0) {
                    ForkJoinTask<Integer> cc = ForkJoinTask.adapt(() -> flash_forkjoin(s, next));
                    cc.fork();
                    tasks.add(cc);
                }
//                if (s.turn > 0) tasks.add(ForkJoinTask.adapt(() -> flash_forkjoin(s, next)));
//                if (s.turn > 0) {n++; tasks.add(exec.submit(() -> flash_forkjoin(s, next)));}
            }
            
            try {
//                for (int i = 0; i < n; i++) {
//                    Future<Integer> task = exec.take();
//                    int a = task.get();
//                    if (a == LOSE) {precalc.put(code, WIN); return WIN;}
//                    if (a == DRAW) res = DRAW;
//                }
                for (ForkJoinTask<Integer> task : ForkJoinTask.invokeAll(tasks)) {
                    int a = task.join();
                    if (a == LOSE) {precalc.put(code, WIN); return WIN;}
                    if (a == DRAW) res = DRAW;
                }
//                for (ForkJoinTask<Integer> task : tasks) {
//                    int a = task.invoke();
//                    if (a == LOSE) {precalc.put(code, WIN); return WIN;}
//                    if (a == DRAW) res = DRAW;
//                }
            } catch (Exception e) { }
//            finally {
//                ciao.shutdown();
//            }
            
            precalc.put(code, res);
            return res;
        }
        
        public void flash(Situation<P> inizio, Next<P> next) {
            Map<Move<P>, Situation<P>> mappa = next.get(inizio);
            Codez code = new Codez(new EncS<P>(mec, inizio).encod);
            //a questo punto tutte quelle successive dovrebbero essere state calcolate
            boolean is_victory = false, is_patta = false;
            for (Situation<P> s : mappa.values()) {
                if (s.turn <= 0) {
                    if (s.turn == inizio.turn * -1) {
                        is_victory = true;
//                        break;
                        precalc.put(code, WIN);
                        return;
                    }
                    else if (s.turn == 0) {
                        is_patta = true;
                    }
                    }
            }
            for (Situation<P> s : mappa.values()) {
                if (interrotto != null && interrotto.get() == true) throw new IllegalStateException("Interrotto.");
                if (s.turn > 0) {
                    Codez scode = new Codez(new EncS<P>(mec, s).encod);
                    if (! precalc.containsKey(scode)) {
                        flash(s, next);
                    }
                    Integer res = precalc.get(scode);
                    if (res != null) {
                        if (res == LOSE) {
                            is_victory = true;
                            break;
                        }
                        else if (res == DRAW) {
                            is_patta = true;
                        }
                    }
                    else {
                        throw new IllegalStateException("Non so che fare !!!");
                    }
                }
            }
            
            if (is_victory) {precalc.put(code, WIN); return;}
            if (is_patta) {precalc.put(code, DRAW); return;}
            precalc.put(code, LOSE);
            return;
        }

        public String gName() {
            return nome;
        }
        
        
        public Move<P> move(Situation<P> s, Next<P> next) {
            if (s == null || next == null) throw new NullPointerException();
            Map<Move<P>, Situation<P>> mappa = next.get(s);
            Move<P> vinci = null, pari = null, perdi = null;
            for (Map.Entry<Move<P>, Situation<P>> entry : mappa.entrySet()) {
                Situation<P> situazione = entry.getValue();
                if (situazione.turn <= 0) {
                    if (situazione.turn == s.turn * -1) {
                        vinci = entry.getKey();
                        break;
                    }
                    else if (situazione.turn == 0) {
                        pari = entry.getKey();
                    }
                    else {
                        perdi = entry.getKey();
                    }
                }
                else {
                    Codez code = new Codez(new EncS<P>(mec, situazione).encod);
                    if (! precalc.containsKey(code)) {
                        if (parallel_move) flash_forkjoin(situazione, next);
                        else flash(situazione, next);
//                        non sono sicuro che qui dovrei continuare. Forse dovrei calcolare altre mosse...?
//                        continue;
                    }
                    Integer res = precalc.get(code);
                    if (res != null) {
                        if (res == LOSE) {
                            vinci = entry.getKey();

                            break;
                        }
                        else if (res == DRAW) {
                            pari = entry.getKey();
                        }
                        else {
                            perdi = entry.getKey();
                        }
                    }
                    else {
                        throw new IllegalStateException("Non so che fare !!!");
                }
                }
            }
            if (!(vinci == null)) return vinci;
            if (!(pari == null)){ return pari; }
            if (!(perdi == null)) {
                return perdi;
            }
            throw new IllegalStateException("Non ho trovato proprio nessuna mossa da fare :(");
        }
        
    }

    @Override
    public String name() { return "Optimal Player"; }

    /** Se la directory non è null, in essa salva e recupera file che contengono le
     * strategie ottimali per giochi specifici. Ogni strategia è salvata nella
     * directory in un file il cui nome rispetta il seguente formato:
     * <pre>
     *     strategy_<i>gameName</i>.dat
     * </pre>
     * dove <code><i>gameName</i></code> è il nome del gioco, cioè quello ritornato
     * dal metodo {@link GameRuler#name()}. La directory di default non è impostata
     * e quindi è null. */
    @Override
    public void setDir(Path dir) {
        m_dir = dir;
    }
    
    Param<String> uno = new Param() {
        public String value = "Sequential";
        public String name() { return "Execution"; }
        public String prompt() { return "Threaded execution"; }
        public List<String> values() {
            String cacca [] =  {"Sequential", "Parallel"};
            List<String> valori = new ArrayList<>(Arrays.asList(cacca));
            return Collections.unmodifiableList(valori);
        }
        public void set(Object v) {
            if (!( v instanceof String)) throw new IllegalArgumentException();
            String m = (String) v;
            if (!values().contains(m)) throw new IllegalArgumentException();
            else this.value = m;
        }
        public String get() { return value; }
    };

    /** Ritorna una lista con il seguente parametro:
     * <pre>
     *     - name: "Execution"
     *     - prompt: "Threaded execution"
     *     - values: ["Sequential","Parallel"]
     *     - default: "Sequential"
     * </pre>
     * @return la lista con il parametro */
    @Override
    public List<Param<?>> params() {
        return surs;
    }

    /** Ritorna {@link Play#YES} se conosce già la strategia ottimale per il gioco
     * specificato o perché è in un file (nella directory impostata con
     * {@link OptimalPlayerFactory#setDir(Path)}) o perché è in memoria, altrimenti
     * stima se può essere praticamente possibile imparare la strategia
     * ottimale e allora ritorna {@link Play#TRY_COMPUTE} altrimenti ritorna
     * {@link Play#NO}. Il gioco, cioè il {@link GameRuler}, valutato è quello
     * ottenuto dalla {@link GameFactory} specificata. Se non conosce già la
     * strategia ritorna sempre {@link Play#TRY_COMPUTE} eccetto che per i giochi
     * con i seguenti nomi che sa che è impossibile calcolarla:
     * <pre>
     *     Othello8x8, Othello10x10, Othello12x12
     * </pre>
     * Il controllo sull'esistenza di un file con la strategia è effettuato solamente
     * in base al nome (senza tentare di leggere il file, perché potrebbe richiedere
     * troppo tempo). */
    @Override
    public Play canPlay(GameFactory<? extends GameRuler<P>> gF) {
        if (gF == null) throw new NullPointerException();
        GameRuler<P> ruler;
        try {
            ruler = gF.newGame();
        } catch (IllegalStateException e) {
            gF.setPlayerNames("fittizio1", "fittizio2");
            ruler = gF.newGame();
        }
        String n = ruler.name();
        if (mappa_strategie.containsKey(n)) return Play.YES;
        if (n == "Othello8x8" || n == "Othello10x10" || n == "Othello12x12") return Play.NO;
        if (m_dir != null) {
            String file_name = "strategy_" + n + ".dat";
            try {
                Path p = m_dir.resolve(file_name);
                System.out.println("CanPlay: Cerco il file " + file_name + " in " + p);
                if (Files.exists(p))
                    if (Files.isRegularFile(p)) {
                        System.out.println("CanPlay: Trovato il file " + file_name);
                        return Play.YES;
                    }
            } catch (Exception e) { throw new IllegalStateException(e); }
        }
        return Play.TRY_COMPUTE;
    }

    /** Tenta di calcolare la strategia ottimale per il gioco specificato. Ovviamente
     * effettua il calcolo solo se il metodo
     * {@link OptimalPlayerFactory#canPlay(GameFactory)} ritorna {@link Play#TRY_COMPUTE}
     * per lo stesso gioco. Il gioco, cioè il {@link GameRuler}, da valutare è quello
     * ottenuto dalla {@link GameFactory} specificata. Se il calcolo ha successo e
     * una directory ({@link OptimalPlayerFactory#setDir(Path)} ) è impostata, tenta
     * di salvare il file con la strategia calcolata, altrimenti la mantiene in
     * memoria. */
    @Override
    public String tryCompute(GameFactory<? extends GameRuler<P>> gF, boolean parallel,
                             Supplier<Boolean> interrupt) {
        Play p = canPlay(gF);
        if (p != Play.TRY_COMPUTE) return "Non posso calcolare la strategia.";
        GameRuler<P> ruler;
        try {
            ruler = gF.newGame();
        } catch (IllegalStateException e) {
            gF.setPlayerNames("fittizio1", "fittizio2");
            ruler = gF.newGame();
        }
        
//ecco il calcolo
        MahStrategy<P> teh_strategy = new MahStrategy(ruler.name(), parallel, ruler.mechanics(), interrupt);
//        parallel = true;
        if (parallel) teh_strategy.flash_forkjoin(ruler.mechanics().start, ruler.mechanics().next);
        else teh_strategy.flash(ruler.mechanics().start, ruler.mechanics().next);
        System.out.println("\nSize of precalc: " + teh_strategy.precalc.size());


        boolean salvata = false;
        if (m_dir != null) {
//eh eh, qui devo salvare il file
            try {
                String file_name = "strategy_" + ruler.name() + ".dat";
                Path path = m_dir.resolve(file_name);
                //qui ci potrebbe essere un problema con object outputstream che non fallisce se 
                //trova un file già creato
                ObjectOutputStream stream = new ObjectOutputStream(Files.newOutputStream(path.toAbsolutePath(), StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE));
                stream.writeObject(teh_strategy.precalc);
                salvata = true;
                System.out.println("Salvata la strategia " + ruler.name());
            } catch (Exception e) {e.printStackTrace();}
        }
        if (!salvata) {
            mappa_strategie.put(ruler.name(), teh_strategy);
        }
        
        
        //eh eh, qui la devo salvare in memoria
        
        return null;
    }

    /** Se il metodo {@link OptimalPlayerFactory#canPlay(GameFactory)} ritorna
     * {@link Play#YES} tenta di creare un {@link OptimalPlayer} con la strategia
     * per il gioco specificato cercandola tra quelle in memoria e se la directory
     * è impostata ({@link OptimalPlayerFactory#setDir(Path)}) anche nel file. */
    @Override
    public Player<P> newPlayer(GameFactory<? extends GameRuler<P>> gF, String name) {
        if (gF == null || name == null) throw new NullPointerException();
        if (canPlay(gF) != Play.YES) throw new IllegalStateException("canPlay() non è Play.YES; Abort");
        GameRuler<P> ruler;
        try {
            ruler = gF.newGame();
        } catch (IllegalStateException e) {
            gF.setPlayerNames("fittizio1", "fittizio2");
            ruler = gF.newGame();
        }
        String n = ruler.name();
        if (mappa_strategie.containsKey(n)) {
            ((MahStrategy<P>) mappa_strategie.get(n)).parallel_move = (String) uno.get() == "Parallel" ? true : false;

            return new OptimalPlayer<P>(name, mappa_strategie.get(n));
        }
        else {
            if (m_dir != null) {
//eh eh, qui leggo il file
            try {
                String file_name = "strategy_" + ruler.name() + ".dat";
                Path path = m_dir.resolve(file_name);
                //qui ci potrebbe essere un problema con object outputstream che non fallisce se 
                //trova un file già creato
                ObjectInputStream stream = new ObjectInputStream(Files.newInputStream(path));
                MahStrategy strategia = new MahStrategy(ruler.name(), false, ruler.mechanics(), null);
                strategia.precalc = (ConcurrentHashMap<Codez, Integer>) stream.readObject();
                strategia.parallel_move = (String) uno.get() == "Parallel" ? true : false;
                System.out.println("NewPlayer: Trovato file strategia " + ruler.name());
                return new OptimalPlayer<P>(name, strategia);
            } catch ( Exception e) {e.printStackTrace();}
            }
            throw new IllegalStateException("La strategia non è salvata in memoria e la directory non è stata impostata. Abort");
        }
    }
}

class Codez implements Serializable{
    public final byte[] data;
    
    public Codez (byte [] data) {
        if (data == null) throw new NullPointerException();
        this.data = data;
    }
    
    public boolean equals(Object other) {
        if (! (other instanceof Codez) ) return false;
        return Arrays.equals(this.data , ((Codez) other).data);
    }
    
    public int hashCode() {
        return Arrays.hashCode(data);
    }
}
